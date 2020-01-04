package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.OrderT;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ScreenUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

import static com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity.KEY_ORDER;

public class RefundActivity extends BaseActivity {
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBar = findViewById(R.id.progressBar);
        progressBar.postDelayed(() -> progressBar.setVisibility(View.GONE), 1000);
        ActionBarUtil.setTitle(mActivity,"退货退款");

        OrderT orderT = (OrderT) getIntent().getSerializableExtra(KEY_ORDER);
        ((TextView) findViewById(R.id.numTxt)).setText(orderT.getNum());
        ((TextView) findViewById(R.id.dateTxt)).setText(FormatUtil.getDateString1(orderT.getDate()));
        ((TextView) findViewById(R.id.titleTxt)).setText(orderT.getTitle());
        ((TextView) findViewById(R.id.specTxt)).setText(orderT.getSpec());
        ((TextView) findViewById(R.id.countTxt)).setText(orderT.getCount() + "");
        ((TextView) findViewById(R.id.priceTxt)).setText(FormatUtil.getMoneyString(orderT.getPrice()));
        ((TextView) findViewById(R.id.stateTxt)).setText(orderT.getState());
        Glide.with(this)
                .load(orderT.getIcon())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(this, 2))))
                .into((ImageView) findViewById(R.id.iconImg));
        findViewById(R.id.confirmBtn).setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
                setResult(Activity.RESULT_OK);
                ToastUtil.showToast("退货退款成功!");
                finish();
            }, 1000);
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_refund;
    }
}
