package app.avery.pipemajorhelperv2.UI;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import app.avery.pipemajorhelperv2.Model.MusicSet;
import app.avery.pipemajorhelperv2.R;

public class MusicActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.musicFragmentPlaceholder, new MusicListViewFragment());
        fragmentTransaction.commit();
    }

    @Override
    public int getContentViewId(){
        return R.layout.activity_music;
    }

    @Override
    public int getNavigationMenuItemId(){
        return R.id.action_music;
    }

    //METHODS
    public void addSetClick(View view){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.musicFragmentPlaceholder, new SetDetailViewFragment());
        getIntent().putExtra("SetToDetail", "New Set");
        fragmentTransaction.commit();
    }

    public void showSetDetail(MusicSet set){

    }


}
