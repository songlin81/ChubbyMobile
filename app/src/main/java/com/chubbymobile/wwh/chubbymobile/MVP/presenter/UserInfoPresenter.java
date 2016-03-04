package com.chubbymobile.wwh.chubbymobile.MVP.presenter;

import com.chubbymobile.wwh.chubbymobile.MVP.bean.User;;
import com.chubbymobile.wwh.chubbymobile.MVP.model.GetUserInfo;
import com.chubbymobile.wwh.chubbymobile.MVP.model.IGetUser;
import com.chubbymobile.wwh.chubbymobile.MVP.model.OnUserInfoListener;
import com.chubbymobile.wwh.chubbymobile.MVP.view.IShowUserView;
import android.os.Handler;

public class UserInfoPresenter {
    private IGetUser iGetUser;
    private IShowUserView iShowUserView;
    private Handler mHandler = new Handler();

    public UserInfoPresenter(IShowUserView iShowUserView) {
        this.iShowUserView = iShowUserView;
        this.iGetUser = new GetUserInfo();
    }

    public void getUserInfoToShow(int id) {
        iShowUserView.showLoading();
        iGetUser.getUserInfo(id, new OnUserInfoListener() {

            @Override
            public void getUserInfoSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iShowUserView.toMainActivity(user);
                        iShowUserView.hideLoading();
                    }
                });
            }

            @Override
            public void getUserInfoFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iShowUserView.showFailedError();
                        iShowUserView.hideLoading();
                    }
                });
            }
        });
    }
}