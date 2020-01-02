package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.willy.ratingbar.ScaleRatingBar;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.BaseObserver;
import com.zskjprojectj.andoubusinessside.http.BaseResult;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.http.ListData;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.model.UserT;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.UserUtil;

import java.util.Random;

import butterknife.OnClick;

import static com.zskjprojectj.andoubusinessside.activity.OrderListActivity.KEY_ORDER_TYPE;

public class UserCenterActivity extends BaseActivity {

    public static final String KEY_USER = "KEY_USER";

    @OnClick(R.id.settingEntryBtn)
    void onSettingEntryBtn() {
        ActivityUtils.startActivity(SettingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setStatusBarColor(mActivity, Color.TRANSPARENT);
        ActionBarUtil.getBackground(mActivity, false).setAlpha(0);
        HttpRxObservable.getObservable(mActivity, ApiUtils.getApiService().testList())
                .subscribe(new BaseObserver<ListData<Order>>(mActivity) {
                    @Override
                    public void onSuccess(BaseResult<ListData<Order>> result) {

                    }
                });
        findViewById(R.id.todayOrderEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, OrderListActivity.class);
            intent.putExtra(KEY_ORDER_TYPE, 0);
            startActivity(intent);
        });
        findViewById(R.id.manageShopEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, ManageShopActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.chartEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, ChartActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.walletEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, WalletActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.manageGoodsEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, ManageGoodsActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.noticeListEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, NoticeListActivity.class);
            startActivity(intent);
        });
        ScrollView scrollView = findViewById(R.id.scrollView);
        final ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = ()
                -> ActionBarUtil.getBackground(mActivity, false).setAlpha((float) (scrollView.getScrollY() * 0.01));
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            private ViewTreeObserver observer;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (observer == null) {
                    observer = scrollView.getViewTreeObserver();
                    observer.addOnScrollChangedListener(onScrollChangedListener);
                } else if (!observer.isAlive()) {
                    observer.removeOnScrollChangedListener(onScrollChangedListener);
                    observer = scrollView.getViewTreeObserver();
                    observer.addOnScrollChangedListener(onScrollChangedListener);
                }

                return false;
            }
        });
        UserUtil.getInstance().userT.avatar = ("http://img4.imgtn.bdimg.com/it/u=2843285098,2906023234&fm=26&gp=0.jpg");
        UserUtil.getInstance().userT.name = ("淘淘的淘宝店");
        UserUtil.getInstance().userT.accountCount = (789.44F);
        UserUtil.getInstance().userT.unSendCount = (78);
        UserUtil.getInstance().userT.unPayCount = (18);
        UserUtil.getInstance().userT.finishCount = (63);
        UserUtil.getInstance().userT.itemCount = (123);
        UserUtil.getInstance().userT.rate = (4);
        UserUtil.getInstance().userT.refundCount = (12);
        UserUtil.getInstance().userT.sendedCount = (123);
        UserUtil.getInstance().userT.state = ("营业中");
        UserUtil.getInstance().userT.todayOrderCount = (12);
        UserUtil.getInstance().userT.todayfinishOrderCount = (new Random().nextInt(99));
        UserUtil.getInstance().userT.unUseCount = (new Random().nextInt(99));
        UserUtil.getInstance().userT.cancelCount = (new Random().nextInt(99));
        UserUtil.getInstance().userT.isVip = (true);
        bindView(UserUtil.getInstance().userT);
    }

    private void bindView(UserT userT) {
        Glide.with(mActivity)
                .load(userT.avatar)
                .apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.temp1))
                .into((ImageView) findViewById(R.id.avatar));
        ((TextView) findViewById(R.id.name)).setText(userT.name);
        ((TextView) findViewById(R.id.state)).setText(userT.state);
        ((ScaleRatingBar) findViewById(R.id.ratingBar)).setRating(userT.rate);
        findViewById(R.id.vipImg).setVisibility(userT.isVip ? View.VISIBLE : View.GONE);
        ((TextView) findViewById(R.id.finishCount2)).setText(userT.finishCount + "");
        ((TextView) findViewById(R.id.todayOrderCount)).setText(userT.todayfinishOrderCount + "");
        ((TextView) findViewById(R.id.finishCount1)).setText(userT.finishCount + "");
        View todayFinishEntryBtn = findViewById(R.id.todayFinishEntryBtn);
        ((TextView) findViewById(R.id.accountCount)).setText(FormatUtil.getMoneyString(userT.accountCount));
        View cancelOrderEntryBtn = findViewById(R.id.cancelOrderEntryBtn);
        TextView itemManageTxt1 = findViewById(R.id.itemManageTxt1);
        TextView itemManageTxt2 = findViewById(R.id.itemManageTxt2);
        TextView cancelOrderCountTxt = findViewById(R.id.cancelCount);
        ((TextView) findViewById(R.id.itemCount)).setText(userT.itemCount + "");
        if (userT.types.contains(UserT.Type.MALL)) {
            ActionBarUtil.setTitle(mActivity, "商城商家中心", false);
            View unsendOrderEntryBtn = findViewById(R.id.unsendOrderEntryBtn);
            View unsendOrderEntryBtn2 = findViewById(R.id.unsendOrderEntryBtn2);
            unsendOrderEntryBtn.setVisibility(View.VISIBLE);
            unsendOrderEntryBtn2.setVisibility(View.VISIBLE);
            View.OnClickListener unSendOrderListEntryBtnListener = view -> {
                Intent intent = new Intent(this, OrderListActivity.class);
                intent.putExtra(KEY_ORDER_TYPE, 2);
                startActivity(intent);
            };
            unsendOrderEntryBtn.setOnClickListener(unSendOrderListEntryBtnListener);
            unsendOrderEntryBtn2.setOnClickListener(unSendOrderListEntryBtnListener);

            View unPayOrderEntryBtn = findViewById(R.id.unpayOrderListEntryBtn);
            unPayOrderEntryBtn.setVisibility(View.VISIBLE);
            unPayOrderEntryBtn.setOnClickListener(view -> {
                Intent intent = new Intent(this, OrderListActivity.class);
                intent.putExtra(KEY_ORDER_TYPE, 1);
                startActivity(intent);
            });

            View sendOrderEntryBtn = findViewById(R.id.sendedOrderEntryBtn);
            sendOrderEntryBtn.setVisibility(View.VISIBLE);
            sendOrderEntryBtn.setOnClickListener(view -> {
                Intent intent = new Intent(this, OrderListActivity.class);
                intent.putExtra(KEY_ORDER_TYPE, 3);
                startActivity(intent);
            });

            View refundOrderEntryBtn = findViewById(R.id.refundEntryBtn);
            refundOrderEntryBtn.setVisibility(View.VISIBLE);
            refundOrderEntryBtn.setOnClickListener(view -> {
                Intent intent = new Intent(this, RefundOrderListActivity.class);
                startActivity(intent);
            });
            View.OnClickListener finishOrderEntryListener = view -> {
                Intent intent = new Intent(this, OrderListActivity.class);
                intent.putExtra(KEY_ORDER_TYPE, 4);
                startActivity(intent);
            };
            findViewById(R.id.finishOrderEntryBtn).setOnClickListener(finishOrderEntryListener);
            findViewById(R.id.finishOrderEntryBtn2).setOnClickListener(finishOrderEntryListener);

            ((TextView) findViewById(R.id.unsendCount1)).setText(userT.unSendCount + "");
            ((TextView) findViewById(R.id.unsendCount2)).setText(userT.unSendCount + "");
            ((TextView) findViewById(R.id.unsendCount2)).setText(userT.unSendCount + "");
            ((TextView) findViewById(R.id.unpayCount)).setText(userT.unPayCount + "");
            ((TextView) findViewById(R.id.sendedCount)).setText(userT.sendedCount + "");
            ((TextView) findViewById(R.id.refundCount)).setText(userT.refundCount + "");
            ((TextView) findViewById(R.id.itemCount)).setText(userT.itemCount + "");
            itemManageTxt1.setText("商品管理");
            itemManageTxt2.setText("当前商品");
        } else if (userT.types.contains(UserT.Type.HOTEL)) {
            ActionBarUtil.setTitle(mActivity, "酒店商家中心", false);
            View.OnClickListener finishOrderEntryListener2 = view -> {
                Intent intent = new Intent(this, FinishHotelOrderListActivity.class);
                intent.putExtra(KEY_ORDER_TYPE, 4);
                startActivity(intent);
            };
            findViewById(R.id.finishOrderEntryBtn2).setOnClickListener(finishOrderEntryListener2);
            findViewById(R.id.finishOrderEntryBtn).setOnClickListener(finishOrderEntryListener2);
            todayFinishEntryBtn.setVisibility(View.VISIBLE);
            todayFinishEntryBtn.setOnClickListener(finishOrderEntryListener2);
            ((TextView) findViewById(R.id.todayFinishCount)).setText(userT.todayfinishOrderCount + "");
            View scanBtn = findViewById(R.id.scanBtn);
            scanBtn.setVisibility(View.VISIBLE);
            scanBtn.setOnClickListener(view -> ActivityUtils.startActivity(QrCodeActivity.class));
            View unUseOrderEntryBtn = findViewById(R.id.unUseOrderEntryBtn);
            unUseOrderEntryBtn.setVisibility(View.VISIBLE);
            unUseOrderEntryBtn.setOnClickListener(view ->
                    startActivity(new Intent(UserCenterActivity.this, UnCheckInOrderListActivity.class)));
            cancelOrderEntryBtn.setVisibility(View.VISIBLE);
            cancelOrderEntryBtn.setOnClickListener(view ->
                    startActivity(new Intent(UserCenterActivity.this, CancelOrderListActivity.class)));
            ((TextView) findViewById(R.id.unUserCount)).setText(userT.unSendCount + "");
            cancelOrderCountTxt.setText(userT.cancelCount + "");
            itemManageTxt1.setText("房间管理");
            itemManageTxt2.setText("当前房间");
        } else if (userT.types.contains(UserT.Type.RESTAURANT)) {
            ActionBarUtil.setTitle(mActivity, "饭店商家中心", false);
            ((TextView) findViewById(R.id.todayOrderTxt)).setText("今日预约");
            todayFinishEntryBtn.setVisibility(View.VISIBLE);
            todayFinishEntryBtn.setOnClickListener(view ->
                    startActivity(new Intent(UserCenterActivity.this, DishOrderListActivityActivity.class)));
            findViewById(R.id.dateOrderEntryBtn).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.dateOrderCount)).setText(userT.dateOrderCount + "");
            cancelOrderEntryBtn.setVisibility(View.VISIBLE);
            itemManageTxt1.setText("菜品管理");
            itemManageTxt2.setText("当前菜品");
            cancelOrderCountTxt.setText(userT.cancelCount + "");
            cancelOrderEntryBtn.setOnClickListener(view ->
                    startActivity(new Intent(UserCenterActivity.this, DishOrderListActivityActivity.class)));
            View.OnClickListener finishOrderEntryListener3 = view -> {
                Intent intent = new Intent(this, DishOrderListActivityActivity.class);
                startActivity(intent);
            };
            findViewById(R.id.finishOrderEntryBtn2).setOnClickListener(finishOrderEntryListener3);
            findViewById(R.id.finishOrderEntryBtn).setOnClickListener(finishOrderEntryListener3);
            findViewById(R.id.dateOrderEntryBtn).setOnClickListener(finishOrderEntryListener3);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_user_center;
    }
}
