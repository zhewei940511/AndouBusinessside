package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.BaseObserver;
import com.zskjprojectj.andoubusinessside.http.BaseResult;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.model.UserT;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.ListUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;
import com.zskjprojectj.andoubusinessside.utils.UserUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
        HttpRxObservable.getObservable(mActivity, ApiUtils.getApiService().merchantsInfo(
                LoginInfo.getUid(),
                LoginInfo.getToken()
        )).subscribe(new BaseObserver<List<User.Role>>(mActivity) {
            @Override
            public void onSuccess(BaseResult<List<User.Role>> result) {
                if (ListUtil.isEmpty(result.data)) {
                    ToastUtil.showToast("您还不是商家,请选择要入驻的商家类型");
                    onJoinEntryBtnClick();
                } else {
                    noMerchantsContainer.setVisibility(View.GONE);
                }
            }
        });
        UserUtil.getInstance().userT = new UserT();
        UserUtil.getInstance().userT.types.add(UserT.Type.MALL);
        UserUtil.getInstance().userT.types.add(UserT.Type.HOTEL);
//        UserUtil.getInstance().userT.types.add(UserT.Type.RESTAURANT);

        if (UserUtil.getInstance().userT.types.contains(UserT.Type.MALL)) {
            mallEntryBtn.setVisibility(View.VISIBLE);
            mallEntryBtn.setOnClickListener(view -> {
                UserUtil.getInstance().userT.currentType = UserT.Type.MALL;
                ActivityUtils.startActivity(UserCenterActivity.class);
            });
        }
        if (UserUtil.getInstance().userT.types.contains(UserT.Type.HOTEL)) {
            hotelEntryBtn.setVisibility(View.VISIBLE);
            hotelEntryBtn.setOnClickListener(view -> {
                UserUtil.getInstance().userT.currentType = UserT.Type.HOTEL;
                ActivityUtils.startActivity(UserCenterActivity.class);
            });
        }
        if (UserUtil.getInstance().userT.types.contains(UserT.Type.RESTAURANT)) {
            restaurantEntryBtn.setVisibility(View.VISIBLE);
            restaurantEntryBtn.setOnClickListener(view -> {
                UserUtil.getInstance().userT.currentType = UserT.Type.RESTAURANT;
                ActivityUtils.startActivity(UserCenterActivity.class);
            });
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.joinEntryBtn, R.id.joinEntryBtn2})
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
