package com.fan.perfectteaching.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fan.perfectteaching.R;
import com.fan.perfectteaching.activity.base.BackBaseActivity;
import com.fan.perfectteaching.beans.StatusBean;
import com.fan.perfectteaching.netutil.MyWealthApi;
import com.fan.perfectteaching.netutil.SuscriberX;
import com.fan.perfectteaching.util.SharedPreferencesUtil;
import com.fan.perfectteaching.util.ToastUtil;

import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdateActivity extends BackBaseActivity {


    private EditText sexE;
    private EditText e_mail;
    private EditText birthE;
    private EditText classE;
    private Button submit;

    private String sex;
    private String email;
    private String birth;
    private String userId;
    private String cclass;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_personal_info);
        setTitle("完善个人信息");
        initView();
        userId = SharedPreferencesUtil.getPrefString(UpdateActivity.this, "USER_ID", "");
    }

    private void initView() {
        sexE = findViewById(R.id.sex_et);
        e_mail =  findViewById(R.id.e_mail);
        birthE =  findViewById(R.id.remark);
        classE = findViewById(R.id.classes);
        submit = findViewById(R.id.bt_enter_info);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cclass =classE.getText().toString();
                sex = sexE.getText().toString();
                email = e_mail.getText().toString();
                birth = birthE.getText().toString();
                setPersonInfo();
            }
        });
    }

    private void setPersonInfo() {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", userId);
        params.put("sex", sex);
        params.put("cclass", cclass);
        params.put("email", email);
        params.put("birth", birth);
        mSubscription.add(MyWealthApi.getInstance().getMyWealthService().setPersonInfo(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SuscriberX<StatusBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StatusBean statusBean) {
                        if (statusBean.isOk()) {
                            finish();
                        } else {
                            ToastUtil.showToast(UpdateActivity.this, statusBean.getMsg());
                        }
                    }
                }));
    }
}
