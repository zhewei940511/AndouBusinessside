package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.fragment.OrderFragment;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;

import java.util.ArrayList;

public class CancelOrderListActivity extends BaseActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "已取消订单");
        SlidingTabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        fragments.add(new OrderFragment(Order.STATE.YI_PING_JIA));
        fragments.add(new OrderFragment(Order.STATE.YI_PING_JIA));
        tabLayout.setTabSpaceEqual(true);
        tabLayout.setViewPager(viewPager, new String[]{"待审核", "已取消"}, this, fragments);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_cancel_order_list;
    }
}
