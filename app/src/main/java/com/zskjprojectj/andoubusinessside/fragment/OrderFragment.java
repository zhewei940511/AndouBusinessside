package com.zskjprojectj.andoubusinessside.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.activity.EditPriceActivity;
import com.zskjprojectj.andoubusinessside.activity.HotelOrderDetailActivity;
import com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity;
import com.zskjprojectj.andoubusinessside.activity.RefundActivity;
import com.zskjprojectj.andoubusinessside.activity.ReviewDetailActivity;
import com.zskjprojectj.andoubusinessside.activity.SendActivity;
import com.zskjprojectj.andoubusinessside.adapter.OrderListAdapter;
import com.zskjprojectj.andoubusinessside.app.BaseFragment;
import com.zskjprojectj.andoubusinessside.model.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;

import static com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity.KEY_ORDER;

public class OrderFragment extends BaseFragment {
    private int state;
    private OrderListAdapter adapter;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    public OrderFragment(int state) {
        this.state = state;
        adapter = new OrderListAdapter(state, R.layout.layout_order_list_item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter.bindToRecyclerView(view.findViewById(R.id.recyclerView));
        adapter.setOnItemChildClickListener((adapter, view1, position) -> {
            Intent intent;
            switch (view1.getId()) {
                case R.id.controlBtn:
                    switch (state) {
                        case 1:
                            intent = new Intent(getActivity(), EditPriceActivity.class);
                            intent.putExtra(KEY_ORDER, (Serializable) adapter.getItem(position));
                            startActivityForResult(intent, 666);
                            break;
                        case 2:
                            intent = new Intent(getActivity(), SendActivity.class);
                            intent.putExtra(KEY_ORDER, (Serializable) adapter.getItem(position));
                            startActivityForResult(intent, 666);
                            break;
                        case 4:
                        case 10:
                            intent = new Intent(getActivity(), ReviewDetailActivity.class);
                            intent.putExtra(KEY_ORDER, (Serializable) adapter.getItem(position));
                            startActivity(intent);
                            break;
                        case 5:
                            intent = new Intent(getActivity(), RefundActivity.class);
                            intent.putExtra(KEY_ORDER, (Serializable) adapter.getItem(position));
                            startActivity(intent);
                            break;
                        case 8:
                        case 9:
                            intent = new Intent(getActivity(), HotelOrderDetailActivity.class);
                            intent.putExtra(KEY_ORDER, (Serializable) adapter.getItem(position));
                            startActivity(intent);
                            break;
                        default:
                            intent = new Intent(getActivity(), OrderInfoActivity.class);
                            intent.putExtra(KEY_ORDER, (Serializable) adapter.getItem(position));
                            startActivityForResult(intent, 666);
                            break;
                    }
                    break;
                case R.id.orderInfoEntryBtn:
                    if (state == 10) {
                        intent = new Intent(getActivity(), HotelOrderDetailActivity.class);
                        intent.putExtra(KEY_ORDER, (Serializable) adapter.getItem(position));
                        startActivityForResult(intent, 666);
                    } else {
                        intent = new Intent(getActivity(), OrderInfoActivity.class);
                        intent.putExtra(KEY_ORDER, (Serializable) adapter.getItem(position));
                        startActivityForResult(intent, 666);
                    }
                    break;
            }
        });
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableLoadMoreWhenContentNotFull(true);
        refreshLayout.autoLoadMore();
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            ArrayList<Order> orderInfos = new ArrayList<>();
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            adapter.setNewData(orderInfos);
            refreshLayout.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            ArrayList<Order> orderInfos = new ArrayList<>();
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            orderInfos.add(getOrder(state));
            adapter.addData(orderInfos);
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO 修改价格刷新列表项
        //TODO 发货刷新列表项
    }

    public static Order getOrder(int state) {
        Order info = new Order();
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
        return R.layout.fragment_order_list;
    }
}
