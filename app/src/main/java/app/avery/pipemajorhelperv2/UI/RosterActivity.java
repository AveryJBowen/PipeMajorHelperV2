package app.avery.pipemajorhelperv2.UI;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import app.avery.pipemajorhelperv2.Model.Band;
import app.avery.pipemajorhelperv2.Model.Member;
import app.avery.pipemajorhelperv2.Model.User;
import app.avery.pipemajorhelperv2.R;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RosterActivity extends BaseActivity {
    private Realm realm;
    private RecyclerView rosterView;
    private RosterRecyclerViewAdapter adapter;
    Band band;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();
        band = realm.where(Band.class).findFirst();

        if(band.getRoster().size() > 0){
            setUpRecyclerView(band.getRoster());
        }
        else {
            populateTestRoster(realm);
        }
    }

    private void populateTestRoster(Realm realm){
        realm.executeTransaction(r -> {
            for(int i = 0; i < 5; i++){
                Member member = r.createObject(Member.class);
                member.setName("Testy McTestface" + i);
                member.setRank("Piper");
                band.getRoster().add(member);
            }
        });
        realm.close();
        setUpRecyclerView(band.getRoster());
    }

    private void setUpRecyclerView(RealmList<Member> rosterList){
        rosterView = findViewById(R.id.rosterRecyclerView);
        adapter = new RosterRecyclerViewAdapter(rosterList);
        rosterView.setLayoutManager(new LinearLayoutManager(this));
        rosterView.setAdapter(adapter);
    }

    @Override
    public int getContentViewId(){
        return R.layout.activity_roster;
    }

    @Override
    public int getNavigationMenuItemId(){
        return R.id.action_roster;
    }

    public void addMemberClick(View view){

    }
}
