package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.UserUtil;

import butterknife.OnClick;

import static com.zskjprojectj.andoubusinessside.activity.UserCenterActivity.KEY_USER;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "查看内容");
        UserUtil.getInstance().user = new User();
        findViewById(R.id.mallEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UserCenterActivity.class);
            UserUtil.getInstance().user.setType(0);
            intent.putExtra(KEY_USER, UserUtil.getInstance().user);
            startActivity(intent);
        });
        findViewById(R.id.hotelEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UserCenterActivity.class);
            UserUtil.getInstance().user.setType(1);
            intent.putExtra(KEY_USER, UserUtil.getInstance().user);
            startActivity(intent);
        });
        findViewById(R.id.restaurantEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UserCenterActivity.class);
            UserUtil.getInstance().user.setType(2);
            intent.putExtra(KEY_USER, UserUtil.getInstance().user);
            startActivity(intent);
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.joinEntryBtn)
    void onJoinEntryBtnClick() {
        ActivityUtils.startActivity(JoinActivity.class);
    }
}
