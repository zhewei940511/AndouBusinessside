package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zskjprojectj.andoubusinessside.R;

public class NewDishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dish);
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("菜品上传");
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
    }
}
