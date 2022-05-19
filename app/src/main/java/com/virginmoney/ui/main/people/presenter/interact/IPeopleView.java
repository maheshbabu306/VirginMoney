package com.virginmoney.ui.main.people.presenter.interact;

import com.virginmoney.ui.main.people.model.PeopleModel;
import java.util.List;

public interface IPeopleView {
    void showProgress();
    void hideProgress();
    void showToast(String msg);
    void success(String msg);
    void peopleData(List<PeopleModel> peopledata);

}
