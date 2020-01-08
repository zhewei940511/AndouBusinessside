package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.Express;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.UiUtil;

import butterknife.BindView;

import static com.zskjprojectj.andoubusinessside.activity.OrderDetailActivity.KEY_ORDER;

public class SendActivity extends BaseActivity {

    @BindView(R.id.expressNameTxt)
    TextView expressNameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "去发货");
        Order info = (Order) getIntent().getSerializableExtra(KEY_ORDER);
        ((TextView) findViewById(R.id.numTxt)).setText(info.order_sn);
        ((TextView) findViewById(R.id.dateTxt)).setText(info.pay_time);
        ((TextView) findViewById(R.id.receiverTxt)).setText(info.userinfo.name);
        ((TextView) findViewById(R.id.mobileTxt)).setText(info.userinfo.mobile);
        ((TextView) findViewById(R.id.addrTxt)).setText(info.userinfo.getAddressDetail());
        UiUtil.bindOrderGoods(info, findViewById(R.id.goodsContainer));
        expressNameTxt.setOnClickListener(v ->
                ActivityUtils.startActivityForResult(mActivity, SelectExpressActivity.class, 666));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == Activity.RESULT_OK && data != null) {
            Express express = SelectExpressActivity.getResult(data);
            expressNameTxt.setText(express.name);
        }
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
