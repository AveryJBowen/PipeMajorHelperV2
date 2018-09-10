package app.avery.pipemajorhelperv2.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import app.avery.pipemajorhelperv2.Model.MusicSet;
import app.avery.pipemajorhelperv2.R;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class MusicRecyclerViewAdapter extends RealmRecyclerViewAdapter<MusicSet, MusicRecyclerViewAdapter.ViewHolder>{

    MusicRecyclerViewAdapter(OrderedRealmCollection<MusicSet> data){
        super(data, true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final MusicSet set = getItem(position);
        holder.setName.setText(set.getName());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView setName;

        public ViewHolder(View view){
            super(view);
            setName = view.findViewById(R.id.setNameItem);
        }
    }
}
