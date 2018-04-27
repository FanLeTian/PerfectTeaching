package com.fan.perfectteaching.activity.base;

import android.view.MenuItem;
import android.view.View;

import com.fan.perfectteaching.R;


public abstract class BackBaseActivity extends BaseActivity {

    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.fh);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

     @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideToolbar() {
        if (toolbar != null) {
            toolbar.setVisibility(View.GONE);
        }
    }

    public void showToolbar() {
        if (toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
        }
    }
}