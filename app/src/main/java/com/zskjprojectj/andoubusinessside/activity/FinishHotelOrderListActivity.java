package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;

import com.zskjprojectj.andoubusinessside.R;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.fragment.OrderFragment;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zhuosongkj.android.library.util.ActionBarUtil;

public class FinishHotelOrderListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "已完成订单");
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new OrderFragment(Order.STATE.YI_PING_JIA))
                .commitAllowingStateLoss();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_finish_hotel_order_list;
    }
}
