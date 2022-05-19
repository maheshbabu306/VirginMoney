package com.virginmoney.ui.main.rooms.presenter.interact;

import com.virginmoney.ui.main.rooms.model.RoomModel;

import java.util.List;

public interface IRoomView {
    void showProgress();
    void hideProgress();
    void showToast(String msg);
    void success(String msg);
    void RoomData(List<RoomModel> roomModel);

}
