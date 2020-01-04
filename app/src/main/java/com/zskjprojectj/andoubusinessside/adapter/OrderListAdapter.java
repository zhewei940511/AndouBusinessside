package com.zskjprojectj.andoubusinessside.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ScreenUtil;
import com.zskjprojectj.andoubusinessside.utils.UrlUtil;

public class OrderListAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {
    private Order.STATE state;

    public OrderListAdapter(Order.STATE state) {
        super(R.layout.layout_order_list_item);
        this.state = state;
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        helper.setText(R.id.numTxt, item.order_id);
        helper.setText(R.id.stateTxt, getStateStr(item));
        helper.setText(R.id.titleTxt, item.name);
        helper.setText(R.id.specTxt, item.getSpec());
        helper.setText(R.id.countTxt, item.num);
        helper.setText(R.id.priceTxt, FormatUtil.getMoneyString(item.price));
        helper.setText(R.id.totalTxt, FormatUtil.getMoneyString(item.pay_money));
        String controlStr = "";
//        switch (state.stateInt) {
//            case 0:
//            case 5:
//            case 9:
//            case 8:
//                controlStr = "查看详情";
//                break;
//            case 1:
//                controlStr = "修改价格";
//                break;
//            case 2:
//                controlStr = "去发货";
//                helper.setVisible(R.id.orderInfoEntryBtn, true);
//                helper.addOnClickListener(R.id.orderInfoEntryBtn);
//                break;
//            case 3:
//                controlStr = "订单详情";
//                break;
//            case 4:
//            case 10:
//                controlStr = "评价详情";
//                helper.setVisible(R.id.orderInfoEntryBtn, true);
//                helper.addOnClickListener(R.id.orderInfoEntryBtn);
//                break;
//            case 6:
//                controlStr = "退款详情";
//                break;
//            default:
//                helper.setVisible(R.id.controlBtn, false);
//                break;
//        }
        helper.setText(R.id.controlBtn, controlStr);
        Glide.with(helper.itemView.getContext())
                .load(UrlUtil.getImageUrl(item.img))
                .apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(helper.itemView.getContext(), 2)))
                        .placeholder(R.mipmap.ic_placeholder))
                .into((ImageView) helper.itemView.findViewById(R.id.iconImg));
        helper.addOnClickListener(R.id.controlBtn);
    }

    private String getStateStr(Order item) {
        for (Order.STATE state : Order.STATE.values()) {
            if (item.status == state.stateInt) {
                return state.stateStr;
            }
        }
        return "";
    }
}
