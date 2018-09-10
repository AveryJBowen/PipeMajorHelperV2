package app.avery.pipemajorhelperv2.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import app.avery.pipemajorhelperv2.R;

public class MusicActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId(){
        return R.layout.activity_music;
    }

    @Override
    public int getNavigationMenuItemId(){
        return R.id.action_music;
    }


}
