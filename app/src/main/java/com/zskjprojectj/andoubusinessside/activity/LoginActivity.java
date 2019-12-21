package com.zskjprojectj.andoubusinessside.activity;

import android.widget.EditText;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.BaseObserver;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;

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

    @OnClick(R.id.loginBtn)
    void onLoginBtnClick() {
        KeyboardUtils.hideSoftInput(mActivity);
        if (mobileEdt.getText().toString().isEmpty() || passwordEdt.getText().toString().isEmpty()) {
            ToastUtils.showShort("手机号或密码不能为空!");
            return;
        }
        HttpRxObservable.getObservable(ApiUtils.getApiService().test())
                .subscribe(new BaseObserver<Object>(mActivity) {
                    @Override
                    public void onHandleSuccess(Object o) {
                        ActivityUtils.startActivity(MainActivity.class);
                        finish();
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onErrorA() {
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
