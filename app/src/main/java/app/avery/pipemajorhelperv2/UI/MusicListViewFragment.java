package app.avery.pipemajorhelperv2.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.avery.pipemajorhelperv2.Model.Band;
import app.avery.pipemajorhelperv2.Model.MusicSet;
import app.avery.pipemajorhelperv2.R;
import io.realm.Realm;


public class MusicListViewFragment extends Fragment {
    private Realm realm;
    private RecyclerView musicSetView;
    private MusicRecyclerViewAdapter adapter;
    Band band;
    MusicSet set;

    public MusicListViewFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_music_list_view, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

    }


}