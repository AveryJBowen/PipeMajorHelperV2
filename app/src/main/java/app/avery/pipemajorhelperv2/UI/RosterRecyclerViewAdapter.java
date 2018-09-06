package app.avery.pipemajorhelperv2.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.avery.pipemajorhelperv2.Model.Member;
import app.avery.pipemajorhelperv2.R;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class RosterRecyclerViewAdapter extends RealmRecyclerViewAdapter<Member, RosterRecyclerViewAdapter.ViewHolder> {
    //private OnRosterItemClick callback;

    RosterRecyclerViewAdapter(OrderedRealmCollection<Member> data){
        super(data, true);
        //this.callback = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final Member member = getItem(position);
        holder.memberName.setText(member.getName());
        holder.memberRank.setText(member.getRank());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView memberName;
        TextView memberRank;

        public ViewHolder(View view){
            super(view);
            memberName = view.findViewById(R.id.memberNameItem);
            memberRank = view.findViewById(R.id.memberRankItem);
        }
    }
}
