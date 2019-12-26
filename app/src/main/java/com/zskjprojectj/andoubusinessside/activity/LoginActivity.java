package com.zskjprojectj.andoubusinessside.activity;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.BaseObserver;
import com.zskjprojectj.andoubusinessside.http.BaseResult;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.Config;
import com.zskjprojectj.andoubusinessside.model.User;
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
        ActivityUtils.startActivity(JoinActivity.class);
    }

    @OnTextChanged(value = R.id.passwordEdt, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPasswordTextChanged(Editable passwordEdt) {
        setLoginBtnState();
    }

    @OnTextChanged(value = R.id.mobileEdt, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterMobileTextChanged(Editable mobileEdt) {
        setLoginBtnState();
    }

    private void setLoginBtnState() {
        loginBtn.setEnabled(RegexUtils.isMobileSimple(mobileEdt.getText().toString()) &&
                !passwordEdt.getText().toString().isEmpty());
    }

    @OnClick(R.id.loginBtn)
    void onLoginBtnClick() {
        KeyboardUtils.hideSoftInput(mActivity);
        HttpRxObservable.getObservable(ApiUtils.getApiService().login(
                mobileEdt.getText().toString(),
                passwordEdt.getText().toString()))
                .subscribe(new BaseObserver<User>(mActivity) {

                    @Override
                    public void onSuccess(BaseResult<User> result) {
                        Config.saveUidAndToken(result.data.id, result.data.token);
                        ActivityUtils.startActivity(MainActivity.class);
                        ToastUtil.showToast(result.getMsg());
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
