package com.zskjprojectj.andoubusinessside.activity;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;

import butterknife.OnClick;

public class FindPasswordActivity extends BaseActivity {

    @OnClick(R.id.backBtn)
    void onBackBtnClick() {
        finish();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_find_password;
    }
}
