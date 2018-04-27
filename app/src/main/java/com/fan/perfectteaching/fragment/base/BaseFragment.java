package com.fan.perfectteaching.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import rx.subscriptions.CompositeSubscription;



public abstract class BaseFragment extends Fragment {
    protected CompositeSubscription mSubscription = new CompositeSubscription();
    protected View mContentView;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            initView(inflater, container, savedInstanceState);
            initData();
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    protected void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutRes(), container, false);
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected void initData() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }

    protected void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}
