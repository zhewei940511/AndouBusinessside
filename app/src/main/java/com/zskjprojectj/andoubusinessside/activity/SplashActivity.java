package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.Config;

public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView.postDelayed(() -> {
            if (Config.getToken().isEmpty()) {
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
