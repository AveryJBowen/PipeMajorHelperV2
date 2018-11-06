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
import app.avery.pipemajorhelperv2.Model.Member;
import app.avery.pipemajorhelperv2.R;
import io.realm.Realm;
import io.realm.RealmList;

public class RosterListViewFragment extends Fragment {
    private Realm realm;
    private RecyclerView rosterView;
    private RosterRecyclerViewAdapter adapter;
    Band band;
    Member member;

    public RosterListViewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_roster_list_view, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        ImageButton addMemberButton = view.findViewById(R.id.addMemberButton);
        addMemberButton.setBackgroundResource(R.drawable.ic_member_add);
        TextView bandNameRoster = view.findViewById(R.id.bandNameRoster);
        realm = Realm.getDefaultInstance();
        band = realm.where(Band.class).findFirst();
        bandNameRoster.setText(band.getName() + " Roster");

        if(band.getRoster().size() > 0){
            setUpRecyclerView(view, band.getRoster());

        }
        else {
            populateTestRoster(view, realm);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        rosterView.setAdapter(null);
        realm.close();
    }

    private void setUpRecyclerView(View v, RealmList<Member> rosterList){
        rosterView = v.findViewById(R.id.rosterRecyclerView);
        adapter = new RosterRecyclerViewAdapter(rosterList);
        rosterView.setLayoutManager(new LinearLayoutManager(getContext()));
        rosterView.setAdapter(adapter);

        TouchHelperCallback touchHelperCallback = new TouchHelperCallback();
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
        touchHelper.attachToRecyclerView(rosterView);
    }

    //TODO: Delete tester method:
    private void populateTestRoster(View view, Realm realm){
        realm.executeTransaction(r -> {
            for(int i = 0; i < 15; i++){
                Member member = r.createObject(Member.class);
                member.setName("Testy McTestface" + i);
                if(i % 2 == 0){
                    if(i == 2){
                        member.setRank("Pipe Major");
                    }
                    if(i == 4){
                        member.setRank("Pipe Sergeant");
                    }
                    else{
                        member.setRank("Piper");
                    }
                }
                else{
                    if(i == 1){
                        member.setRank("Drum Major");
                    }
                    else{
                        member.setRank("Drummer");
                    }
                }
                member.setCity("Baltimore");
                member.setEmail("test@test.com");
                member.setPhone("717-681-7070");
                member.setStreetAddress("1414 Key Highway");
                member.setState("MD");
                member.setZipcode("21230");
                member.setYearJoined(2001);
                band.getRoster().add(member);
            }
        });
        realm.close();
        setUpRecyclerView(view, band.getRoster());
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
            member = realm.where(Member.class).equalTo("name", name).findFirst();
            realm.commitTransaction();
            ((RosterActivity)getActivity()).showMemberDetail(member);
        }

        @Override
        public boolean isLongPressDragEnabled(){
            return false;
        }
    }
}
