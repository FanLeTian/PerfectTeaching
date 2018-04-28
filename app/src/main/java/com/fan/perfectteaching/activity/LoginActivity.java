package com.fan.perfectteaching.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.fan.perfectteaching.R;
import com.fan.perfectteaching.activity.base.BackBaseActivity;
import com.fan.perfectteaching.beans.LoginBean;
import com.fan.perfectteaching.netutil.MyWealthApi;
import com.fan.perfectteaching.netutil.SuscriberX;
import com.fan.perfectteaching.util.SharedPreferencesUtil;
import com.fan.perfectteaching.util.ToastUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by acer on 2017/4/27.
 */

public class LoginActivity extends BackBaseActivity implements View.OnClickListener{


    private View toRigister;
    private View toFindPass;
    private EditText account;
    private EditText password;
    private Button login;

    private String loginName;
    private String passWord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        setTitle("登录");
        initView();
    }

    private void initView() {
        toRigister = findViewById(R.id.to_rigister);
        toFindPass = findViewById(R.id.forget);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        toFindPass.setOnClickListener(this);
        toRigister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_rigister:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.forget:
//                Intent intent2 = new Intent(this, FindPassWordActivity.class);
//                startActivity(intent2);
                break;
            case R.id.login:
                loginName = account.getText().toString();
                passWord = password.getText().toString();
                if (TextUtils.isEmpty(loginName)) {
                    ToastUtil.showToast(this, "账号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(passWord)) {
                    ToastUtil.showToast(this, "密码不能为空");
                    return;
                }
                toLogin(loginName, passWord);
                break;
        }
    }

    private void toLogin(String loginName, String passWord) {
        HashMap<String, String> params = new HashMap<>();
        params.put("num", loginName);
        params.put("password", passWord);
        mSubscription.add(MyWealthApi.getInstance().getMyWealthService().getLogin(params)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new SuscriberX<LoginBean>(LoginActivity.this){
            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(LoginBean loginBean) {
                super.onNext(loginBean);
                if (loginBean.isOk()) {
                    ToastUtil.showToast(LoginActivity.this, loginBean.getMsg());
                    SharedPreferencesUtil.setPrefString(LoginActivity.this, "USER_ID", loginBean.getData().getId() + "");
                    SharedPreferencesUtil.setPrefString(LoginActivity.this, "USER_NAME", loginBean.getData().getName());
                    finish();
                } else {
                    ToastUtil.showToast(LoginActivity.this, loginBean.getMsg());
                }
            }
        }));
    }
}
