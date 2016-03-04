package com.chubbymobile.wwh.chubbymobile.MVP.model;

import com.chubbymobile.wwh.chubbymobile.MVP.bean.User;

public class GetUserInfo implements IGetUser {

    @Override
    public void getUserInfo(final int id, final OnUserInfoListener listener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (id == 1) {
                    User user = new User();
                    user.setName("Dev per");
                    user.setAge("100");
                    user.setSex("M");
                    user.setId("1");
                    listener.getUserInfoSuccess(user);
                } else {
                    listener.getUserInfoFailed();
                }
            }
        }.start();
    }
}