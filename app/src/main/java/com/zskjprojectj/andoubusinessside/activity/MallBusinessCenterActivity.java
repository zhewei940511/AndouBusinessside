package com.zskjprojectj.andoubusinessside.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.willy.ratingbar.ScaleRatingBar;
import com.zskjprojectj.andoubusinessside.model.MallBusinessCenter;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.base.BaseActivity;
import com.zskjprojectj.andoubusinessside.base.BasePresenter;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;

import static com.zskjprojectj.andoubusinessside.activity.OrderListActivity.KEY_ORDER_TYPE;

/**
 * 商城商家中心
 */
public class MallBusinessCenterActivity extends BaseActivity {

    private MallBusinessCenter info;
    private View progressBar;


    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_mallbusinesscenter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        info = new MallBusinessCenter();
        info.setAvatar("http://img4.imgtn.bdimg.com/it/u=2843285098,2906023234&fm=26&gp=0.jpg");
        info.setName("淘淘的淘宝店");
        info.setAccountCount(789.44F);
        info.setUnSendCount(78);
        info.setUnPayCount(18);
        info.setFinishCount(63);
        info.setGoodsCount(123);
        info.setRate(4);
        info.setRefundCount(12);
        info.setSendedCount(123);
        info.setState("营业中");
        info.setTodayOrderCount(12);
        info.setVip(true);

        progressBar.postDelayed(() -> {
            bindView(info);
        }, 1500);
    }

    private void bindView(MallBusinessCenter info) {
        Glide.with(mAt)
                .load(info.getAvatar())
                .apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.temp1))
                .into((ImageView) findViewById(R.id.avatar));
        ((TextView) findViewById(R.id.name)).setText(info.getName());
        ((TextView) findViewById(R.id.state)).setText(info.getState());
        ((ScaleRatingBar) findViewById(R.id.ratingBar)).setRating(info.getRate());
        ((TextView) findViewById(R.id.todayOrderCount)).setText(info.getTodayOrderCount() + "");
        ((TextView) findViewById(R.id.unsendCount1)).setText(info.getUnSendCount() + "");
        ((TextView) findViewById(R.id.unsendCount2)).setText(info.getUnSendCount() + "");
        ((TextView) findViewById(R.id.unsendCount2)).setText(info.getUnSendCount() + "");
        ((TextView) findViewById(R.id.finishCount1)).setText(info.getFinishCount() + "");
        ((TextView) findViewById(R.id.finishCount2)).setText(info.getFinishCount() + "");
        ((TextView) findViewById(R.id.unpayCount)).setText(info.getUnPayCount() + "");
        ((TextView) findViewById(R.id.sendedCount)).setText(info.getSendedCount() + "");
        ((TextView) findViewById(R.id.refundCount)).setText(info.getRefundCount() + "");
        ((TextView) findViewById(R.id.goodsCount)).setText(info.getGoodsCount() + "");
        ((TextView) findViewById(R.id.accountCount)).setText(FormatUtil.getMoneyString(info.getAccountCount()));
        findViewById(R.id.vipImg).setVisibility(info.isVip() ? View.VISIBLE : View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initViews() {
        progressBar = findViewById(R.id.progressBar);
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        findViewById(R.id.unpayOrderListEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, OrderListActivity.class);
            intent.putExtra(KEY_ORDER_TYPE, 1);
            startActivity(intent);
        });
        findViewById(R.id.unsendOrderEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, OrderListActivity.class);
            intent.putExtra(KEY_ORDER_TYPE, 2);
            startActivity(intent);
        });
        findViewById(R.id.sendedOrderEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, OrderListActivity.class);
            intent.putExtra(KEY_ORDER_TYPE, 3);
            startActivity(intent);
        });
        findViewById(R.id.finishOrderEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, OrderListActivity.class);
            intent.putExtra(KEY_ORDER_TYPE, 4);
            startActivity(intent);
        });
        findViewById(R.id.refundEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, RefundOrderListActivity.class);
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
        ScrollView scrollView = findViewById(R.id.scrollView);
        View actionBarBackground = findViewById(R.id.customActionBarBackground);
        final ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = ()
                -> actionBarBackground.setAlpha((float) (scrollView.getScrollY() * 0.01));
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
    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
