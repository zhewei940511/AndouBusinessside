package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.fragment.OrderFragment;

import java.util.ArrayList;

public class CancelOrderListActivity extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order_list);
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("已取消订单");
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        SlidingTabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        fragments.add(new OrderFragment(9));
        fragments.add(new OrderFragment(8));
        tabLayout.setTabSpaceEqual(true);
        tabLayout.setViewPager(viewPager, new String[]{"待审核", "已取消"}, this, fragments);
    }
}
