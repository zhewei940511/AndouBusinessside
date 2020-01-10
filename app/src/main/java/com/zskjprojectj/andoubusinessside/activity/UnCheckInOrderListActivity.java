package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.adapter.OrderListAdapter;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.OrderT;
import com.zhuosongkj.android.library.util.ActionBarUtil;

import java.util.ArrayList;

import static com.zskjprojectj.andoubusinessside.activity.OrderDetailActivity.KEY_ORDER;

public class UnCheckInOrderListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "待入住订单");
        OrderListAdapter adapter = new OrderListAdapter();
        adapter.bindToRecyclerView(findViewById(R.id.recyclerView));
        ArrayList<OrderT> data = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            data.add(DishOrderListActivityActivity.getOrder(7));
        }
//        adapter.setNewData(data);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            if (view.getId() == R.id.controlBtn) {
                Intent intent = new Intent(UnCheckInOrderListActivity.this, HotelOrderDetailActivity.class);
                intent.putExtra(KEY_ORDER, adapter.getItem(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_un_check_in_order_list;
    }
}
