package com.fan.perfectteaching.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.fan.perfectteaching.R;
import com.fan.perfectteaching.activity.base.BackBaseActivity;
import com.fan.perfectteaching.util.SharedPreferencesUtil;

public class AccountMannagerActivity extends BackBaseActivity {
    private TextView name;
    private View completInfo;
    private View resetPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_mamager_laout);
        setTitle("账户管理");
        initView();
    }

    private void initView() {
        name = (TextView) findViewById(R.id.name);
        completInfo = findViewById(R.id.to_prove);
        name.setText(SharedPreferencesUtil.getPrefString(this, "USER_NAME", ""));
        completInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountMannagerActivity.this, UpdateActivity.class);
                startActivity(intent);
            }
        });
    }
}
