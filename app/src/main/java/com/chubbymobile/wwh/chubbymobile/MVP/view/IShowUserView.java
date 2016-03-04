package com.chubbymobile.wwh.chubbymobile.MVP.view;

import com.chubbymobile.wwh.chubbymobile.MVP.bean.User;

public interface IShowUserView {

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

}
