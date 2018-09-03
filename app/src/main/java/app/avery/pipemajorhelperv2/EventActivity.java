package app.avery.pipemajorhelperv2;

import android.os.Bundle;

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
