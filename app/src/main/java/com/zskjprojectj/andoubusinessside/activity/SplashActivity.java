package com.zskjprojectj.andoubusinessside.activity;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.widget.ImageView;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void doCreate(Bundle savedInstanceState) {
        ImageView mIvLoading = findViewById(R.id.loading_process_dialog_progressBar);
        ((Animatable)mIvLoading.getDrawable()).start();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }
}
