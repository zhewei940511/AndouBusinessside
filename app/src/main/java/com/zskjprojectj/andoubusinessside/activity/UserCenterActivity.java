package com.zskjprojectj.andoubusinessside.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.willy.ratingbar.ScaleRatingBar;
import com.zskjprojectj.andoubusinessside.R;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.model.UserT;
import com.zhuosongkj.android.library.util.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.UrlUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zskjprojectj.andoubusinessside.activity.OrderListActivity.KEY_STATE;
import static com.zskjprojectj.andoubusinessside.model.User.Role.KEY_ROLE;

public class UserCenterActivity extends BaseActivity {

    @OnClick(R.id.settingEntryBtn)
    void onSettingEntryBtn() {
        ActivityUtils.startActivity(SettingActivity.class);
    }

    @BindView(R.id.unpayOrderListEntryBtn)
    View unpayOrderListEntryBtn;
    @BindView(R.id.unsendOrderEntryBtn)
    View unsendOrderEntryBtn;
    @BindView(R.id.unsendOrderEntryBtn2)
    View unsendOrderEntryBtn2;

    @BindView(R.id.itemManageTxt1)
    TextView itemManageTxt1;
    @BindView(R.id.itemManageTxt2)
    TextView itemManageTxt2;

    @BindView(R.id.finishOrderEntryBtn)
    View finishOrderEntryBtn;
    @BindView(R.id.finishOrderEntryBtn2)
    View finishOrderEntryBtn2;

    @BindView(R.id.sendedOrderEntryBtn)
    View sendedOrderEntryBtn;

    @BindView(R.id.refundEntryBtn)
    View refundEntryBtn;
    User.Role role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.transparentStatusBar(mActivity);
        ActionBarUtil.getBackground(mActivity, false).setAlpha(0);
        initScrollView();
        role = (User.Role) getIntent().getSerializableExtra(KEY_ROLE);
        if (role.merchant_type_id == User.Role.Type.MALL.typeInt) {
            ActionBarUtil.setTitle(mActivity, "商城商家中心", false);
            itemManageTxt1.setText("商品管理");
            itemManageTxt2.setText("当前商品");
        } else if (role.merchant_type_id == User.Role.Type.HOTEL.typeInt) {
            ActionBarUtil.setTitle(mActivity, "酒店商家中心", false);
            itemManageTxt1.setText("房间管理");
            itemManageTxt2.setText("当前房间");
        } else if (role.merchant_type_id == User.Role.Type.RESTAURANT.typeInt) {
            ActionBarUtil.setTitle(mActivity, "饭店商家中心", false);
            itemManageTxt1.setText("菜品管理");
            itemManageTxt2.setText("当前菜品");
        }
        loadRoleInfo();
        findViewById(R.id.todayOrderEntryBtn).setOnClickListener(view -> {
            Intent intent = new Intent(this, OrderListActivity.class);
            intent.putExtra(KEY_STATE, 0);
            startActivity(intent);
        });
        findViewById(R.id.manageShopEntryBtn).setOnClickListener(view ->
                ActivityUtils.startActivityForResult(mActivity, ManageShopActivity.class, 666));
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
    }

    private void loadRoleInfo() {
        HttpRxObservable.getObservable(mActivity, true, true, ApiUtils.getApiService().roleInfo(
                LoginInfo.getUid(),
                role.id,
                role.merchant_type_id
        ), result -> {
            Bitmap placeholder = BitmapFactory.decodeResource(mActivity.getResources(), R.mipmap.ic_placeholder);
            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(mActivity.getResources(), placeholder);
            circularBitmapDrawable.setCircular(true);
            Glide.with(mActivity)
                    .load(UrlUtil.getImageUrl(result.data.info.logo_img))
                    .apply(RequestOptions
                            .circleCropTransform()
                            .placeholder(circularBitmapDrawable))
                    .into((ImageView) findViewById(R.id.avatar));
            ((TextView) findViewById(R.id.name)).setText(result.data.info.name);
            ((ScaleRatingBar) findViewById(R.id.ratingBar)).setRating(result.data.info.stars_all);


            if (role.merchant_type_id == User.Role.Type.MALL.typeInt) {
                unpayOrderListEntryBtn.setVisibility(View.VISIBLE);
                unpayOrderListEntryBtn.setOnClickListener(view -> OrderListActivity.start(Order.STATE.DAI_FU_KUAN.ordinal()));
                ((TextView) findViewById(R.id.unpayCount)).setText(result.data.payment);
                unsendOrderEntryBtn.setVisibility(View.VISIBLE);
                unsendOrderEntryBtn2.setVisibility(View.VISIBLE);
                View.OnClickListener unSendOrderListEntryBtnListener =
                        view -> OrderListActivity.start(Order.STATE.DAI_FA_HUO.ordinal());
                unsendOrderEntryBtn.setOnClickListener(unSendOrderListEntryBtnListener);
                unsendOrderEntryBtn2.setOnClickListener(unSendOrderListEntryBtnListener);
                ((TextView) findViewById(R.id.todayOrderCount)).setText(result.data.today);
                ((TextView) findViewById(R.id.unsendCount1)).setText(result.data.deliver);
                ((TextView) findViewById(R.id.unsendCount2)).setText(result.data.deliver);
                ((TextView) findViewById(R.id.finishCount2)).setText(result.data.affirm);
                ((TextView) findViewById(R.id.finishCount1)).setText(result.data.all);
                ((TextView) findViewById(R.id.itemCount)).setText(result.data.manage);
                ((TextView) findViewById(R.id.sendedCount)).setText(result.data.shipments);
                ((TextView) findViewById(R.id.refundCount)).setText(result.data.cancel);
                ((TextView) findViewById(R.id.accountCount))
                        .setText(FormatUtil.getMoneyString(result.data.balance.get(0).money));
                sendedOrderEntryBtn.setVisibility(View.VISIBLE);
                sendedOrderEntryBtn.setOnClickListener(view ->
                        OrderListActivity.start(Order.STATE.YI_FA_HUO.ordinal()));
                View.OnClickListener finishOrderEntryListener = view ->
                        OrderListActivity.start(Order.STATE.YI_PING_JIA.ordinal());
                finishOrderEntryBtn.setOnClickListener(finishOrderEntryListener);
                finishOrderEntryBtn2.setOnClickListener(finishOrderEntryListener);
                refundEntryBtn.setVisibility(View.VISIBLE);
                refundEntryBtn.setOnClickListener(view -> {
                    Intent intent = new Intent(this, RefundOrderListActivity.class);
                    startActivity(intent);
                });
            } else if (role.merchant_type_id == User.Role.Type.HOTEL.typeInt) {

            } else if (role.merchant_type_id == User.Role.Type.RESTAURANT.typeInt) {

            }
        }).subscribe();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initScrollView() {
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == Activity.RESULT_OK) {
            loadRoleInfo();
        }
    }

    private void bindView(UserT userT, User.Role role) {
        View todayFinishEntryBtn = findViewById(R.id.todayFinishEntryBtn);
        View cancelOrderEntryBtn = findViewById(R.id.cancelOrderEntryBtn);
        TextView cancelOrderCountTxt = findViewById(R.id.cancelCount);
        if (role.merchant_type_id == User.Role.Type.HOTEL.typeInt) {
            View.OnClickListener finishOrderEntryListener2 = view -> {
                Intent intent = new Intent(this, FinishHotelOrderListActivity.class);
                intent.putExtra(KEY_STATE, 4);
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

        } else if (role.merchant_type_id == User.Role.Type.RESTAURANT.typeInt) {
            ((TextView) findViewById(R.id.todayOrderTxt)).setText("今日预约");
            todayFinishEntryBtn.setVisibility(View.VISIBLE);
            todayFinishEntryBtn.setOnClickListener(view ->
                    startActivity(new Intent(UserCenterActivity.this, DishOrderListActivityActivity.class)));
            findViewById(R.id.dateOrderEntryBtn).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.dateOrderCount)).setText(userT.dateOrderCount + "");
            cancelOrderEntryBtn.setVisibility(View.VISIBLE);

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

    public static void start(User.Role role) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_ROLE, role);
        ActivityUtils.startActivity(bundle, UserCenterActivity.class);
    }
}
