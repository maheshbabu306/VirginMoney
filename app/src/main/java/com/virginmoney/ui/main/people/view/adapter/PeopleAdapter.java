package com.virginmoney.ui.main.people.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.MyViewHolder> {

    PeopleAdapter.MyViewHolder myViewHolder;
    Context mContext;
    private List<PeopleModel> data;

    public PeopleAdapter(Context context, List<PeopleModel> peopledata) {
        this.mContext = context;
        this.data = peopledata;
    }
    @Override
    public PeopleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_peopleitem, parent, false);
        myViewHolder = new PeopleAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PeopleAdapter.MyViewHolder holder, int position) {
        PeopleModel pdata = data.get(position);

        Glide.with(mContext)
                .load(pdata.getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.userImage);

        holder.personName.setText(pdata.getFirstName()+" "+pdata.getLastName());
        holder.email.setText(pdata.getEmail());
        holder.work.setText(pdata.getJobtitle());
        holder.id.setText(String.valueOf(pdata.getId()));
        holder.favColor.setText(pdata.getFavouriteColor());
        holder.created.setText(pdata.getCreatedAt());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_image) ImageView userImage;
        @BindView(R.id.name_text) TextView personName;
        @BindView(R.id.email_text) TextView email;
        @BindView(R.id.work_text) TextView work;
        @BindView(R.id.id_text) TextView id;
        @BindView(R.id.fav_clr_text) TextView favColor;
        @BindView(R.id.created_text) TextView created;

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
