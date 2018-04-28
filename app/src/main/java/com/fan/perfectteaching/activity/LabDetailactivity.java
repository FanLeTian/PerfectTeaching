package com.fan.perfectteaching.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fan.perfectteaching.R;
import com.fan.perfectteaching.activity.base.BackBaseActivity;
import com.fan.perfectteaching.beans.HomeBean;
import com.fan.perfectteaching.beans.LabDetailBean;
import com.fan.perfectteaching.beans.SelectBean;
import com.fan.perfectteaching.netutil.MyWealthApi;
import com.fan.perfectteaching.netutil.SuscriberX;
import com.fan.perfectteaching.util.SharedPreferencesUtil;
import com.fan.perfectteaching.util.ToastUtil;

import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LabDetailactivity extends BackBaseActivity implements View.OnClickListener{


    private TextView labName;
    private TextView labContent;
    private TextView labType;
    private TextView teacherTitle;
    private TextView teacherName;
    private TextView teacherEmail;
    private View labTeacher;
    private Button reservation;
    private Integer labId;
    private String title;

    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab_detaill_layout);
        title = getIntent().getStringExtra("title");
        String id = getIntent().getStringExtra("labId");
        labId = Integer.parseInt(id);
        setTitle(title);
        userId = SharedPreferencesUtil.getPrefString(LabDetailactivity.this, "USER_ID", "");
        initView();
        getProductRequest();
    }

    private void initView() {
        labName = findViewById(R.id.lib_name);
        labContent = findViewById(R.id.lib_content);
        labType = findViewById(R.id.lab_type);
        teacherName = findViewById(R.id.lab_teacher_name);
        teacherTitle = findViewById(R.id.lab_teacher_title);
        teacherEmail = findViewById(R.id.lab_teacher_email);
        labTeacher = findViewById(R.id.lab_teacher);
        reservation = findViewById(R.id.to_reservation);
        reservation.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        userId = SharedPreferencesUtil.getPrefString(LabDetailactivity.this, "USER_ID", "");
    }

    private void getProductRequest() {
        mSubscription.add(MyWealthApi.getInstance().getMyWealthService().getLabById(labId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SuscriberX<LabDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LabDetailBean labDetailBean) {
                        labName.setText("  "+labDetailBean.getData().getName());
                        labContent.setText("  "+labDetailBean.getData().getContent());
                        labType.setText("  "+labDetailBean.getData().getType());
                        teacherEmail.setText("  "+labDetailBean.getData().getTeacher().getEmail());
                        teacherName.setText("  "+labDetailBean.getData().getTeacher().getName());
                        teacherTitle.setText("  "+labDetailBean.getData().getTeacher().getTitle());
                    }
                }));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_reservation:
                if (!userId.equals("")) {
                    getSelectReslut();
                } else {
                    ToastUtil.showToast(LabDetailactivity.this,"未登录，请登录后再进行选择");
                    Intent intent = new Intent(LabDetailactivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private void getSelectReslut() {
        MyWealthApi.getInstance().getMyWealthService().selectLab(userId, labId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SuscriberX<SelectBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SelectBean selectBean) {
                        if (selectBean.isOk()) {
                            ToastUtil.showToast(LabDetailactivity.this, "选择成功");
                        } else {
                            ToastUtil.showToast(LabDetailactivity.this, selectBean.getMsg());
                        }
                    }
                });
    }
}
