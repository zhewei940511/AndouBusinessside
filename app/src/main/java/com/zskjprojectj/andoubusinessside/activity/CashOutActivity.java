package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

public class CashOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_out);
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("余额提现");
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        View progress = findViewById(R.id.progressBar);
        findViewById(R.id.confirmBtn).setOnClickListener(view -> {
            progress.setVisibility(View.VISIBLE);
            progress.postDelayed(() -> {
                ToastUtil.showToast("提现成功!");
                finish();
            }, 1000);
        });
    }
}
