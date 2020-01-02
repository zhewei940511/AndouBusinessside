package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.view.View;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.BaseObserver;
import com.zskjprojectj.andoubusinessside.http.BaseResult;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.ListUtil;

import java.util.List;

import butterknife.BindView;

public class JoinActivity extends BaseActivity {

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
                JoinInfoUploadActivity.start(JoinInfoUploadActivity.Type.MALL));
        hotelJoinEntryBtn.setOnClickListener(v ->
                JoinInfoUploadActivity.start(JoinInfoUploadActivity.Type.HOTEL));
        restaurantJoinEntryBtn.setOnClickListener(v ->
                JoinInfoUploadActivity.start(JoinInfoUploadActivity.Type.RESTAURANT));
        HttpRxObservable.getObservable(mActivity, ApiUtils.getApiService().merchantsInfo(
                LoginInfo.getUid(),
                LoginInfo.getToken()
        )).subscribe(new BaseObserver<List<User.Role>>(mActivity) {
            @Override
            public void onSuccess(BaseResult<List<User.Role>> result) {
                if (ListUtil.isEmpty(result.data))
                    return;
                for (User.Role role : result.data) {
                    if (role.merchant_type_id == User.Role.Type.MALL.typeInt) {
                        mallJoinEntryBtn.setVisibility(View.GONE);
                    }
                    if (role.merchant_type_id == User.Role.Type.HOTEL.typeInt) {
                        hotelJoinEntryBtn.setVisibility(View.GONE);
                    }
                    if (role.merchant_type_id == User.Role.Type.RESTAURANT.typeInt) {
                        restaurantJoinEntryBtn.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_join;
    }
}
