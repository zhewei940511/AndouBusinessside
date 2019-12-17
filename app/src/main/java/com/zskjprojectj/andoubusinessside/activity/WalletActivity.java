package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.fragment.DealDetailListFragment;

import java.util.ArrayList;

public class WalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        View progress = findViewById(R.id.progressBar);
        progress.postDelayed(() -> progress.setVisibility(View.GONE), 1000);
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
}
