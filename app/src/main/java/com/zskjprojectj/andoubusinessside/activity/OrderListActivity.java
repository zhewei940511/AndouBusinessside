package com.zskjprojectj.andoubusinessside.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.fragment.OrderFragment;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {

    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        SlidingTabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        TextView titleTxt = findViewById(R.id.titleTxt);
        viewPager.setOffscreenPageLimit(4);
        fragments.add(new OrderFragment());
        fragments.add(new OrderFragment());
        fragments.add(new OrderFragment());
        fragments.add(new OrderFragment());
        fragments.add(new OrderFragment());
        tabLayout.setViewPager(viewPager
                , new String[]{"全部", "待付款", "待发货", "已发货", "已评价"}
                , this, fragments);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        titleTxt.setText("全部订单");
                        break;
                    case 1:
                        titleTxt.setText("待付款订单");
                        break;
                    case 2:
                        titleTxt.setText("待发货订单");
                        break;
                    case 3:
                        titleTxt.setText("已发货订单");
                        break;
                    case 4:
                        titleTxt.setText("已评价订单");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
