package com.fan.perfectteaching.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fan.perfectteaching.R;
import com.fan.perfectteaching.activity.AccountMannagerActivity;
import com.fan.perfectteaching.activity.MainActivity;
import com.fan.perfectteaching.activity.OrderActivity;
import com.fan.perfectteaching.fragment.base.BaseFragment;
import com.fan.perfectteaching.util.SharedPreferencesUtil;
import com.fan.perfectteaching.util.ToastUtil;

public class HelpFragment extends BaseFragment implements View.OnClickListener{


    private TextView accountName;
    private View noCommit;
    private View noComplet;
    private View accountManager;
    private Button exit;


    @Override
    protected int getLayoutRes() {
        return R.layout.help_layout;
    }


    @Override
    protected void initData() {
        super.initData();
        accountName = (TextView) mContentView.findViewById(R.id.account_name);
        accountName.setText(SharedPreferencesUtil.getPrefString(getContext(), "USER_NAME", ""));
        noCommit = mContentView.findViewById(R.id.no_submit_oder);
        noComplet = mContentView.findViewById(R.id.no_complet_oder);
        accountManager = mContentView.findViewById(R.id.account_management);
        exit = (Button) mContentView.findViewById(R.id.exit);
        noCommit.setOnClickListener(this);
        noComplet.setOnClickListener(this);
        accountManager.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        accountName.setText(SharedPreferencesUtil.getPrefString(getContext(), "USER_NAME", ""));
    }

    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent(getContext(), OrderActivity.class);
        switch (v.getId()) {
            case R.id.no_submit_oder:
                intent1.putExtra("type", "0");
                intent1.putExtra("title", "待完成课程/实验");
                getContext().startActivity(intent1);
                break;
            case R.id.no_complet_oder:
                intent1.putExtra("type", "1");
                intent1.putExtra("title", "已完成课程/实验");
                getContext().startActivity(intent1);
                break;
            case R.id.account_management:
                Intent intent = new Intent(getContext(), AccountMannagerActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.exit:
                SharedPreferencesUtil.setPrefString(getContext(), "USER_NAME", "");
                SharedPreferencesUtil.setPrefString(getContext(), "USER_ID", "");
                ToastUtil.showToast(getContext(), "您已安全退出");
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setTabSelection(0);
                break;
        }

    }
}
