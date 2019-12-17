package com.zskjprojectj.andoubusinessside.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.activity.EditPriceActivity;
import com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity;
import com.zskjprojectj.andoubusinessside.activity.RefundActivity;
import com.zskjprojectj.andoubusinessside.activity.ReviewDetailActivity;
import com.zskjprojectj.andoubusinessside.activity.SendActivity;
import com.zskjprojectj.andoubusinessside.base.BaseFragment;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ScreenUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static com.zskjprojectj.andoubusinessside.activity.OrderInfoActivity.KEY_ORDER;

public class OrderFragment extends BaseFragment {
    private int state;
    private OrderListAdapter adapter;

    public OrderFragment(int state) {
        this.state = state;
        adapter = new OrderListAdapter(state, R.layout.layout_order_list_item);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        adapter.bindToRecyclerView(view.findViewById(R.id.recyclerview));
        adapter.setOnItemChildClickListener((adapter, view1, position) -> {
            Intent intent;
            switch (view1.getId()) {
                case R.id.orderInfoControlBtn:
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
                            intent = new Intent(getActivity(), ReviewDetailActivity.class);
                            intent.putExtra(KEY_ORDER, (Serializable) adapter.getItem(position));
                            startActivity(intent);
                            break;
                        case 5:
                            intent = new Intent(getActivity(), RefundActivity.class);
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
                    intent = new Intent(getActivity(), OrderInfoActivity.class);
                    intent.putExtra(KEY_ORDER, (Serializable) adapter.getItem(position));
                    startActivityForResult(intent, 666);
                    break;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO 修改价格刷新列表项
        //TODO 发货刷新列表项
    }

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected void getDataFromServer() {

    }

    @Override
    protected void initData() {
        ArrayList<Order> orderInfos = new ArrayList<>();
        orderInfos.add(getOrderInfo());
        orderInfos.add(getOrderInfo());
        orderInfos.add(getOrderInfo());
        orderInfos.add(getOrderInfo());
        orderInfos.add(getOrderInfo());
        orderInfos.add(getOrderInfo());
        orderInfos.add(getOrderInfo());
        orderInfos.add(getOrderInfo());
        orderInfos.add(getOrderInfo());
        adapter.setNewData(orderInfos);
    }

    private Order getOrderInfo() {
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
                info.setState("待审核");
                break;
            case 6:
                info.setState("退货退款成功");
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

    class OrderListAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {
        private int state;

        public OrderListAdapter(int state, int layoutResId) {
            super(layoutResId);
            this.state = state;
        }

        @Override
        protected void convert(BaseViewHolder helper, Order item) {
            helper.setText(R.id.numTxt, item.getNum());
            helper.setText(R.id.stateTxt, item.getState());
            helper.setText(R.id.titleTxt, item.getTitle());
            helper.setText(R.id.specTxt, item.getSpec());
            helper.setText(R.id.countTxt, item.getCount() + "");
            helper.setText(R.id.priceTxt, FormatUtil.getMoneyString(item.getPrice()));
            helper.setText(R.id.totalTxt, FormatUtil.getMoneyString(item.getTotal()));
            String controlStr = "";
            switch (state) {
                case 0:
                case 5:
                    controlStr = "查看详情";
                    break;
                case 1:
                    controlStr = "修改价格";
                    break;
                case 2:
                    controlStr = "去发货";
                    helper.setVisible(R.id.orderInfoEntryBtn, true);
                    helper.addOnClickListener(R.id.orderInfoEntryBtn);
                    break;
                case 3:
                    controlStr = "订单详情";
                    break;
                case 4:
                    controlStr = "评价详情";
                    break;
                case 6:
                    controlStr = "退款详情";
                    break;
                default:
                    helper.setVisible(R.id.orderInfoControlBtn, false);
                    break;
            }
            helper.setText(R.id.orderInfoControlBtn, controlStr);
            Glide.with(getActivity())
                    .load(item.getIcon())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(getActivity(), 2))))
                    .into((ImageView) helper.itemView.findViewById(R.id.iconImg));
            helper.addOnClickListener(R.id.orderInfoControlBtn);
        }
    }
}
