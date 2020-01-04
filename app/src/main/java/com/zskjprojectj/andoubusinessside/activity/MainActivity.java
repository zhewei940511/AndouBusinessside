package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.ListUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zskjprojectj.andoubusinessside.activity.JoinActivity.REQUEST_CODE_JOIN;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mallEntryBtn)
    View mallEntryBtn;
    @BindView(R.id.hotelEntryBtn)
    View hotelEntryBtn;
    @BindView(R.id.restaurantEntryBtn)
    View restaurantEntryBtn;
    @BindView(R.id.noMerchantsContainer)
    View noMerchantsContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "查看内容");
        ActionBarUtil.setBackEnable(mActivity, false);
        loadMerchantsInfo();
    }

    private void loadMerchantsInfo() {
        HttpRxObservable.getObservable(mActivity, true, true,
                ApiUtils.getApiService().merchantsInfo(LoginInfo.getUid()),
                result -> {
                    if (!ListUtil.isEmpty(result.data)) {
                        noMerchantsContainer.setVisibility(View.GONE);
                        for (User.Role role : result.data) {
                            View.OnClickListener onUserCenterEntryBtnClickListener =
                                    view -> {
                                        LoginInfo.saveMerchantTypeAndId(role.id, role.merchant_type_id);
                                        UserCenterActivity.start(role);
                                    };
                            if (role.merchant_type_id == User.Role.Type.MALL.typeInt) {
                                mallEntryBtn.setVisibility(View.VISIBLE);
                                mallEntryBtn.setOnClickListener(onUserCenterEntryBtnClickListener);
                            }
                            if (role.merchant_type_id == User.Role.Type.HOTEL.typeInt) {
                                hotelEntryBtn.setVisibility(View.VISIBLE);
                                hotelEntryBtn.setOnClickListener(onUserCenterEntryBtnClickListener);
                            }
                            if (role.merchant_type_id == User.Role.Type.RESTAURANT.typeInt) {
                                restaurantEntryBtn.setVisibility(View.VISIBLE);
                                restaurantEntryBtn.setOnClickListener(onUserCenterEntryBtnClickListener);
                            }
                        }
                    } else {
                        findViewById(R.id.noMerchantsContainer).setVisibility(View.VISIBLE);
                    }
                })
                .subscribe();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.joinEntryBtn, R.id.joinEntryBtn2})
    void onJoinEntryBtnClick() {
        ActivityUtils.startActivityForResult(mActivity, JoinActivity.class, REQUEST_CODE_JOIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_JOIN && resultCode == Activity.RESULT_OK) {
            loadMerchantsInfo();
        }
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
