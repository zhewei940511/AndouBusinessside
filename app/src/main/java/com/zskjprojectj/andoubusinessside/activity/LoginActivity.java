package com.zskjprojectj.andoubusinessside.activity;

import android.widget.EditText;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.BaseObserver;
import com.zskjprojectj.andoubusinessside.http.BaseResult;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.User;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.mobileEdt)
    EditText mobileEdt;

    @BindView(R.id.passwordEdt)
    EditText passwordEdt;

    @OnClick(R.id.findPasswordBtn)
    void onFindPasswordBtnClick() {
        ActivityUtils.startActivity(FindPasswordActivity.class);
    }

    @OnClick(R.id.joinEntryBtn)
    void onJoinEntryBtnClick() {
        ActivityUtils.startActivity(JoinActivity.class);
    }

    @OnClick(R.id.loginBtn)
    void onLoginBtnClick() {
        KeyboardUtils.hideSoftInput(mActivity);
        String phoneStr = mobileEdt.getText().toString().trim();
        String passwordStr = passwordEdt.getText().toString().trim();
        if (phoneStr.isEmpty() || passwordStr.isEmpty()) {
            ToastUtils.showShort("手机号或密码不能为空!");
            return;
        }
        HttpRxObservable.getObservable(ApiUtils.getApiService().login(phoneStr, passwordStr))
                .subscribe(new BaseObserver<User>(mActivity) {

                    @Override
                    public void onSuccess(BaseResult<User> result) {
                        ActivityUtils.startActivity(MainActivity.class);
                        finish();
                    }

                    @Override
                    public void onFailure(String msg) {
                        ActivityUtils.startActivity(MainActivity.class);
                        finish();
                    }
                });
    }

    @OnClick(R.id.backBtn)
    void onBackBtnClick() {
        finish();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }
}
