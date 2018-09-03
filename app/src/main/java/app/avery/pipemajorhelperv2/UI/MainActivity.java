package app.avery.pipemajorhelperv2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.avery.pipemajorhelperv2.Model.Band;
import app.avery.pipemajorhelperv2.Model.Job;
import app.avery.pipemajorhelperv2.Model.Member;
import app.avery.pipemajorhelperv2.Model.MusicSet;
import app.avery.pipemajorhelperv2.Model.User;
import app.avery.pipemajorhelperv2.R;
import io.realm.Realm;
import io.realm.RealmList;

public class MainActivity extends BaseActivity {
    public static final String TAG = "Log: ";
    private Realm realm;
    private Intent starterIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        starterIntent = getIntent();
        realm = Realm.getDefaultInstance();
        final User user = realm.where(User.class).findFirst();

        if(user == null){
            setContentView(R.layout.activity_main_view_get_user);
        }
        else{
            String userName = user.getName();
            Band band = user.getBand();
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

        final User newUser = new User();
        final Band newBand = new Band();
        newUser.setName(newUserName);
        newUser.setBand(newBand);
        newBand.setName(newBandName);
        newBand.setUser(newUser);
        newUser.setBand(newBand);

        realm.beginTransaction();
        realm.copyToRealm(newUser);
        realm.copyToRealm(newBand);
        realm.commitTransaction();
        realm.close();

        finish();
        startActivity(starterIntent);

        //setContentView(R.layout.activity_main);
        //TextView welcomeText = findViewById(R.id.welcomeText);
        //welcomeText.setText("Welcome, " + newUser.getName() + "!\n" + newBand.getName());
    }

}
