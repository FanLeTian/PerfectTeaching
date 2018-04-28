package com.fan.perfectteaching.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.fan.perfectteaching.R;
import com.fan.perfectteaching.activity.base.BackBaseActivity;
import com.fan.perfectteaching.adapter.OderAdapter;
import com.fan.perfectteaching.beans.OrderBean;
import com.fan.perfectteaching.netutil.MyWealthApi;
import com.fan.perfectteaching.netutil.SuscriberX;
import com.fan.perfectteaching.util.SharedPreferencesUtil;
import com.fan.perfectteaching.widegts.recycler.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderActivity extends BackBaseActivity implements OnItemClickListener<OrderBean.DataBean>, OnRefreshListener {


    private IRecyclerView mRecyclerView;
    private OderAdapter mOderAdapter;
    private List<OrderBean.DataBean> dataList = new ArrayList<>();
    private String userId;
    private String type;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);
        String title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");
        userId = SharedPreferencesUtil.getPrefString(this, "USER_ID", "");
        setTitle(title);
        initView();
        if (type.equals("0")) {

        } else if (type.equals("1")) {

        }
    }

    private void initView() {
        mRecyclerView = (IRecyclerView) findViewById(R.id.oder_recycler);
        mOderAdapter = new OderAdapter(this);
        mOderAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setIAdapter(mOderAdapter);
        mRecyclerView.setOnRefreshListener(this);
        mRecyclerView.setLoadMoreEnabled(false);
        getOderList();
    }


    @Override
    public void onItemClick(int position, OrderBean.DataBean reslutBean, View v) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("title", reslutBean.getName());
        intent.putExtra("oderId", reslutBean.getId()+"");
        intent.putExtra("type",type);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        dataList.clear();
        getOderList();
    }


    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    private void getOderList() {
        mSubscription.add(MyWealthApi.getInstance().getMyWealthService().getAllOder(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SuscriberX<OrderBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(OrderBean orderBean) {
                        if (orderBean.isOk()) {
                            List<OrderBean.DataBean> list = new ArrayList<>();
                            dataList = orderBean.getData();
                            if (type.equals("0")) {
                                for (OrderBean.DataBean dataBean : dataList) {
                                    if (dataBean.getStatus() == 0) {
                                        list.add(dataBean);
                                    }
                                }
                            } else if (type.equals("1")) {
                                dataList = orderBean.getData();
                                for (OrderBean.DataBean dataBean : dataList) {
                                    if (dataBean.getStatus() == 1) {
                                        list.add(dataBean);
                                    }
                                }
                            }
                            mOderAdapter.refreshItems(list);
                            mRecyclerView.setRefreshing(false);
                        }
                    }
                }));
    }
}
