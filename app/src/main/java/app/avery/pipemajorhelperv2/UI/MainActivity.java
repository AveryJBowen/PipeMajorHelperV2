package app.avery.pipemajorhelperv2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.avery.pipemajorhelperv2.Model.Band;
import app.avery.pipemajorhelperv2.Model.User;
import app.avery.pipemajorhelperv2.R;
import io.realm.Realm;

public class MainActivity extends BaseActivity {
    private Realm realm;
    private Intent starterIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        starterIntent = getIntent();

        realm = Realm.getDefaultInstance();
        final Band band = realm.where(Band.class).findFirst();

        if(band == null){
            setContentView(R.layout.activity_main_view_get_user);
        }
        else{
            String userName = band.getUser().getName();
            String bandName = band.getName();
            TextView welcomeText = findViewById(R.id.welcomeText);
            welcomeText.setText("Welcome back, " + userName + "!\n" + bandName);
        }
    }

    @Override
    public int getContentViewId(){
        return R.layout.activity_main;
    }

    @Override
    public int getNavigationMenuItemId(){
        return R.id.action_main;
    }

    public void onStartClick(View v){
        EditText userName = findViewById(R.id.userGetName);
        EditText bandName = findViewById(R.id.bandGetName);
        String newUserName = userName.getText().toString();
        String newBandName = bandName.getText().toString();

        realm.executeTransaction(r -> {
            User newUser = r.createObject(User.class);
            Band newBand = r.createObject(Band.class);
            newUser.setName(newUserName);
            newUser.setBand(newBand);
            newBand.setName(newBandName);
            newBand.setUser(newUser);
        });
        realm.close();
        finish();
        startActivity(starterIntent);
    }
}
