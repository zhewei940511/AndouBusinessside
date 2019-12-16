package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zskjprojectj.andoubusinessside.R;

public class EditPriceSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(Activity.RESULT_OK);
        setContentView(R.layout.activity_edit_price_success);
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("修改成功");
        findViewById(R.id.checkOrderInfoBtn).setOnClickListener(view -> finish());
    }
}
