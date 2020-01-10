package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ActivityUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.zskjprojectj.andoubusinessside.R;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.fragment.OrderFragment;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zhuosongkj.android.library.util.ActionBarUtil;

import java.util.ArrayList;

public class OrderListActivity extends BaseActivity {

    public static final String KEY_STATE = "KEY_STATE";
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "全部订单");
        SlidingTabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(4);
        fragments.add(new OrderFragment(Order.STATE.ALL));
        fragments.add(new OrderFragment(Order.STATE.DAI_FU_KUAN));
        fragments.add(new OrderFragment(Order.STATE.DAI_FA_HUO));
        fragments.add(new OrderFragment(Order.STATE.YI_FA_HUO));
        fragments.add(new OrderFragment(Order.STATE.YI_PING_JIA));
        tabLayout.setViewPager(viewPager
                , new String[]{"全部", "待付款", "待发货", "已发货", "已评价"}
                , this, fragments);
        tabLayout.setTabSpaceEqual(true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ActionBarUtil.setTitle(mActivity, "全部订单");
                        break;
                    case 1:
                        ActionBarUtil.setTitle(mActivity, "待付款订单");
                        break;
                    case 2:
                        ActionBarUtil.setTitle(mActivity, "待发货订单");
                        break;
                    case 3:
                        ActionBarUtil.setTitle(mActivity, "已发货订单");
                        break;
                    case 4:
                        ActionBarUtil.setTitle(mActivity, "已评价订单");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setCurrentTab(getIntent().getIntExtra(KEY_STATE, 0));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == Activity.RESULT_OK) {
            for (Fragment fragment : fragments) {
                ((OrderFragment) fragment).refresh();
            }
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_order_list;
    }

    public static void start(int state) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_STATE, state);
        ActivityUtils.startActivity(bundle, OrderListActivity.class);
    }
}
