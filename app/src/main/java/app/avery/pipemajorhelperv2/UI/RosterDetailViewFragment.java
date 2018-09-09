package app.avery.pipemajorhelperv2.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import app.avery.pipemajorhelperv2.Model.Band;
import app.avery.pipemajorhelperv2.Model.Member;
import app.avery.pipemajorhelperv2.R;
import io.realm.Realm;
import static java.lang.Integer.parseInt;

public class RosterDetailViewFragment extends Fragment implements View.OnClickListener{
    private Realm realm;
    Member member;
    Band band;
    EditText rosterDetailName;
    ImageView instrumentImage;
    ImageView rankImage;
    Spinner rankSpinner;
    EditText profileEmail;
    EditText profilePhone;
    EditText profileAddress;
    EditText profileCity;
    EditText profileState;
    EditText profileZip;
    EditText profileYear;

    public RosterDetailViewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_roster_detail_view, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        rosterDetailName = view.findViewById(R.id.rosterDetailName);
        instrumentImage = view.findViewById(R.id.instrumentImage);
        rankImage = view.findViewById(R.id.rankImage);
        rankSpinner = view.findViewById(R.id.rankSpinner);
        profileEmail = view.findViewById(R.id.profileEmail);
        profilePhone = view.findViewById(R.id.profilePhone);
        profileAddress = view.findViewById(R.id.profileAddress);
        profileCity = view.findViewById(R.id.profileCity);
        profileState = view.findViewById(R.id.profileState);
        profileZip = view.findViewById(R.id.profileZip);
        profileYear = view.findViewById(R.id.profileYear);
        ImageButton editButton = view.findViewById(R.id.editButton);
        editButton.setOnClickListener(this);
        ImageButton deleteButton = view.findViewById(R.id.deleteButton);
        deleteButton.setBackgroundResource(R.drawable.ic_delete);
        deleteButton.setOnClickListener(this);

        String name = (getActivity().getIntent().getStringExtra("MemberToDetail"));
        realm = Realm.getDefaultInstance();
        band = realm.where(Band.class).findFirst();

        if(!name.equals("New Member")){
            editButton.setBackgroundResource(R.drawable.ic_edit);
            member = realm.where(Member.class).equalTo("name", name).findFirst();
            rosterDetailName.setText(member.getName());
            String rank = member.getRank();
            String streetAddress = member.getStreetAddress();
            String city = member.getCity();
            String state = member.getState();
            String phone = member.getPhone();
            String zip = member.getZipcode();
            String email = member.getEmail();
            String year = Integer.toString(member.getYearJoined());

            profileAddress.setText(streetAddress);
            profileCity.setText(city);
            profileState.setText(state);
            profileEmail.setText(email);
            profilePhone.setText(phone);
            profileZip.setText(zip);
            profileYear.setText(year);

            for(int i = 0; i < rankSpinner.getAdapter().getCount(); i++){
                if(rankSpinner.getAdapter().getItem(i).toString().contains(rank)){
                    rankSpinner.setSelection(i);
                }
            }

            //SET INSTRUMENT IMAGE:
            if(rank.contains("Pipe")){
                instrumentImage.setBackgroundResource(R.drawable.ic_pipes);
            }
            else if(rank.contains("Drum")){
                instrumentImage.setBackgroundResource(R.drawable.ic_drum);
            }
            else{
                instrumentImage.setBackgroundResource(R.drawable.ic_star);
            }

            //SET RANK IMAGE FOR OFFICERS
            if(rank.contains("Lance")){
                rankImage.setBackgroundResource(R.drawable.ic_lance_corp);
            }
            else if(rank.contains("Corporal")){
                rankImage.setBackgroundResource(R.drawable.ic_corp);
            }
            else if(rank.contains("Sergeant")){
                rankImage.setBackgroundResource(R.drawable.ic_sarge);
            }
            else if(rank.contains("Major")){
                rankImage.setBackgroundResource(R.drawable.ic_major);
            }
        }

        else{
            editButton.setBackgroundResource(R.drawable.ic_save);
            rosterDetailName.setHint("Member Name");
            profileAddress.setHint("Street Address");
            profileCity.setHint("City");
            profileState.setHint("State");
            profileEmail.setHint("Email");
            profilePhone.setHint("Phone");
            profileZip.setHint("Zip");
            profileYear.setHint("Year");
        }
    }

    public void saveMember(){
        String zip = profileZip.getText().toString();
        String address = profileAddress.getText().toString();
        String state = profileState.getText().toString();
        String city = profileCity.getText().toString();
        String rank = rankSpinner.getSelectedItem().toString();
        String email = profileEmail.getText().toString();
        String yearString = profileYear.getText().toString();
        int year = parseInt(yearString);
        String name = rosterDetailName.getText().toString();
        String phone = profilePhone.getText().toString();

        Realm realm = Realm.getDefaultInstance();
        final Member memberToUpdate = realm.where(Member.class).equalTo("name", name).findFirst();
        if(memberToUpdate == null){
            realm.executeTransaction(r -> {
                Member memberToAdd = r.createObject(Member.class);
                memberToAdd.setName(name);
                memberToAdd.setYearJoined(year);
                memberToAdd.setZipcode(zip);
                memberToAdd.setState(state);
                memberToAdd.setStreetAddress(address);
                memberToAdd.setPhone(phone);
                memberToAdd.setEmail(email);
                memberToAdd.setRank(rank);
                memberToAdd.setCity(city);
                band.getRoster().add(memberToAdd);
            });
        }
        else{
            realm.executeTransaction(r -> {
                memberToUpdate.setName(name);
                memberToUpdate.setYearJoined(year);
                memberToUpdate.setZipcode(zip);
                memberToUpdate.setState(state);
                memberToUpdate.setStreetAddress(address);
                memberToUpdate.setPhone(phone);
                memberToUpdate.setEmail(email);
                memberToUpdate.setRank(rank);
                memberToUpdate.setCity(city);
            });
        }
    }

    public void deleteMember(){
        String name = rosterDetailName.getText().toString();
        Realm realm = Realm.getDefaultInstance();

        final Member memberToDelete = realm.where(Member.class).equalTo("name", name).findFirst();
        realm.executeTransaction(r -> {
            memberToDelete.deleteFromRealm();
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editButton:
                saveMember();
                getActivity().recreate();
                break;
            case R.id.deleteButton:
                deleteMember();
                ((RosterActivity)getActivity()).showListView();
                break;
        }

    }
}
