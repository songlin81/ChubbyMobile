package com.chubbymobile.wwh.chubbymobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chubbymobile.wwh.chubbymobile.MVP.bean.User;
import com.chubbymobile.wwh.chubbymobile.MVP.presenter.UserInfoPresenter;
import com.chubbymobile.wwh.chubbymobile.MVP.view.IShowUserView;

public class MVPActivity extends Activity implements IShowUserView {

    private Button btn;
    private TextView name_tv, age_tv, sex_tv;
    private ProgressDialog pd = null;
    private UserInfoPresenter userInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        userInfoPresenter = new UserInfoPresenter(this);
        btn = (Button) findViewById(R.id.btn);
        name_tv = (TextView) findViewById(R.id.name_tv);
        age_tv = (TextView) findViewById(R.id.age_tv);
        sex_tv = (TextView) findViewById(R.id.sex_tv);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading……");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfoPresenter.getUserInfoToShow(1);
            }
        });
    }

    @Override
    public void showLoading() {
        pd.show();
    }

    @Override
    public void hideLoading() {
        pd.cancel();
    }

    @Override
    public void toMainActivity(User user) {
        name_tv.setText(user.getName());
        age_tv.setText(user.getAge());
        sex_tv.setText(user.getSex());
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "Failure occurred.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(MVPActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
