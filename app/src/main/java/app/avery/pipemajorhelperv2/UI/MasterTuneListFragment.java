package app.avery.pipemajorhelperv2.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import app.avery.pipemajorhelperv2.Model.Band;
import app.avery.pipemajorhelperv2.Model.Tune;
import app.avery.pipemajorhelperv2.Model.MusicSet;
import app.avery.pipemajorhelperv2.R;
import io.realm.Realm;
import io.realm.RealmList;

public class MasterTuneListFragment extends Fragment {
    private Realm realm;
    Band band;
    MusicSet set;
    private RecyclerView libraryView;
    private EditText setName;
    private TuneLibraryRecyclerViewAdapter adapter;
    Tune tune;

    public MasterTuneListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_master_tune_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        realm = Realm.getDefaultInstance();
        band = realm.where(Band.class).findFirst();
        String name = band.getName();

        realm.executeTransaction(r -> {
            set = r.createObject(MusicSet.class);
        });
        setName = view.findViewById(R.id.newSetName);
        TextView bandName = view.findViewById(R.id.bandNameLibrary);
        bandName.setText(name + " Master Tune Library");

        if(band.getLibrary().size() > 0){
            setUpRecyclerView(view, band.getLibrary());

        }
        else {
            populateTestRoster(view, realm);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        libraryView.setAdapter(null);
        realm.close();
    }

    private void setUpRecyclerView(View v, RealmList<Tune> tuneList){
        libraryView = v.findViewById(R.id.libraryRecyclerView);
        adapter = new TuneLibraryRecyclerViewAdapter(tuneList);
        libraryView.setLayoutManager(new LinearLayoutManager(getContext()));
        libraryView.setAdapter(adapter);

        MasterTuneListFragment.TouchHelperCallback touchHelperCallback = new MasterTuneListFragment.TouchHelperCallback();
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
        touchHelper.attachToRecyclerView(libraryView);
    }

    //TODO: Delete tester method:
    private void populateTestRoster(View view, Realm realm){
        realm.executeTransaction(r -> {
            for(int i = 0; i < 15; i++){
                Tune tune = r.createObject(Tune.class);
                tune.setName("Tester tune " + i);
                if(i % 2 == 0){
                    if(i == 2){
                        tune.setSignature("3/4");
                    }
                    if(i == 4){
                        tune.setSignature("6/8");
                    }
                    else{
                        tune.setSignature("2/4");
                    }
                }
                else{
                    if(i == 1){
                        tune.setSignature("9/8");
                    }
                    else{
                        tune.setSignature("4/4");
                    }
                }
                band.getLibrary().add(tune);
            }
        });
        realm.close();
        setUpRecyclerView(view, band.getLibrary());
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
            //TODO: get set name from edit box and create the new set 
            String name = adapter.getItem(viewHolder.getAdapterPosition()).getName();
            realm.beginTransaction();
            tune = realm.where(Tune.class).equalTo("name", name).findFirst();
            set.getTuneList().add(tune);

            realm.commitTransaction();
        }

        @Override
        public boolean isLongPressDragEnabled(){
            return false;
        }
    }

}
