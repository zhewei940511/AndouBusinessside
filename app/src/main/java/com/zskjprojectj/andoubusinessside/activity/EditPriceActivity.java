package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;
import com.zskjprojectj.andoubusinessside.utils.UiUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zskjprojectj.andoubusinessside.activity.OrderDetailActivity.KEY_ORDER;

public class EditPriceActivity extends BaseActivity {

    @BindView(R.id.editTotalTxt)
    EditText editTotalTxt;

    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "修改价格");
        order = (Order) getIntent().getSerializableExtra(KEY_ORDER);
        ((TextView) findViewById(R.id.freightTxt)).setText(order.shipping_free);
        ((TextView) findViewById(R.id.totalTxt)).setText(FormatUtil.getMoneyString(order.order_money));
        ((TextView) findViewById(R.id.totalTxt2)).setText(FormatUtil.getMoneyString(order.goodsPay));
        ((TextView) findViewById(R.id.countTxt)).setText(order.allnum);
        editTotalTxt.setText(FormatUtil.getMoneyString(order.order_money));
        editTotalTxt.setSelection(editTotalTxt.getText().length());
        UiUtil.bindOrderGoods(order, findViewById(R.id.goodsContainer));
    }

    @OnClick(R.id.confirmBtn)
    void onConfirmClick() {
        double newAmount = Double.parseDouble(editTotalTxt.getText().toString().replaceAll("￥  ", ""));
        if (order.order_money == newAmount) {
            ToastUtil.showToast("请修改商品价格!");
            return;
        }
        HttpRxObservable.getObservable(mActivity, true, false,
                ApiUtils.getApiService().editPrice(
                        LoginInfo.getUid(),
                        LoginInfo.getMerchantId(),
                        order.order_sn,
                        newAmount
                ), result -> {
                    Intent intent = new Intent(EditPriceActivity.this, EditPriceSuccessActivity.class);
                    startActivityForResult(intent, 666);
                }).subscribe();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_edit_price;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == Activity.RESULT_OK) {
            setResult(resultCode);
            finish();
        }
    }

    public static void start(Activity activity, Order order, int requestCode) {
        Intent intent = new Intent(activity, EditPriceActivity.class);
        intent.putExtra(KEY_ORDER, order);
        ActivityUtils.startActivityForResult(activity, intent, requestCode);
    }
}
