package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.Random;

public class OrderInfoActivity extends AppCompatActivity {

    public static final String KEY_ORDER_INFO = "KEY_ORDER_INFO";
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.postDelayed(() -> progressBar.setVisibility(View.GONE), 1500);
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("订单详情");
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        Order info = getOrderInfo();
        ((TextView) findViewById(R.id.numTxt)).setText(info.getNum());
        ((TextView) findViewById(R.id.dateTxt)).setText(FormatUtil.getDateString1(info.getDate()));
        ((TextView) findViewById(R.id.receiverTxt)).setText(info.getReceiver());
        ((TextView) findViewById(R.id.receiverTxt)).setText(info.getReceiver());
        ((TextView) findViewById(R.id.titleTxt)).setText(info.getTitle());
        ((TextView) findViewById(R.id.specTxt)).setText(info.getSpec());
        ((TextView) findViewById(R.id.countTxt1)).setText(info.getCount() + "");
        ((TextView) findViewById(R.id.countTxt2)).setText(info.getCount() + "");
        ((TextView) findViewById(R.id.priceTxt)).setText(FormatUtil.getMoneyString(info.getPrice()));
        ((TextView) findViewById(R.id.totalTxt)).setText(FormatUtil.getMoneyString(info.getTotal()));
        ((TextView) findViewById(R.id.payWayTxt)).setText(info.getState());
        ((TextView) findViewById(R.id.addrTxt)).setText(info.getAddr());
        ((TextView) findViewById(R.id.scoreTxt)).setText(FormatUtil.getMoneyString(info.getScore()));
        ((TextView) findViewById(R.id.freightTxt)).setText(FormatUtil.getMoneyString(info.getFreight()));
        Glide.with(this)
                .load(info.getIcon())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(this, 2))))
                .into((ImageView) findViewById(R.id.iconImg));
        findViewById(R.id.editPriceEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(OrderInfoActivity.this, EditPriceActivity.class);
            intent.putExtra(KEY_ORDER_INFO, info);
            startActivityForResult(intent, 666);
        });
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

    private Order getOrderInfo() {
        Order info = new Order();
        info.setCount(new Random().nextInt(100));
        info.setIcon("https://himg2.huanqiucdn.cn/attachment2010/2019/1214/20191214071048532.jpg");
        info.setNum(new Random().nextInt(1000000000) + "");
        info.setPrice(new Random().nextFloat());
        info.setState("代付款");
        info.setSpec("四件套");
        info.setTitle("亲润孕妇化妆品套装BB霜 遮瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护");
        info.setTotal(new Random().nextFloat());
        info.setDate(System.currentTimeMillis());
        info.setReceiver("王杨");
        info.setAddr("重庆市南岸区亚太路9号就系国际6栋10-8");
        info.setMobile("13388888888");
        info.setScore(new Random().nextFloat());
        info.setFreight(new Random().nextFloat());
        return info;
    }
}
