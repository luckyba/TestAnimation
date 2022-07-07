package com.luckyba.testanimation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private Context mContext;
    private ArrayList<People> listPeople;
    private List<Integer> listId;
    private MainActivity.Listener mListener;
    public PeopleAdapter(Context context, MainActivity.Listener listener) {
        mContext = context;
        mListener = listener;
    }

    public void setData(ArrayList<People> listPeople, List<Integer> listIndex) {
        this.listPeople = listPeople;
        this.listId = listIndex;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View container = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new PeopleViewHolder(container);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        holder.itemView.setOnLongClickListener(v -> {
            mListener.onLongClickItem(v, position);
            return true;
        });
        holder.cbFavorite.setOnClickListener(v->{
            mListener.onClickItem(v, position);
        });
        People people = getPeople(listId.get(position));
        if (people != null) {
            holder.index = position;
            holder.tvName.setText(people.name);
            holder.cbFavorite.setChecked(people.isFavorite);
            holder.tvIndex.setText(String.valueOf(people.id));
        }

    }

    private People getPeople(int id) {
        for(People people:listPeople) {
            if (people.id == id) {
                return people;
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return listId.size();
    }

    @Override
    public void onViewRecycled(@NonNull PeopleViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull PeopleViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    static class PeopleViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbFavorite;
        TextView tvName;
        TextView tvIndex;
        int index;
        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            cbFavorite = itemView.findViewById(R.id.cb_favarite);
            tvName = itemView.findViewById(R.id.name);
            tvIndex = itemView.findViewById(R.id.tv_index);
        }
    }

}
