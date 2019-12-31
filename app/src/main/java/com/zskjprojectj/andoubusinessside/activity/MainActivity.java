package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
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
        ActionBarUtil.setBackEnable(mActivity, false);
        UserUtil.getInstance().user = new User();
        findViewById(R.id.mallEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UserCenterActivity.class);
            UserUtil.getInstance().user.types.add(User.Type.MALL);
            intent.putExtra(KEY_USER, UserUtil.getInstance().user);
            startActivity(intent);
        });
        findViewById(R.id.hotelEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UserCenterActivity.class);
            UserUtil.getInstance().user.types.add(User.Type.HOTEL);
            intent.putExtra(KEY_USER, UserUtil.getInstance().user);
            startActivity(intent);
        });
        findViewById(R.id.restaurantEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UserCenterActivity.class);
            UserUtil.getInstance().user.types.add(User.Type.RESTUARANT);
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

    @Override
    public void onBackPressed() {
        if (contentView.getTag() == null) {
            ToastUtils.showShort("再按一次退出");
            contentView.setTag(new Object());
            contentView.postDelayed(() -> contentView.setTag(null), 2000);
            return;
        }
        super.onBackPressed();
    }
}
