package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;

public class JoinInfoUploadActivity extends BaseActivity {

    public static final String KEY_JOIN_TYPE = "KEY_JOIN_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int type = getIntent().getIntExtra(KEY_JOIN_TYPE, Type.HOTEL.ordinal());
        if (type == Type.HOTEL.ordinal()) {
            ActionBarUtil.setTitle(mActivity, "酒店商家入驻");
        } else if (type == Type.MALL.ordinal()) {
            ActionBarUtil.setTitle(mActivity, "商城商家入驻");
        } else if (type == Type.RESTAURANT.ordinal()) {
            ActionBarUtil.setTitle(mActivity, "饭店商家入驻");
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_join_info_upload;
    }

    public static void start(Type type) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_JOIN_TYPE, type.ordinal());
        ActivityUtils.startActivity(bundle, JoinInfoUploadActivity.class);
    }

    public enum Type {
        HOTEL, MALL, RESTAURANT
    }
}
