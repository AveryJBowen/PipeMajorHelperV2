package app.avery.pipemajorhelperv2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import app.avery.pipemajorhelperv2.R;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    protected BottomNavigationView navigation;

    private void updateNavigationBarState(){
        int actionID = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        updateNavigationBarState();
    }

    @Override
    public void onPause(){
        super.onPause();
        overridePendingTransition(0, 0);
    }

    void selectBottomNavigationBarItem(int itemID){
        Menu menu = navigation.getMenu();
        for(int i = 0, size = menu.size(); i < size; i++){
            MenuItem item = menu.getItem(i);
            boolean shouldBeChecked = item.getItemId() == itemID;
            if(shouldBeChecked){
                item.setChecked(true);
                break;
            }
        }
    }

    public void onMainImageSelected(int selection){
        if(selection == R.id.action_events){
            startActivity(new Intent(this, EventActivity.class));
        }
        else if(selection == R.id.action_music){
            startActivity(new Intent(this, MusicActivity.class));
        }
        else if(selection == R.id.action_roster){
            startActivity(new Intent(this, RosterActivity.class));
        }
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        navigation.postDelayed(() -> {
            int itemId = item.getItemId();
            if(itemId == R.id.action_main){
                startActivity(new Intent(this, MainActivity.class));
            }
            else if(itemId == R.id.action_events){
                startActivity(new Intent(this, EventActivity.class));
            }
            else if(itemId == R.id.action_music){
                startActivity(new Intent(this, MusicActivity.class));
            }
            else if(itemId == R.id.action_roster){
                startActivity(new Intent(this, RosterActivity.class));
            }
            finish();
        }, 100);
        return true;
    }

    public abstract int getContentViewId();

    public abstract int getNavigationMenuItemId();
}
