package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.BarUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.zskjprojectj.andoubusinessside.R;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.fragment.DealDetailListFragment;
import com.zhuosongkj.android.library.util.ActionBarUtil;

import java.util.ArrayList;

public class WalletActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.transparentStatusBar(mActivity);
        ActionBarUtil.setTitle(mActivity, "我的钱包", false);
        SlidingTabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(4);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DealDetailListFragment(0));
        fragments.add(new DealDetailListFragment(1));
        tabLayout.setTabSpaceEqual(true);
        tabLayout.setViewPager(viewPager, new String[]{"余额明细", "提现明细"}, this, fragments);
        findViewById(R.id.cashOutEntryBtn).setOnClickListener(view -> startActivity(new Intent(WalletActivity.this, CashOutActivity.class)));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_wallet;
    }
}
