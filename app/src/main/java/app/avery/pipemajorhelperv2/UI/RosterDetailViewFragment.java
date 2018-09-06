package app.avery.pipemajorhelperv2.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.avery.pipemajorhelperv2.Model.Member;
import app.avery.pipemajorhelperv2.R;

public class RosterDetailViewFragment extends Fragment {
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

    }


}
