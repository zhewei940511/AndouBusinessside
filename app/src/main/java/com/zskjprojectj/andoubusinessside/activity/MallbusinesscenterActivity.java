package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.base.BaseActivity;
import com.zskjprojectj.andoubusinessside.base.BasePresenter;

/**
 * 商城商家中心
 */
public class MallbusinesscenterActivity extends BaseActivity {
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_mallbusinesscenter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        topView.setTitle("商城商家中心");
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
