package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ScreenUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

import java.util.Random;

import static com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity.KEY_ORDER;

public class EditPriceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_price);
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("修改价格");
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        Order info = (Order) getIntent().getSerializableExtra(KEY_ORDER);
        ((TextView) findViewById(R.id.titleTxt)).setText(info.getTitle());
        ((TextView) findViewById(R.id.countTxt)).setText(info.getCount() + "");
        ((TextView) findViewById(R.id.priceTxt)).setText(FormatUtil.getMoneyString(info.getPrice()));
        ((TextView) findViewById(R.id.totalTxt)).setText(FormatUtil.getMoneyString(info.getTotal()));
        EditText editTotalTxt = findViewById(R.id.editTotalTxt);
        editTotalTxt.setText(FormatUtil.getMoneyString(info.getTotal()));
        editTotalTxt.setSelection(editTotalTxt.getText().length() - 1);
        ((TextView) findViewById(R.id.specTxt)).setText(info.getSpec());
        Glide.with(this)
                .load(info.getIcon())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(this, 2))))
                .into((ImageView) findViewById(R.id.iconImg));
        findViewById(R.id.confirmBtn).setOnClickListener(view -> {
            view.setEnabled(false);
            View progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
                if (new Random().nextBoolean()) {
                    Intent intent = new Intent(EditPriceActivity.this, EditPriceSuccessActivity.class);
                    startActivityForResult(intent, 666);
                } else {
                    view.setEnabled(true);
                    ToastUtil.showToast("修改失败,请重试!");
                }
            }, 1000);
        });
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        editTotalTxt.addTextChangedListener(textWatcher);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == Activity.RESULT_OK) {
            setResult(resultCode);
            finish();
        }
    }
}
