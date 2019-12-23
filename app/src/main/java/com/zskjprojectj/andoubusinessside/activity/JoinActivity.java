package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;

import butterknife.OnClick;

public class JoinActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "商家入驻");
    }

    @OnClick(R.id.hotelJoinEntryBtn)
    void onHotelJoinEntryBtnClick() {
        JoinInfoUploadActivity.start(JoinInfoUploadActivity.Type.HOTEL);
    }

    @OnClick(R.id.mallJoinEntryBtn)
    void onMallJoinEntryBtnClick() {
        JoinInfoUploadActivity.start(JoinInfoUploadActivity.Type.MALL);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_join;
    }
}
