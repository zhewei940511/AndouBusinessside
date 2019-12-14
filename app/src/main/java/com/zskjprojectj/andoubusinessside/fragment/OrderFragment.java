package com.zskjprojectj.andoubusinessside.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.base.BaseFragment;
import com.zskjprojectj.andoubusinessside.model.OrderInfo;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.Random;

public class OrderFragment extends BaseFragment {

    private OrderListAdapter adapter = new OrderListAdapter(R.layout.layout_order_list_item);

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        adapter.bindToRecyclerView(view.findViewById(R.id.recyclerview));
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
        ArrayList<OrderInfo> orderInfos = new ArrayList<>();
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

    private OrderInfo getOrderInfo() {
        OrderInfo info = new OrderInfo();
        info.setCount(new Random().nextInt(100));
        info.setIcon("https://himg2.huanqiucdn.cn/attachment2010/2019/1214/20191214071048532.jpg");
        info.setNum(new Random().nextInt(1000000000) + "");
        info.setPrice(new Random().nextFloat());
        info.setState("代付款");
        info.setSpec("四件套");
        info.setTitle("亲润孕妇化妆品套装BB霜 遮瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护瑕孕妇护");
        info.setTotal(new Random().nextFloat());
        return info;
    }

    class OrderListAdapter extends BaseQuickAdapter<OrderInfo, BaseViewHolder> {
        public OrderListAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, OrderInfo item) {
            helper.setText(R.id.numTxt, item.getNum());
            helper.setText(R.id.stateTxt, item.getState());
            helper.setText(R.id.titleTxt, item.getTitle());
            helper.setText(R.id.specTxt, item.getSpec());
            helper.setText(R.id.countTxt, item.getCount() + "");
            helper.setText(R.id.priceTxt, FormatUtil.getMoneyString(item.getPrice()));
            helper.setText(R.id.totalTxt, FormatUtil.getMoneyString(item.getTotal()));
            Glide.with(getActivity())
                    .load(item.getIcon())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(getActivity(), 2))))
                    .into((ImageView) helper.itemView.findViewById(R.id.iconImg));

        }
    }
}
