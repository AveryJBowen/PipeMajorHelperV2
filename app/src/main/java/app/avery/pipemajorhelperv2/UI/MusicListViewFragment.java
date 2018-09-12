package app.avery.pipemajorhelperv2.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import app.avery.pipemajorhelperv2.Model.Band;
import app.avery.pipemajorhelperv2.Model.MusicSet;
import app.avery.pipemajorhelperv2.Model.Tune;
import app.avery.pipemajorhelperv2.R;
import io.realm.Realm;
import io.realm.RealmList;


public class MusicListViewFragment extends Fragment {
    private Realm realm;
    private RecyclerView musicSetView;
    private MusicRecyclerViewAdapter adapter;
    Band band;
    MusicSet set;

    public MusicListViewFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_music_list_view, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        ImageButton addSetButton = view.findViewById(R.id.addSetButton);
        addSetButton.setBackgroundResource(R.drawable.ic_add_list);
        TextView bandNameMusic = view.findViewById(R.id.bandNameMusic);
        realm = Realm.getDefaultInstance();
        band = realm.where(Band.class).findFirst();
        bandNameMusic.setText(band.getName() + " Repertoire");

        if(band.getSets().size() > 0){
            setUpRecyclerView(view, band.getSets());
        }
        else{
            populateTestSets(view, realm);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        musicSetView.setAdapter(null);
        realm.close();
    }

    private void setUpRecyclerView(View v, RealmList<MusicSet> setList){
        musicSetView = v.findViewById(R.id.musicRecyclerView);
        adapter = new MusicRecyclerViewAdapter(setList);
        musicSetView.setLayoutManager(new LinearLayoutManager(getContext()));
        musicSetView.setAdapter(adapter);

        TouchHelperCallback touchHelperCallback = new TouchHelperCallback();
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
        touchHelper.attachToRecyclerView(musicSetView);
    }

    //TODO: Delete tester method:
    private void populateTestSets(View view, Realm realm){
        realm.executeTransaction(r -> {
            for(int i = 0; i < 15; i++){
                MusicSet set = r.createObject(MusicSet.class);
                set.setName("Music Set " + i);
                for(int j = 0; j < 3; j++){
                    Tune tune = r.createObject(Tune.class);
                    tune.setName("Tune " + j);
                    set.getTuneList().add(tune);
                }
                band.getSets().add(set);
            }
        });
        realm.close();
        setUpRecyclerView(view, band.getSets());
    }

    private class TouchHelperCallback extends ItemTouchHelper.SimpleCallback{
        TouchHelperCallback(){
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
            return true;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction){
            String name = adapter.getItem(viewHolder.getAdapterPosition()).getName();
            realm.beginTransaction();
            set = realm.where(MusicSet.class).equalTo("name", name).findFirst();
            realm.commitTransaction();
            ((MusicActivity)getActivity()).showSetDetail(set);
        }

        @Override
        public boolean isLongPressDragEnabled(){
            return false;
        }
    }

}