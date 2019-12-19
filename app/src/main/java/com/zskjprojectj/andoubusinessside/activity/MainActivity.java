package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.base.BaseActivity;
import com.zskjprojectj.andoubusinessside.base.BasePresenter;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.utils.UserUtil;

import static com.zskjprojectj.andoubusinessside.activity.UserCenterActivity.KEY_USER;

/**
 * 主页
 */
public class MainActivity extends BaseActivity {


    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initViews() {
        UserUtil.user = new User();
        findViewById(R.id.mallEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UserCenterActivity.class);
            UserUtil.user.setType(0);
            intent.putExtra(KEY_USER, UserUtil.user);
            startActivity(intent);
        });
        findViewById(R.id.hotelEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UserCenterActivity.class);
            UserUtil.user.setType(1);
            intent.putExtra(KEY_USER, UserUtil.user);
            startActivity(intent);
        });
        findViewById(R.id.restaurantEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UserCenterActivity.class);
            UserUtil.user.setType(2);
            intent.putExtra(KEY_USER, UserUtil.user);
            startActivity(intent);
        });
    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
