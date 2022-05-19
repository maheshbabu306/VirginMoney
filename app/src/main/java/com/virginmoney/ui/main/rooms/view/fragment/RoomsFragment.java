package com.virginmoney.ui.main.rooms.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastytoast.TastyToast;
import com.virginmoney.R;
import com.virginmoney.ui.main.networkUtils.NetworkConnectivity;
import com.virginmoney.ui.main.rooms.model.RoomModel;
import com.virginmoney.ui.main.rooms.presenter.implementation.RoomsDataInteractImpl;
import com.virginmoney.ui.main.rooms.presenter.interact.IRoomView;
import com.virginmoney.ui.main.rooms.presenter.interact.IRoomsInteract;
import com.virginmoney.ui.main.rooms.view.adapter.RoomAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomsFragment extends Fragment implements IRoomView {
    @BindView(R.id.rooms_recyclerview) RecyclerView peopleRecylerview;
    @BindView(R.id.pBar_rooms) ProgressBar progressBar;

    RoomAdapter roomAdapter;
    IRoomsInteract interact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.fragment_rooms, container, false);
        ButterKnife.bind(this, view);
        interact = new RoomsDataInteractImpl(this);

        if (NetworkConnectivity.checkConnection(getContext())) {
            interact.getRoomData();
        } else {
            showToast("Please Check Your Internet Connection");
        }
        return view;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String msg) {
        TastyToast.warning(getContext(),msg,TastyToast.LENGTH_LONG, TastyToast.SHAPE_RECTANGLE, false);
    }

    @Override
    public void success(String msg) {
        TastyToast.success(getContext(),msg,TastyToast.LENGTH_LONG, TastyToast.SHAPE_RECTANGLE, false);
    }
    @Override
    public void RoomData(List<RoomModel> roomdata) {
        if (roomdata != null && !roomdata.equals("") && roomdata.size()>0) {
            roomAdapter = new RoomAdapter(getContext(),roomdata);
            peopleRecylerview.setHasFixedSize(true);
            @SuppressLint("WrongConstant")
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
            peopleRecylerview.setLayoutManager(mLayoutManager1);
            peopleRecylerview.setAdapter(roomAdapter);
        }else {
            showToast("Data Recieve Empty");
        }
    }
}
