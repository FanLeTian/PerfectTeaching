package com.fan.perfectteaching.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fan.perfectteaching.R;
import com.fan.perfectteaching.activity.base.BackBaseActivity;
import com.fan.perfectteaching.beans.LabDetailBean;
import com.fan.perfectteaching.beans.StatusBean;
import com.fan.perfectteaching.netutil.MyWealthApi;
import com.fan.perfectteaching.netutil.SuscriberX;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderDetailActivity extends BackBaseActivity {

    private String orderid;
    private String type;

    private TextView labName;
    private TextView labContent;
    private TextView labType;
    private TextView teacherTitle;
    private TextView teacherName;
    private TextView teacherEmail;
    private View labTeacher;
    private Button reservation;
    private Button toHomeWork;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oder_detail_layout);
        setTitle(getIntent().getStringExtra("title"));
        orderid = getIntent().getStringExtra("oderId");
        type = getIntent().getStringExtra("type");
        initView();
        initData();
    }



    private void initData() {
        int id = Integer.parseInt(orderid);
        mSubscription.add(MyWealthApi.getInstance().getMyWealthService().getLabById(id)
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

    private void initView() {
        labName = findViewById(R.id.lib_name);
        labContent = findViewById(R.id.lib_content);
        labType = findViewById(R.id.lab_type);
        teacherName = findViewById(R.id.lab_teacher_name);
        teacherTitle = findViewById(R.id.lab_teacher_title);
        teacherEmail = findViewById(R.id.lab_teacher_email);
        labTeacher = findViewById(R.id.lab_teacher);
        reservation = findViewById(R.id.to_reservation);
        toHomeWork = findViewById(R.id.home_work);
        if (type.equals("0")) {
            reservation.setEnabled(true);
            reservation.setText("待完成");
            reservation.setBackgroundColor(ContextCompat.getColor(this, R.color.aaaaa));
        } else if (type.equals("1")) {
            reservation.setEnabled(false);
            reservation.setText("已完成");
            reservation.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        }
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        toHomeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitLab();
            }
        });
    }

    private void commitLab() {
        HashMap<String, String> params = new HashMap<>();
        params.put("orderid", orderid);
        mSubscription.add(MyWealthApi.getInstance().getMyWealthService()
                .commit(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SuscriberX<StatusBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StatusBean statusBean) {

                    }
                }));
    }

}
