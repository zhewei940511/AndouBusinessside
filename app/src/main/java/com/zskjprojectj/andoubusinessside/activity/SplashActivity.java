package com.zskjprojectj.andoubusinessside.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;

public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setStatusBarColor(mActivity, Color.WHITE);
        BarUtils.setStatusBarLightMode(mActivity,true);
        contentView.postDelayed(() -> {
            if (LoginInfo.getToken().isEmpty()) {
                ActivityUtils.startActivity(LoginActivity.class);
            } else {
                ActivityUtils.startActivity(MainActivity.class);
            }
            finish();
        }, 1000);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }
}
