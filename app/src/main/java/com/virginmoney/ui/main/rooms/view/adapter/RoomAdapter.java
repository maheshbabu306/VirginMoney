package com.virginmoney.ui.main.rooms.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.virginmoney.R;
import com.virginmoney.ui.main.people.model.PeopleModel;
import com.virginmoney.ui.main.rooms.model.RoomModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {

    RoomAdapter.MyViewHolder myViewHolder;
    Context mContext;
    private List<RoomModel> data;

    public RoomAdapter(Context context, List<RoomModel> roomdata) {
        this.mContext = context;
        this.data = roomdata;
    }
    @Override
    public RoomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_roomsitem, parent, false);
        myViewHolder = new RoomAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.MyViewHolder holder, int position) {
        RoomModel pdata = data.get(position);
        holder.id.setText(String.valueOf(pdata.getId()));
        holder.created.setText(pdata.getCreatedAt());
        holder.occupency.setText(String.valueOf(pdata.getMaxOccupancy()));

        if(pdata.getIsOccupied().equals(true)){
            holder.roomAvailable.setVisibility(View.GONE);
            holder.roomNotAvailable.setVisibility(View.VISIBLE);
            holder.roomNotAvailable.setText("Not Avilable");

        } else if (pdata.getIsOccupied().equals(false)) {
            holder.roomNotAvailable.setVisibility(View.GONE);
            holder.roomAvailable.setVisibility(View.VISIBLE);
            holder.roomAvailable.setText("Avilable");

        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id_text) TextView id;
        @BindView(R.id.occupency_text) TextView occupency;
        @BindView(R.id.created_text) TextView created;
        @BindView(R.id.room_avilable) TextView roomAvailable;
        @BindView(R.id.room_not_avilable) TextView roomNotAvailable;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
