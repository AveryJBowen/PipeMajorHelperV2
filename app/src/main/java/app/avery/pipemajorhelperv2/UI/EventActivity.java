package app.avery.pipemajorhelperv2.UI;

import app.avery.pipemajorhelperv2.R;

public class EventActivity extends BaseActivity {

    @Override
    public int getContentViewId(){
        return R.layout.activity_event;
    }

    @Override
    public int getNavigationMenuItemId(){
        return R.id.action_events;
    }
}
