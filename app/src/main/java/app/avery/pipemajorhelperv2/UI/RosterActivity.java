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

public class RosterActivity extends BaseActivity {
    private Realm realm;
    private RecyclerView rosterView;
    private RosterRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        final User user = realm.where(User.class).findFirst();
        final Band band = user.getBand();
        final RealmList<Member> rosterList = band.getRoster();

        if(rosterList.size() > 0){
            setUpRecyclerView(rosterList);
        }
        else {

        }
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
