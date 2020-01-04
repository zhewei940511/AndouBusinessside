package com.zskjprojectj.andoubusinessside.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.activity.EditPriceActivity;
import com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity;
import com.zskjprojectj.andoubusinessside.activity.ReviewDetailActivity;
import com.zskjprojectj.andoubusinessside.activity.SendActivity;
import com.zskjprojectj.andoubusinessside.adapter.OrderListAdapter;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.app.BaseFragment;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.PageLoadUtil;

import butterknife.BindView;

public class OrderFragment extends BaseFragment {
    private Order.STATE state;
    private OrderListAdapter adapter = new OrderListAdapter();

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public OrderFragment(Order.STATE state) {
        this.state = state;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter.setOnItemChildClickListener((adapter1, view1, position) -> {
            Order order = adapter.getItem(position);
            switch (view1.getId()) {
                case R.id.controlBtn:
                    switch (Order.STATE.findState(order.status)) {
                        case DAI_FU_KUAN:
                            EditPriceActivity.start(getActivity(), order, 666);
                            break;
                        case DAI_FA_HUO:
                            SendActivity.start(getActivity(), order, 666);
                            break;
                        case YI_PING_JIA:
//                        case 10:
                            ReviewDetailActivity.start(getActivity(), order, 666);
                            break;
//                        case 5:
//                            intent = new Intent(getActivity(), RefundActivity.class);
//                            intent.putExtra(KEY_ORDER, adapter.getItem(position));
//                            startActivity(intent);
//                            break;
//                        case 8:
//                        case 9:
//                            intent = new Intent(getActivity(), HotelOrderDetailActivity.class);
//                            intent.putExtra(KEY_ORDER, adapter.getItem(position));
//                            startActivity(intent);
//                            break;
                        default:
                            OrderInfoActivity.start(getActivity(), order, 666);
                            break;
                    }
                    break;
                case R.id.orderInfoEntryBtn:
//                    if (state.stateInt == 10) {
//                        intent = new Intent(getActivity(), HotelOrderDetailActivity.class);
//                        intent.putExtra(KEY_ORDER, adapter.getItem(position));
//                        startActivityForResult(intent, 666);
//                    } else {
                    OrderInfoActivity.start(getActivity(), adapter.getItem(position), 666);
//                    }
                    break;
            }
        });
        PageLoadUtil<Order> pageLoadUtil = PageLoadUtil.get((BaseActivity) getActivity(), recyclerView, adapter, refreshLayout);
        pageLoadUtil.load(() -> ApiUtils.getApiService().orderList(
                LoginInfo.getUid(),
                LoginInfo.getMerchantId(),
                LoginInfo.getMerchantTypeId(),
                state.stateInt,
                pageLoadUtil.page
        ));
    }

    public void refresh() {
        refreshLayout.autoRefresh();
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_order_list;
    }
}
