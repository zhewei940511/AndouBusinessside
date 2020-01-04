package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.model.OrderT;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ScreenUtil;

public class OrderInfoActivity extends BaseActivity {

    public static final String KEY_ORDER = "KEY_ORDER";
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.postDelayed(() -> progressBar.setVisibility(View.GONE), 1500);
        ActionBarUtil.setTitle(mActivity, "订单详情");
        OrderT info = (OrderT) getIntent().getSerializableExtra(KEY_ORDER);
        TextView controlBtn = findViewById(R.id.controlBtn);
        TextView payWayTxt = findViewById(R.id.payWayTxt);
        if ("待付款".equals(info.getState())) {
            controlBtn.setText("修改价格");
            controlBtn.setOnClickListener(view -> {
                Intent intent = new Intent(OrderInfoActivity.this, EditPriceActivity.class);
                intent.putExtra(KEY_ORDER, info);
                startActivityForResult(intent, 666);
            });
            payWayTxt.setText(info.getState());
        } else if ("待发货".equals(info.getState())) {
            findViewById(R.id.orderStateContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.finalTotalContainer).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.stateTxt)).setText(info.getState());
            ((TextView) findViewById(R.id.finalTotalTxt)).setText(FormatUtil.getMoneyString(info.getTotal()));
            controlBtn.setText("去发货");
            controlBtn.setOnClickListener(view -> {
                Intent intent = new Intent(OrderInfoActivity.this, SendActivity.class);
                intent.putExtra(KEY_ORDER, info);
                startActivityForResult(intent, 666);
            });
            payWayTxt.setText("支付宝支付");
            payWayTxt.setTextColor(Color.parseColor("#ff1c1c1c"));
        } else if ("已发货".equals(info.getState())) {
            ((TextView) findViewById(R.id.stateTxt)).setText(info.getState());
            findViewById(R.id.billContainer).setVisibility(View.GONE);
            findViewById(R.id.sendContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.controlBtnContainer).setVisibility(View.GONE);
        } else if ("退货退款成功".equals(info.getState())) {
            findViewById(R.id.refundAddrContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.sendContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.refundContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.controlBtnContainer).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.stateTxt)).setText(info.getState());
            findViewById(R.id.billContainer).setVisibility(View.GONE);
        } else {
            controlBtn.setVisibility(View.GONE);
        }
        ((TextView) findViewById(R.id.numTxt)).setText(info.getNum());
        ((TextView) findViewById(R.id.dateTxt)).setText(FormatUtil.getDateString1(info.getDate()));
        ((TextView) findViewById(R.id.receiverTxt)).setText(info.getReceiver());
        ((TextView) findViewById(R.id.titleTxt)).setText(info.getTitle());
        ((TextView) findViewById(R.id.specTxt)).setText(info.getSpec());
        ((TextView) findViewById(R.id.countTxt1)).setText(info.getCount() + "");
        ((TextView) findViewById(R.id.countTxt2)).setText(info.getCount() + "");
        ((TextView) findViewById(R.id.priceTxt)).setText(FormatUtil.getMoneyString(info.getPrice()));
        ((TextView) findViewById(R.id.totalTxt)).setText(FormatUtil.getMoneyString(info.getTotal()));
        ((TextView) findViewById(R.id.addrTxt)).setText(info.getAddr());
        ((TextView) findViewById(R.id.scoreTxt)).setText(FormatUtil.getMoneyString(info.getScore()));
        ((TextView) findViewById(R.id.freightTxt)).setText(FormatUtil.getMoneyString(info.getFreight()));
        Glide.with(this)
                .load(info.getIcon())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(this, 2))))
                .into((ImageView) findViewById(R.id.iconImg));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_order_info;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == Activity.RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
            }, 1000);
        }
    }

    public static void start(Activity baseActivity, Order info, int requestCode) {
        Intent intent = new Intent(baseActivity, OrderInfoActivity.class);
        intent.putExtra(KEY_ORDER, info);
        ActivityUtils.startActivityForResult(baseActivity, intent, requestCode);
    }
}
