package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.utils.UserUtil;

public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView.postDelayed(() -> {
            if (UserUtil.getInstance().isLogin()) {
                ActivityUtils.startActivity(MainActivity.class);
            } else {
                ActivityUtils.startActivity(LoginActivity.class);
            }
            finish();
        }, 1000);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }
}
