package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.fragment.OrderFragment;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;

public class FinishHotelOrderListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "已完成订单");
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new OrderFragment(10))
                .commitAllowingStateLoss();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_finish_hotel_order_list;
    }
}
