package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.fragment.OrderFragment;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;

import java.util.ArrayList;

public class OrderListActivity extends BaseActivity {

    public static final String KEY_ORDER_TYPE = "KEY_ORDER_TYPE";
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "全部订单");
        SlidingTabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(4);
        fragments.add(new OrderFragment(0));
        fragments.add(new OrderFragment(1));
        fragments.add(new OrderFragment(2));
        fragments.add(new OrderFragment(3));
        fragments.add(new OrderFragment(4));
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
        tabLayout.setCurrentTab(getIntent().getIntExtra(KEY_ORDER_TYPE, 0));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_order_list;
    }
}
