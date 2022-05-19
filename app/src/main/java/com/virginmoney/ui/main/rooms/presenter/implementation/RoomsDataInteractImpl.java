package com.virginmoney.ui.main.rooms.presenter.implementation;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.virginmoney.ui.main.networkUtils.IApiInterface;
import com.virginmoney.ui.main.people.model.PeopleModel;
import com.virginmoney.ui.main.people.presenter.interact.IPeopleView;
import com.virginmoney.ui.main.rooms.model.RoomModel;
import com.virginmoney.ui.main.rooms.presenter.interact.IRoomView;
import com.virginmoney.ui.main.rooms.presenter.interact.IRoomsInteract;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.virginmoney.ui.main.networkUtils.IApiInterface.Live_BASE_URL;


public class RoomsDataInteractImpl implements IRoomsInteract {

    private IRoomView view;

    public RoomsDataInteractImpl(IRoomView view) {
        this.view = view;
    }
    @Override
    public void getRoomData() {
        view.showProgress();
        String baseURL = Live_BASE_URL;
        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IApiInterface client = retrofit.create(IApiInterface.class);
        Call<ResponseBody> calltargetResponse = client.getRoomData();

        calltargetResponse.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    view.hideProgress();
                    if (response.isSuccessful() && response.code() == 200) {
                        String responseReceived = response.body().string();
                        System.out.println(responseReceived);
                        view.success("Success");

                        Type collectionType = new TypeToken<List<RoomModel>>(){}.getType();
                        List<RoomModel> userSites  = new Gson().fromJson( responseReceived , collectionType);
                        view.RoomData((List<RoomModel>) userSites);
                    }else {
                        view.showToast("Fail");
                    }
                }catch (Exception e){
                    view.hideProgress();
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showToast("Failed");
            }
        });
    }
}



