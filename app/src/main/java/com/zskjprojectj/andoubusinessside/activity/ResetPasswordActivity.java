package com.zskjprojectj.andoubusinessside.activity;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.utils.TimerUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class ResetPasswordActivity extends BaseActivity {

    @BindView(R.id.mobileEdt)
    EditText mobileEdt;
    @BindView(R.id.codeEdt)
    EditText codeEdt;
    @BindView(R.id.passwordEdt)
    EditText passwordEdt;
    @BindView(R.id.getCodeBtn)
    TextView getCodeBtn;
    @BindView(R.id.confirmBtn)
    View confirmBtn;
    TimerUtil timerUtil = new TimerUtil();

    @OnTextChanged(value = R.id.mobileEdt, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterMobileTextChanged(Editable mobileEdt) {
        setGetCodeEnableState(mobileEdt.toString());
        setConfirmBtnState();
    }

    @OnTextChanged(value = R.id.codeEdt, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterCodeTextChanged(Editable codeEdt) {
        setConfirmBtnState();
    }

    @OnTextChanged(value = R.id.passwordEdt, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPasswordTextChanged(Editable passwordEdt) {
        setConfirmBtnState();
    }

    private void setGetCodeEnableState(String mobileStr) {
        getCodeBtn.setEnabled(RegexUtils.isMobileSimple(mobileStr) && timerUtil.duration <= 0);
        if (timerUtil.duration <= 0) {
            getCodeBtn.setText("获取验证码");
        } else {
            getCodeBtn.setText(timerUtil.duration + " 秒");
        }
    }

    private void setConfirmBtnState() {
        confirmBtn.setEnabled(
                RegexUtils.isMobileSimple(mobileEdt.getText().toString()) &&
                        codeEdt.getText().toString().trim().length() >= 4 &&
                        !passwordEdt.getText().toString().isEmpty()
        );
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_reset_password;
    }

    @OnClick(R.id.backBtn)
    void onBackBtnClick() {
        finish();
    }

    @OnClick(R.id.getCodeBtn)
    void onGetCodeBtnClick() {
        HttpRxObservable.getObservable(mActivity, true, false,
                ApiUtils.getApiService().getCode(mobileEdt.getText().toString(), "0"),
                result -> {
                    ToastUtil.showToast(result.getMsg());
                    timerUtil.start(() -> setGetCodeEnableState(mobileEdt.getText().toString()), 60);
                })
                .subscribe();
    }

    @OnClick(R.id.confirmBtn)
    void onConfirmBtnClick() {
        KeyboardUtils.hideSoftInput(mActivity);
        HttpRxObservable.getObservable(mActivity, true, false,
                ApiUtils.getApiService().resetPassword(
                        mobileEdt.getText().toString(),
                        codeEdt.getText().toString(),
                        passwordEdt.getText().toString()),
                result -> {
                    ToastUtil.showToast(result.getMsg());
                    finish();
                })
                .subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerUtil.stop();
    }
}
