package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.utils.UserUtil;

import static com.zskjprojectj.andoubusinessside.activity.UserCenterActivity.KEY_USER;

/**
 * 主页
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    protected void doCreate(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
