package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "设置");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }
}
