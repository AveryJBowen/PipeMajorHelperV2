package app.avery.pipemajorhelperv2.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Set;

import app.avery.pipemajorhelperv2.Model.Band;
import app.avery.pipemajorhelperv2.Model.Member;
import app.avery.pipemajorhelperv2.Model.MusicSet;
import app.avery.pipemajorhelperv2.R;
import io.realm.Realm;


public class SetDetailViewFragment extends Fragment implements View.OnClickListener {
    private Realm realm;
    Band band;
    MusicSet set;
    TextView setNameView;
    TextView setTuneListView;

    public SetDetailViewFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_set_detail_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        setNameView = view.findViewById(R.id.setNameTextView);
        setTuneListView = view.findViewById(R.id.setTunes);

        realm = Realm.getDefaultInstance();
        band = realm.where(Band.class).findFirst();

        String name = (getActivity().getIntent().getStringExtra("SetToDetail"));
        setNameView.setText(name);

        set = realm.where(MusicSet.class).equalTo("name", name).findFirst();
        String tuneList = "";
        for(int i = 0; i < set.getTuneList().size(); i++){
            tuneList += set.getTuneList().get(i).getName();
            tuneList += "\n";
        }

        setTuneListView.setText(tuneList);

        ImageButton deleteButton = view.findViewById(R.id.deleteSetButton);
        deleteButton.setBackgroundResource(R.drawable.ic_delete);
        deleteButton.setOnClickListener(this);
    }

    public void deleteMusicSet(){
        String name = setNameView.getText().toString();
        Realm realm = Realm.getDefaultInstance();

        final MusicSet setToDelete = realm.where(MusicSet.class).equalTo("name", name).findFirst();
        realm.executeTransaction(r -> {
            band.getSets().remove(setToDelete);
            setToDelete.deleteFromRealm();
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.deleteSetButton:
                deleteMusicSet();
                ((MusicActivity)getActivity()).showListView();
                break;
        }
    }


}
