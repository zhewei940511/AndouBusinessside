package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.fragment.OrderFragment;

public class FinishHotelOrderListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_hotel_order_list);
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("已完成订单");
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new OrderFragment(10))
                .commitAllowingStateLoss();
    }
}
