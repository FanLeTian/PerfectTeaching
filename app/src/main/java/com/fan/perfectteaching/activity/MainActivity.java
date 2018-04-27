package com.fan.perfectteaching.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.fan.perfectteaching.R;
import com.fan.perfectteaching.activity.base.BaseActivity;
import com.fan.perfectteaching.fragment.AccountFragment;
import com.fan.perfectteaching.fragment.HelpFragment;
import com.fan.perfectteaching.fragment.HomeFragment;
import com.fan.perfectteaching.util.SharedPreferencesUtil;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    /**
     * 用于展示首页的Fragment
     */
    private HomeFragment homeFragment;


    /**
     * 用于展示帮助的Fragment
     */
    private HelpFragment helpFragment;

    /**
     * 用于展示账户的Fragment
     */
    private AccountFragment accountFragment;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    public int number = 0;

    private long exitTime;

    private BottomNavigationBar mBottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化布局元素
        initViews();
        fragmentManager = getSupportFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(number);
    }

    private void initViews() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
        mBottomNavigationBar.setFirstSelectedPosition(0)
                .setInActiveColor("#82858b");
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home_focus, "首页").setInactiveIconResource(R.drawable.home_unfocus).setActiveColor("#13B8D1"))
                .addItem(new BottomNavigationItem(R.drawable.list_focus, "列表").setInactiveIconResource(R.drawable.list_unfocus).setActiveColor("#13B8D1"))
                .addItem(new BottomNavigationItem(R.drawable.mine_focus, "我的").setInactiveIconResource(R.drawable.mine_unfocus).setActiveColor("#13B8D1"))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(this); //设置监听
    }


    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    public void setTabSelection(int index) {
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    // 如果homeFragment为空，则创建一个并添加到界面上
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.content, homeFragment);
                } else {
                    // 如果homeFragment不为空，则直接将它显示出来
                    transaction.show(homeFragment);
                }
                mBottomNavigationBar.selectTab(0);
                number = 0;
                break;
            case 1:
                if (accountFragment == null) {
                    // 如果accountFragment为空，则创建一个并添加到界面上
                    accountFragment = new AccountFragment();
                    transaction.add(R.id.content, accountFragment);
                } else {
                    // 如果accountFragment不为空，则直接将它显示出来
                    transaction.show(accountFragment);
                }
                mBottomNavigationBar.selectTab(1);
                number = 1;
                break;
            case 2:
                if (SharedPreferencesUtil.getPrefString(this, "USER_ID", "").equals("")) {
                    Intent intent2 = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent2, number);
                    break;
                }
                // 当点击了设置tab时，改变控件的图片和文字颜色
                if (helpFragment == null) {
                    // 如果helpFragment为空，则创建一个并添加到界面上
                    helpFragment = new HelpFragment();
                    transaction.add(R.id.content, helpFragment);
                } else {
                    // 如果helpFragment不为空，则直接将它显示出来
                    transaction.show(helpFragment);
                }
                mBottomNavigationBar.selectTab(2);
                number = 2;
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }

        if (accountFragment != null) {
            transaction.hide(accountFragment);
        }
        if (helpFragment != null) {
            transaction.hide(helpFragment);
        }
    }

    @Override
    public void onBackPressed() {
        if (exitTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        }
        exitTime = System.currentTimeMillis();
    }


    @Override
    public void onTabSelected(int position) {
        setTabSelection(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                setTabSelection(0);
                break;
            case 1:
                setTabSelection(1);
                break;
            case 2:
                setTabSelection(2);
                break;
        }
    }
}

