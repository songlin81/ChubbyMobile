package com.chubbymobile.wwh.chubbymobile.MVP.model;

import com.chubbymobile.wwh.chubbymobile.MVP.bean.User;

public interface OnUserInfoListener {

    void getUserInfoSuccess(User user);

    void getUserInfoFailed();
}