package app.avery.pipemajorhelperv2.UI;

import android.os.Bundle;
import android.view.View;

import app.avery.pipemajorhelperv2.Model.Member;
import app.avery.pipemajorhelperv2.R;
import android.support.v4.app.FragmentTransaction;

public class RosterActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayoutPlaceholder, new RosterListViewFragment());
        fragmentTransaction.commit();
    }

    @Override
    public int getContentViewId(){
        return R.layout.activity_roster;
    }

    @Override
    public int getNavigationMenuItemId(){
        return R.id.action_roster;
    }

    //METHODS
    public void addMemberClick(View view){

    }

    public void showMemberDetail(Member member){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayoutPlaceholder, new RosterDetailViewFragment());
        fragmentTransaction.commit();
    }
}
