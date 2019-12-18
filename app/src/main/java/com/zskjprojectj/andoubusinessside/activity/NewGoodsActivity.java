package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zskjprojectj.andoubusinessside.R;

public class NewGoodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goods);
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("商品上传");
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        LinearLayout specContainer = findViewById(R.id.specContainer);
        findViewById(R.id.newSpecBtn).setOnClickListener(view -> {
            View specView = LayoutInflater.from(NewGoodsActivity.this).inflate(R.layout.layout_spec_item, specContainer, false);
            View deleteBtn = specView.findViewById(R.id.deleteBtn);
            specContainer.addView(specView);
            deleteBtn.setVisibility(View.VISIBLE);
            deleteBtn.setOnClickListener(view1 -> ((ViewGroup) specView.getParent()).removeView(specView));
        });
    }
}
