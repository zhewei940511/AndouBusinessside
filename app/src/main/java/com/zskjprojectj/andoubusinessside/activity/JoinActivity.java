package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.ListUtil;

import java.util.List;

import butterknife.BindView;

public class JoinActivity extends BaseActivity {

    public static final int REQUEST_CODE_JOIN = 666;

    @BindView(R.id.mallJoinEntryBtn)
    View mallJoinEntryBtn;
    @BindView(R.id.hotelJoinEntryBtn)
    View hotelJoinEntryBtn;
    @BindView(R.id.restaurantJoinEntryBtn)
    View restaurantJoinEntryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "商家入驻");
        mallJoinEntryBtn.setOnClickListener(v ->
                JoinInfoUploadActivity.start(mActivity, User.Role.Type.MALL, REQUEST_CODE_JOIN));
        hotelJoinEntryBtn.setOnClickListener(v ->
                JoinInfoUploadActivity.start(mActivity, User.Role.Type.HOTEL, REQUEST_CODE_JOIN));
        restaurantJoinEntryBtn.setOnClickListener(v ->
                JoinInfoUploadActivity.start(mActivity, User.Role.Type.RESTAURANT, REQUEST_CODE_JOIN));
        HttpRxObservable.getObservable(mActivity, true, false,
                ApiUtils.getApiService().merchantsInfo(LoginInfo.getUid())
                , result -> {
                    if (!isRole(result.data, User.Role.Type.MALL)) {
                        mallJoinEntryBtn.setVisibility(View.VISIBLE);
                    }
                    if (!isRole(result.data, User.Role.Type.HOTEL)) {
                        hotelJoinEntryBtn.setVisibility(View.VISIBLE);
                    }
                    if (!isRole(result.data, User.Role.Type.RESTAURANT)) {
                        restaurantJoinEntryBtn.setVisibility(View.VISIBLE);
                    }
                })
                .subscribe();
    }

    private boolean isRole(List<User.Role> roles, User.Role.Type type) {
        if (ListUtil.isEmpty(roles)) return false;
        for (User.Role role : roles) {
            if (role.merchant_type_id == type.typeInt) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_JOIN && resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_join;
    }
}
