package com.fan.perfectteaching.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.fan.perfectteaching.R;
import com.fan.perfectteaching.activity.base.ActivityCollector;
import com.fan.perfectteaching.activity.base.BackBaseActivity;
import com.fan.perfectteaching.beans.RegisterBean;
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

public class RegisterActivity extends BackBaseActivity implements View.OnClickListener{


    private EditText account;
    private EditText passWord;
    private EditText num;
    private EditText phone;
    private Button register;
    private String loginName;
    private String password;
    private String number;
    private String mobile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        setTitle("注册");
        initView();
    }

    private void initView() {
        account = findViewById(R.id.phone);
        passWord = findViewById(R.id.eregister_password);
        num = findViewById(R.id.num);
        phone = findViewById(R.id.moblie);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        loginName = account.getText().toString();
        password = passWord.getText().toString();
        number = num.getText().toString();
        mobile = phone.getText().toString();
        switch (v.getId()) {
            case R.id.register:
                if (TextUtils.isEmpty(loginName)) {
                    ToastUtil.showToast(this, "姓名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.showToast(this, "密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(number)) {
                    ToastUtil.showToast(this, "学号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    ToastUtil.showToast(this, "手机号不能为空");
                    return;
                }
                toRigister(loginName, password, number, mobile);
                break;
        }
    }

    private void toRigister(final String loginName, String password, String number, String mobile) {
        HashMap<String, String> params = new HashMap<>();
        params.put("num", number);
        params.put("password", password);
        params.put("phone", mobile);
        params.put("name", loginName);
        mSubscription.add(MyWealthApi.getInstance().getMyWealthService().getRegister(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SuscriberX<RegisterBean>(RegisterActivity.this) {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        super.onNext(registerBean);
                        if (registerBean.isOk()) {
                            ToastUtil.showToast(RegisterActivity.this, "注册成功！");
                            SharedPreferencesUtil.setPrefString(RegisterActivity.this, "USER_ID", registerBean.getData().getId()+"");
                            SharedPreferencesUtil.setPrefString(RegisterActivity.this, "USER_NAME", registerBean.getData().getName());
                            finish();
                            finish();
                            Intent intent = new Intent(RegisterActivity.this, UpdateActivity.class);
                            startActivity(intent);
                        } else {
                            ToastUtil.showToast(RegisterActivity.this, registerBean.getMsg());
                        }
                    }
                }));
    }
}
