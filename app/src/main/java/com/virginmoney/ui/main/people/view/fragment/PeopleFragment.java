package com.virginmoney.ui.main.people.view.fragment;

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
import com.virginmoney.ui.main.people.model.PeopleModel;
import com.virginmoney.ui.main.people.presenter.implementation.PeopleDataInteractImpl;
import com.virginmoney.ui.main.people.presenter.interact.IPeopleInteract;
import com.virginmoney.ui.main.people.presenter.interact.IPeopleView;
import com.virginmoney.ui.main.people.view.adapter.PeopleAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleFragment extends Fragment implements IPeopleView {

    @BindView(R.id.people_recyclerview) RecyclerView peopleRecylerview;
    @BindView(R.id.pBar_people) ProgressBar progressBar;
    PeopleAdapter peopleAdapter;
    IPeopleInteract interact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.fragment_peolpe, container, false);
        ButterKnife.bind(this, view);
        interact = new PeopleDataInteractImpl(this);

        if (NetworkConnectivity.checkConnection(getContext())) {
            interact.getPeopleData();
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
    public void peopleData(List<PeopleModel> peopledata) {
        if (peopledata != null && !peopledata.equals("")  && peopledata.size()>0) {
            peopleAdapter = new PeopleAdapter(getContext(),peopledata);
            peopleRecylerview.setHasFixedSize(true);
            @SuppressLint("WrongConstant")
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
            peopleRecylerview.setLayoutManager(mLayoutManager1);
            peopleRecylerview.setAdapter(peopleAdapter);
        }else {
            showToast("Data Recieve Empty");
        }

    }

}
