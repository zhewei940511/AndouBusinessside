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
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.base.BaseActivity;
import com.zskjprojectj.andoubusinessside.base.BasePresenter;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;

import java.util.Random;

import static com.zskjprojectj.andoubusinessside.activity.OrderListActivity.KEY_ORDER_TYPE;

/**
 * 商城商家中心
 */
public class UserCenterActivity extends BaseActivity {

    public static final String KEY_USER = "KEY_USER";

    private User info;
    private View progressBar;
    private TextView actionBarTitleTxt;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_user_center);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        info = (User) getIntent().getSerializableExtra(KEY_USER);
        info.setAvatar("http://img4.imgtn.bdimg.com/it/u=2843285098,2906023234&fm=26&gp=0.jpg");
        info.setName("淘淘的淘宝店");
        info.setAccountCount(789.44F);
        info.setUnSendCount(78);
        info.setUnPayCount(18);
        info.setFinishCount(63);
        info.setItemCount(123);
        info.setRate(4);
        info.setRefundCount(12);
        info.setSendedCount(123);
        info.setState("营业中");
        info.setTodayOrderCount(12);
        info.setTodayfinishOrderCount(new Random().nextInt(99));
        info.setUnUseCount(new Random().nextInt(99));
        info.setCancelCount(new Random().nextInt(99));
        info.setVip(true);
        bindView(info);
    }

    private void bindView(User user) {
        Glide.with(mAt)
                .load(user.getAvatar())
                .apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.temp1))
                .into((ImageView) findViewById(R.id.avatar));
        ((TextView) findViewById(R.id.name)).setText(user.getName());
        ((TextView) findViewById(R.id.state)).setText(user.getState());
        ((ScaleRatingBar) findViewById(R.id.ratingBar)).setRating(user.getRate());
        findViewById(R.id.vipImg).setVisibility(user.isVip() ? View.VISIBLE : View.GONE);
        ((TextView) findViewById(R.id.finishCount2)).setText(user.getFinishCount() + "");
        ((TextView) findViewById(R.id.todayOrderCount)).setText(user.getTodayOrderCount() + "");
        ((TextView) findViewById(R.id.finishCount1)).setText(user.getFinishCount() + "");
        View todayFinishEntryBtn = findViewById(R.id.todayFinishEntryBtn);
        ((TextView) findViewById(R.id.accountCount)).setText(FormatUtil.getMoneyString(user.getAccountCount()));
        View cancelOrderEntryBtn = findViewById(R.id.cancelOrderEntryBtn);
        TextView itemManageTxt1 = findViewById(R.id.itemManageTxt1);
        TextView itemManageTxt2 = findViewById(R.id.itemManageTxt2);
        TextView cancelOrderCountTxt = findViewById(R.id.cancelCount);
        ((TextView) findViewById(R.id.itemCount)).setText(user.getItemCount() + "");
        switch (user.getType()) {
            case 0:
                actionBarTitleTxt.setText("商城商家中心");
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

                ((TextView) findViewById(R.id.unsendCount1)).setText(user.getUnSendCount() + "");
                ((TextView) findViewById(R.id.unsendCount2)).setText(user.getUnSendCount() + "");
                ((TextView) findViewById(R.id.unsendCount2)).setText(user.getUnSendCount() + "");
                ((TextView) findViewById(R.id.unpayCount)).setText(user.getUnPayCount() + "");
                ((TextView) findViewById(R.id.sendedCount)).setText(user.getSendedCount() + "");
                ((TextView) findViewById(R.id.refundCount)).setText(user.getRefundCount() + "");
                ((TextView) findViewById(R.id.itemCount)).setText(user.getItemCount() + "");
                itemManageTxt1.setText("商品管理");
                itemManageTxt2.setText("当前商品");
                break;
            case 1:
                actionBarTitleTxt.setText("酒店商家中心");
                View.OnClickListener finishOrderEntryListener2 = view -> {
                    Intent intent = new Intent(this, FinishHotelOrderListActivity.class);
                    intent.putExtra(KEY_ORDER_TYPE, 4);
                    startActivity(intent);
                };
                findViewById(R.id.finishOrderEntryBtn2).setOnClickListener(finishOrderEntryListener2);
                findViewById(R.id.finishOrderEntryBtn).setOnClickListener(finishOrderEntryListener2);

                todayFinishEntryBtn.setVisibility(View.VISIBLE);
                todayFinishEntryBtn.setOnClickListener(finishOrderEntryListener2);
                ((TextView) findViewById(R.id.todayFinishCount)).setText(user.getTodayfinishOrderCount() + "");
                View scanBtn = findViewById(R.id.scanBtn);
                scanBtn.setVisibility(View.VISIBLE);
                scanBtn.setOnClickListener(view -> {

                });
                View unUseOrderEntryBtn = findViewById(R.id.unUseOrderEntryBtn);
                unUseOrderEntryBtn.setVisibility(View.VISIBLE);
                unUseOrderEntryBtn.setOnClickListener(view ->
                        startActivity(new Intent(UserCenterActivity.this, UnCheckInOrderListActivity.class)));
                cancelOrderEntryBtn.setVisibility(View.VISIBLE);
                cancelOrderEntryBtn.setOnClickListener(view ->
                        startActivity(new Intent(UserCenterActivity.this, CancelOrderListActivity.class)));
                ((TextView) findViewById(R.id.unUserCount)).setText(user.getUnSendCount() + "");
                cancelOrderCountTxt.setText(user.getCancelCount() + "");
                itemManageTxt1.setText("房间管理");
                itemManageTxt2.setText("当前房间");
                break;
            case 2:
                actionBarTitleTxt.setText("饭店商家中心");
                ((TextView) findViewById(R.id.todayOrderTxt)).setText("今日预约");
                todayFinishEntryBtn.setVisibility(View.VISIBLE);
                todayFinishEntryBtn.setOnClickListener(view ->
                        startActivity(new Intent(UserCenterActivity.this, DishOrderListActivityActivity.class)));
                findViewById(R.id.dateOrderEntryBtn).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.dateOrderCount)).setText(user.getDateOrderCount() + "");
                cancelOrderEntryBtn.setVisibility(View.VISIBLE);
                itemManageTxt1.setText("菜品管理");
                itemManageTxt2.setText("当前菜品");
                cancelOrderCountTxt.setText(user.getCancelCount() + "");
                cancelOrderEntryBtn.setOnClickListener(view ->
                        startActivity(new Intent(UserCenterActivity.this, DishOrderListActivityActivity.class)));
                View.OnClickListener finishOrderEntryListener3 = view -> {
                    Intent intent = new Intent(this, DishOrderListActivityActivity.class);
                    startActivity(intent);
                };
                findViewById(R.id.finishOrderEntryBtn2).setOnClickListener(finishOrderEntryListener3);
                findViewById(R.id.finishOrderEntryBtn).setOnClickListener(finishOrderEntryListener3);
                findViewById(R.id.dateOrderEntryBtn).setOnClickListener(finishOrderEntryListener3);

                break;
        }
        progressBar.setVisibility(View.GONE);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initViews() {
        progressBar = findViewById(R.id.progressBar);
        actionBarTitleTxt = findViewById(R.id.actionBarTitleTxt);
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
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
