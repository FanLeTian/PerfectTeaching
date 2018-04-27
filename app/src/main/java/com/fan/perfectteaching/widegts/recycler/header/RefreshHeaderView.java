package com.fan.perfectteaching.widegts.recycler.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.aspsine.irecyclerview.RefreshTrigger;
import com.fan.perfectteaching.R;


/**
 * 下拉刷新顶部视图
 *
 */
public class RefreshHeaderView extends FrameLayout implements RefreshTrigger {

    private TextView tvRefresh;

    private ProgressBar ivAnimation;
    private AnimationDrawable animationDrawable;

    private int mHeight;

    public RefreshHeaderView(Context context) {
        this(context, null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_irecyclerview_refresh_header_view, this);

        tvRefresh = (TextView) findViewById(R.id.tvRefresh);

        ivAnimation = (ProgressBar) findViewById(R.id.loadingView);

        //animationDrawable = (AnimationDrawable) ivAnimation.getDrawable();
    }

    /**
     * 开始下拉
     * @param automatic
     * @param headerHeight
     * @param finalHeight
     */
    @Override
    public void onStart(boolean automatic, int headerHeight, int finalHeight) {
        mHeight = headerHeight;
    }

    /**
     * 开始移动计算
     *
     * @param isComplete
     * @param automatic
     * @param moved
     */
    @Override
    public void onMove(boolean isComplete, boolean automatic, int moved) {
        // 判断高度 切换文字 下拉刷新 松开刷新
        if (!isComplete) {
            if (moved <= mHeight) {
                tvRefresh.setText("下拉刷新");
            } else {
                tvRefresh.setText("松开刷新");
            }
        }
    }

    /**
     * 正在刷新中
     */
    @Override
    public void onRefresh() {
        // 正在加载中
      //  animationDrawable.start();
        tvRefresh.setText("正在加载");
    }

    @Override
    public void onRelease() {

    }

    /**
     * 刷新结束
     */
    @Override
    public void onComplete() {
        // 刷新成功显示
      //  animationDrawable.stop();
        tvRefresh.setText("刷新完成");
    }

    /**
     * 回复初始状态
     */
    @Override
    public void onReset() {
        // 恢复到未刷新状态
        // tvRefresh.setText("下拉刷新");
    }
}
