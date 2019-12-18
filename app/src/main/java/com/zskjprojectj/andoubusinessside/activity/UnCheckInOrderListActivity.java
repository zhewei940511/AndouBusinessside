package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.adapter.OrderListAdapter;
import com.zskjprojectj.andoubusinessside.fragment.OrderFragment;
import com.zskjprojectj.andoubusinessside.model.Order;

import java.util.ArrayList;

import static com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity.KEY_ORDER;

public class UnCheckInOrderListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_check_in_order_list);
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("待入住订单");
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        OrderListAdapter adapter = new OrderListAdapter(7, R.layout.layout_order_list_item);
        adapter.bindToRecyclerView(findViewById(R.id.recyclerView));
        ArrayList<Order> data = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            data.add(OrderFragment.getOrder(7));
        }
        adapter.setNewData(data);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            if (view.getId() == R.id.controlBtn) {
                Intent intent = new Intent(UnCheckInOrderListActivity.this, HotelOrderDetailActivity.class);
                intent.putExtra(KEY_ORDER, adapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}
