package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.Category;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;

import butterknife.BindView;

import static com.zskjprojectj.andoubusinessside.activity.EditInfoActivity.KEY_INFO;

public class NewGoodsActivity extends BaseActivity {

    @BindView(R.id.categoryTxt)
    TextView categoryTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "商品上传");
        categoryTxt.setOnClickListener(v -> CategoryActivity.start(mActivity, 666));


        LinearLayout specContainer = findViewById(R.id.specContainer);
        findViewById(R.id.newSpecBtn).setOnClickListener(view -> {
            View specView = LayoutInflater.from(NewGoodsActivity.this).inflate(R.layout.layout_spec_item, specContainer, false);
            View deleteBtn = specView.findViewById(R.id.deleteBtn);
            specContainer.addView(specView);
            deleteBtn.setVisibility(View.VISIBLE);
            deleteBtn.setOnClickListener(view1 -> ((ViewGroup) specView.getParent()).removeView(specView));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == Activity.RESULT_OK && data != null) {
            Category category = (Category) data.getSerializableExtra(KEY_INFO);
            categoryTxt.setText(category.name);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_new_goods;
    }
}
