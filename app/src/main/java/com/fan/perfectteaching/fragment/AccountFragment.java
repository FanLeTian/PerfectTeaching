package com.fan.perfectteaching.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.fan.perfectteaching.R;
import com.fan.perfectteaching.activity.LabDetailactivity;
import com.fan.perfectteaching.activity.LoginActivity;
import com.fan.perfectteaching.adapter.HomeAdapter;
import com.fan.perfectteaching.adapter.ListAdapter;
import com.fan.perfectteaching.beans.HomeBean;
import com.fan.perfectteaching.fragment.base.BaseFragment;
import com.fan.perfectteaching.netutil.MyWealthApi;
import com.fan.perfectteaching.netutil.SuscriberX;
import com.fan.perfectteaching.util.SharedPreferencesUtil;
import com.fan.perfectteaching.util.ToastUtil;
import com.fan.perfectteaching.widegts.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AccountFragment extends BaseFragment implements OnItemClickListener<HomeBean.DataBean>,OnRefreshListener {

    private IRecyclerView homeRecycler;
    private ListAdapter homeAdapter;
    private View noDataView;
    private List<HomeBean.DataBean> listData = new ArrayList<>();


    public static AccountFragment newInstance() {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.second_layout;
    }


    @Override
    public void onRefresh() {
        listData.clear();
        getListData();
    }

    private void getListData() {
        mSubscription.add(MyWealthApi.getInstance().getMyWealthService().getLabs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SuscriberX<HomeBean>(getContext()){
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        homeRecycler.setRefreshing(false);
                        super.onError(e);
                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        super.onNext(homeBean);
                        if (homeBean.isOk()) {
                            ToastUtil.showToast(getContext(), homeBean.getMsg());
                            listData = homeBean.getData();
                            homeAdapter.refreshItems(listData);
                        } else {
                            ToastUtil.showToast(getContext(), homeBean.getMsg());

                        }
                        homeRecycler.setRefreshing(false);
                    }
                }));
    }

    @Override
    protected void initData() {
        super.initData();
        if (SharedPreferencesUtil.getPrefString(getContext(), "USER_ID", "").equals("")) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            getContext().startActivity(intent);
        }
        homeAdapter = new ListAdapter(mContext);
        homeRecycler = mContentView.findViewById(R.id.list_recycler);
        homeAdapter.setOnItemClickListener(this);
        homeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        homeRecycler.setIAdapter(homeAdapter);
        homeRecycler.setOnRefreshListener(this);
        homeRecycler.setLoadMoreEnabled(false);
        getListData();
    }


    @Override
    public void onItemClick(int position, HomeBean.DataBean dataBean, View v) {
        Intent intent = new Intent(getContext(), LabDetailactivity.class);
        intent.putExtra("title", dataBean.getName());
        intent.putExtra("labId", dataBean.getId()+"");
        getActivity().startActivity(intent);
    }
}
