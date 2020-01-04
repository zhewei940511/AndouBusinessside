package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.OrderT;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;

import java.util.Random;

import static com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity.KEY_ORDER;

public class DishOrderListActivityActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "取消订单");
        DishOrderListAdapter adapter = new DishOrderListAdapter(R.layout.layout_dish_order_list_item);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Intent intent = new Intent(DishOrderListActivityActivity.this, DateOrderDetailActivity.class);
            intent.putExtra(KEY_ORDER, adapter.getItem(position));
            startActivity(intent);
        });
        adapter.bindToRecyclerView(findViewById(R.id.recyclerView));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
        adapter.addData(getOrder(11));
    }

    public static OrderT getOrder(int state) {
        OrderT info = new OrderT();
        switch (state) {
            case 1:
                info.setState("待付款");
                break;
            case 2:
                info.setState("待发货");
                break;
            case 3:
                info.setState("已发货");
                break;
            case 4:
                info.setState("已评价");
                break;
            case 5:
            case 9:
                info.setState("待审核");
                break;
            case 6:
                info.setState("退货退款成功");
                break;
            case 7:
                info.setState("待入住");
                break;
            case 8:
                info.setState("已取消");
                break;
            case 10:
                info.setState("已完成");
                break;
        }
        info.setCount(new Random().nextInt(100));
        info.setIcon("https://himg2.huanqiucdn.cn/attachment2010/2019/1214/20191214071048532.jpg");
        info.setNum(new Random().nextInt(1000000000) + "");
        info.setPrice(new Random().nextFloat());
        info.setSpec("四件套");
        info.setTitle("亲润孕妇化妆品套装BB霜 遮瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护");
        info.setTotal(new Random().nextFloat());
        info.setDate(System.currentTimeMillis());
        info.setReceiver("王杨");
        info.setAddr("重庆市南岸区亚太路9号就系国际6栋10-8");
        info.setMobile("13388888888");
        info.setScore(new Random().nextFloat());
        info.setFreight(new Random().nextFloat());
        return info;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_cancel_dish_order_list_activity;
    }

    class DishOrderListAdapter extends BaseQuickAdapter<OrderT, BaseViewHolder> {
        public DishOrderListAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, OrderT item) {
            helper.addOnClickListener(R.id.orderDetailEntryBtn);
        }
    }
}
