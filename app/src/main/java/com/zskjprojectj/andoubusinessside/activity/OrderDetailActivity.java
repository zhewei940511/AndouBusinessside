package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.UiUtil;

import butterknife.BindView;

public class OrderDetailActivity extends BaseActivity {

    public static final String KEY_ORDER = "KEY_ORDER";

    @BindView(R.id.controlBtn)
    TextView controlBtn;
    @BindView(R.id.dateTitleTxt)
    TextView dateTitleTxt;
    @BindView(R.id.dateTxt)
    TextView dateTxt;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "订单详情");
        order = (Order) getIntent().getSerializableExtra(KEY_ORDER);
        loadOrderDetail(order);
    }

    private void loadOrderDetail(Order order) {
        HttpRxObservable.getObservable(mActivity, true, true,
                ApiUtils.getApiService().orderDetail(
                        LoginInfo.getUid(),
                        LoginInfo.getMerchantId(),
                        LoginInfo.getMerchantTypeId(),
                        order.order_sn
                ), result -> bindOrderDetail(result.data)).subscribe();
    }

    private void bindOrderDetail(Order order) {
        ((TextView) findViewById(R.id.numTxt)).setText(order.order_sn);
        dateTxt.setText(order.pay_time);
        ((TextView) findViewById(R.id.stateTxt)).setText(Order.STATE.findState(order.status).stateStr);
        ((TextView) findViewById(R.id.receiverTxt)).setText(order.userinfo.name);
        ((TextView) findViewById(R.id.mobileTxt)).setText(order.userinfo.mobile);
        ((TextView) findViewById(R.id.addrTxt)).setText(order.userinfo.getAddressDetail());
        ((TextView) findViewById(R.id.countTxt)).setText(order.allnum);
        UiUtil.bindOrderGoods(order, findViewById(R.id.goodsContainer));
        ((TextView) findViewById(R.id.freightTxt)).setText(order.shipping_free);
        ((TextView) findViewById(R.id.totalTxt)).setText(FormatUtil.getMoneyString(order.goodsPay));
        ((TextView) findViewById(R.id.payWayTxt)).setText(Order.PayWay.findPayWay(order.pay_way).payWayStr);
        if (order.status == Order.STATE.DAI_FU_KUAN.stateInt) {
            controlBtn.setText("修改价格");
            controlBtn.setOnClickListener(view ->
                    EditPriceActivity.start(mActivity, order, 666));
            dateTitleTxt.setText("创建时间：");
            dateTxt.setText(order.created_at);
        } else if (order.status == Order.STATE.DAI_FA_HUO.stateInt) {
            findViewById(R.id.orderStateContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.finalTotalContainer).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.finalTotalTxt)).setText(FormatUtil.getMoneyString(order.order_money));
            controlBtn.setText("去发货");
            controlBtn.setOnClickListener(view -> {
                Intent intent = new Intent(OrderDetailActivity.this, SendActivity.class);
                intent.putExtra(KEY_ORDER, order);
                startActivityForResult(intent, 666);
            });
            dateTitleTxt.setText("支付时间：");
        } else if (order.status == Order.STATE.YI_FA_HUO.stateInt) {
            findViewById(R.id.billContainer).setVisibility(View.GONE);
            findViewById(R.id.sendContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.controlBtnContainer).setVisibility(View.GONE);
            dateTitleTxt.setText("支付时间：");
            controlBtn.setVisibility(View.GONE);
        }
//        else if ("退货退款成功".equals(order.getState())) {
//            findViewById(R.id.refundAddrContainer).setVisibility(View.VISIBLE);
//            findViewById(R.id.sendContainer).setVisibility(View.VISIBLE);
//            findViewById(R.id.refundContainer).setVisibility(View.VISIBLE);
//            findViewById(R.id.controlBtnContainer).setVisibility(View.GONE);
//            findViewById(R.id.billContainer).setVisibility(View.GONE);
//        }
        else {
            controlBtn.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == Activity.RESULT_OK) {
            loadOrderDetail(order);
        }
    }

    public static void start(Activity baseActivity, Order info, int requestCode) {
        Intent intent = new Intent(baseActivity, OrderDetailActivity.class);
        intent.putExtra(KEY_ORDER, info);
        ActivityUtils.startActivityForResult(baseActivity, intent, requestCode);
    }
}
