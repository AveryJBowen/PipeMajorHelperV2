package app.avery.pipemajorhelperv2.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.avery.pipemajorhelperv2.Model.Member;
import app.avery.pipemajorhelperv2.R;
import io.realm.Realm;

public class RosterDetailViewFragment extends Fragment {
    private Realm realm;
    Member member;


    public RosterDetailViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_roster_detail_view, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView rosterDetailName = view.findViewById(R.id.rosterDetailName);
        ImageView instrumentImage = view.findViewById(R.id.instrumentImage);
        String name = (getActivity().getIntent().getStringExtra("MemberToDetail"));
        realm = Realm.getDefaultInstance();
        member = realm.where(Member.class).equalTo("name", name).findFirst();
        rosterDetailName.setText(member.getName());
        if(member.getRank().contains("Pipe")){
            instrumentImage.setBackgroundResource(R.drawable.ic_pipes);
        }
        else if(member.getRank().contains("Drum")){
            instrumentImage.setBackgroundResource(R.drawable.ic_drum);
        }
        else{
            instrumentImage.setBackgroundResource(R.drawable.ic_star);
        }
    }
}
