package app.avery.pipemajorhelperv2;

import android.os.Bundle;

public class MusicActivity extends BaseActivity {

    @Override
    public int getContentViewId(){
        return R.layout.activity_music;
    }

    @Override
    public int getNavigationMenuItemId(){
        return R.id.action_music;
    }
}
