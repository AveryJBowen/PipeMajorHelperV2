package app.avery.pipemajorhelperv2.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.avery.pipemajorhelperv2.Model.Tune;
import app.avery.pipemajorhelperv2.R;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class TuneLibraryRecyclerViewAdapter extends RealmRecyclerViewAdapter<Tune, TuneLibraryRecyclerViewAdapter.ViewHolder> {

    TuneLibraryRecyclerViewAdapter(OrderedRealmCollection<Tune> data){
        super(data, true);
    }

    @Override
    public TuneLibraryRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.library_list_item, parent, false);
        return new TuneLibraryRecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TuneLibraryRecyclerViewAdapter.ViewHolder holder, int position){
        final Tune tune = getItem(position);
        holder.tuneName.setText(tune.getName());
        holder.tuneSignature.setText(tune.getSignature());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tuneName;
        TextView tuneSignature;

        public ViewHolder(View view){
            super(view);
            tuneName = view.findViewById(R.id.tuneNameItem);
            tuneSignature = view.findViewById(R.id.tuneSignatureItem);
        }
    }
}
