package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.fragment.OrderFragment;
import com.zskjprojectj.andoubusinessside.model.Order;

import static com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity.KEY_ORDER;

public class DishOrderListActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_dish_order_list_activity);
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("取消订单");
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        DishOrderListAdapter adapter = new DishOrderListAdapter(R.layout.layout_dish_order_list_item);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Intent intent = new Intent(DishOrderListActivityActivity.this, DateOrderDetailActivity.class);
            intent.putExtra(KEY_ORDER, adapter.getItem(position));
            startActivity(intent);
        });
        adapter.bindToRecyclerView(findViewById(R.id.recyclerView));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
        adapter.addData(OrderFragment.getOrder(11));
    }

    class DishOrderListAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {
        public DishOrderListAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Order item) {
            helper.addOnClickListener(R.id.orderDetailEntryBtn);
        }
    }
}
