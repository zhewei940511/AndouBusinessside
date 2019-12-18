package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ScreenUtil;

import static com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity.KEY_ORDER;

public class HotelOrderDetailActivity extends AppCompatActivity {
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_order_detail);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.postDelayed(() -> progressBar.setVisibility(View.GONE), 1500);
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("订单详情");
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        Order info = (Order) getIntent().getSerializableExtra(KEY_ORDER);
        TextView stateTxt1 = findViewById(R.id.stateTxt1);
        TextView stateTxt2 = findViewById(R.id.stateTxt2);
        TextView controlBtn = findViewById(R.id.controlBtn);
        if (info.getState().equals("待入住")) {
            stateTxt1.setText("待入住");
            stateTxt2.setText("客户暂未入住");
            controlBtn.setText("订单已使用");
        } else if (info.getState().equals("待审核")) {
            stateTxt1.setText("待审核");
            stateTxt2.setText("等待商家审核");
            controlBtn.setText("确认取消");
            findViewById(R.id.refundContainer).setVisibility(View.VISIBLE);
        } else if (info.getState().equals("已取消")) {
            stateTxt1.setText("已取消");
            stateTxt2.setText("客户已取消");
            findViewById(R.id.refundContainer).setVisibility(View.VISIBLE);
            controlBtn.setVisibility(View.GONE);
        } else if (info.getState().equals("已完成")) {
            stateTxt1.setText("已完成");
            stateTxt2.setText("客户已使用");
            controlBtn.setVisibility(View.GONE);
        }
        ((TextView) findViewById(R.id.numTxt)).setText(info.getNum());
        ((TextView) findViewById(R.id.dateTxt)).setText(FormatUtil.getDateString1(info.getDate()));
        ((TextView) findViewById(R.id.titleTxt)).setText(info.getTitle());
        ((TextView) findViewById(R.id.specTxt)).setText(info.getSpec());
        ((TextView) findViewById(R.id.countTxt1)).setText(info.getCount() + "");
        ((TextView) findViewById(R.id.countTxt2)).setText(info.getCount() + "");
        ((TextView) findViewById(R.id.priceTxt)).setText(FormatUtil.getMoneyString(info.getPrice()));
        ((TextView) findViewById(R.id.totalTxt)).setText(FormatUtil.getMoneyString(info.getTotal()));
        ((TextView) findViewById(R.id.scoreTxt)).setText(FormatUtil.getMoneyString(info.getScore()));
        Glide.with(this)
                .load(info.getIcon())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(this, 2))))
                .into((ImageView) findViewById(R.id.iconImg));
    }
}
