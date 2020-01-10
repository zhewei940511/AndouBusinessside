package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.mobileEdt)
    EditText mobileEdt;
    @BindView(R.id.passwordEdt)
    EditText passwordEdt;
    @BindView(R.id.loginBtn)
    View loginBtn;

    @OnClick(R.id.resetPasswordBtn)
    void onResetPasswordBtnClick() {
        ActivityUtils.startActivity(ResetPasswordActivity.class);
    }

    @OnClick(R.id.joinEntryBtn)
    void onJoinEntryBtnClick() {
        ToastUtil.showToast("请先登录,然后选择要入驻的商家类型!");
    }

    @OnTextChanged(value = R.id.passwordEdt, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPasswordTextChanged() {
        setLoginBtnState();
    }

    @OnTextChanged(value = R.id.mobileEdt, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterMobileTextChanged() {
        setLoginBtnState();
    }

    private void setLoginBtnState() {
        loginBtn.setEnabled(
                //TODO 为了测试取消手机号码正确性验证
//                RegexUtils.isMobileSimple(mobileEdt.getText().toString()) &&
                !passwordEdt.getText().toString().isEmpty());
    }

    @OnClick(R.id.loginBtn)
    void onLoginBtnClick() {
        KeyboardUtils.hideSoftInput(mActivity);
        HttpRxObservable.getObservable(mActivity, true, false,
                ApiUtils.getApiService().login(
                        mobileEdt.getText().toString(),
                        passwordEdt.getText().toString()),
                result -> {
                    LoginInfo.saveUidAndToken(result.data.id, result.data.token);
                    ActivityUtils.startActivity(MainActivity.class);
                    ToastUtil.showToast(result.getMsg());
                    finish();
                })
                .subscribe();
    }

    @OnClick(R.id.backBtn)
    void onBackBtnClick() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setStatusBarLightMode(mActivity, true);
        BarUtils.setStatusBarColor(mActivity, Color.TRANSPARENT);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }


    public static void start(BaseActivity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ActivityUtils.startActivity(intent);
    }
}
