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

public class RefundOrderListActivity extends BaseActivity {

    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "退款订单");
        SlidingTabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        fragments.add(new OrderFragment(5));
        fragments.add(new OrderFragment(6));
        tabLayout.setTabSpaceEqual(true);
        tabLayout.setViewPager(viewPager, new String[]{"待审核", "已退款"}, this, fragments);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_order_list;
    }
}
