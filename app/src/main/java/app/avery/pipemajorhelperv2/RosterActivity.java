package app.avery.pipemajorhelperv2;

import android.os.Bundle;

public class RosterActivity extends BaseActivity {

    @Override
    public int getContentViewId(){
        return R.layout.activity_roster;
    }

    @Override
    public int getNavigationMenuItemId(){
        return R.id.action_roster;
    }
}
