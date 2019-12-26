package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.view.View;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

public class CashOutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "余额提现");
        View progress = findViewById(R.id.progressBar);
        findViewById(R.id.confirmBtn).setOnClickListener(view -> {
            progress.setVisibility(View.VISIBLE);
            progress.postDelayed(() -> {
                ToastUtil.showToast("提现成功!");
                finish();
            }, 1000);
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_cash_out;
    }
}
