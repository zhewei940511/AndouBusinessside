package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.UiUtil;

import static com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity.KEY_ORDER;

public class SendActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "去发货");
        Order info = (Order) getIntent().getSerializableExtra(KEY_ORDER);
        ((TextView) findViewById(R.id.numTxt)).setText(info.order_sn);
//        ((TextView) findViewById(R.id.dateTxt)).setText(FormatUtil.getDateString1(info.getDate()));
//        ((TextView) findViewById(R.id.receiverTxt)).setText(info.getReceiver());
//        ((TextView) findViewById(R.id.mobileTxt)).setText(info.getMobile());
//        ((TextView) findViewById(R.id.addrTxt)).setText(info.getAddr());
        UiUtil.bindOrderGoods(info, findViewById(R.id.goodsContainer));
        findViewById(R.id.confirmBtn).setOnClickListener(view -> {

        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_send;
    }

    public static void start(Activity activity, Order order, int requestCode) {
        Intent intent = new Intent(activity, SendActivity.class);
        intent.putExtra(KEY_ORDER, order);
        ActivityUtils.startActivityForResult(activity, intent, requestCode);
    }
}
