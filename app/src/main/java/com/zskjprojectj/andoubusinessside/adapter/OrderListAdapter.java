package com.zskjprojectj.andoubusinessside.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.UiUtil;

public class OrderListAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {

    public OrderListAdapter() {
        super(R.layout.layout_order_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        UiUtil.bindOrderGoods(item, helper.itemView.findViewById(R.id.goodsContainer));
        helper.setText(R.id.numTxt, item.order_sn)
                .setText(R.id.stateTxt, getStateStr(item))
                .setText(R.id.totalTxt, FormatUtil.getMoneyString(item.order_money))
                .setGone(R.id.controlBtn, true);
        String controlStr = "";
        switch (Order.STATE.findState(item.status)) {
//            case 0:
//            case 5:
//            case 9:
//            case 8:
//                controlStr = "查看详情";
//                break;
            case DAI_FU_KUAN:
                controlStr = "修改价格";
                break;
            case DAI_FA_HUO:
                controlStr = "去发货";
                break;
            case YI_PING_JIA:
//            case 10:
                controlStr = "评价详情";
                break;
//            case 6:
//                controlStr = "退款详情";
//                break;
            default:
                helper.setGone(R.id.controlBtn, false);
                break;
        }
        helper.setText(R.id.controlBtn, controlStr);
        helper.addOnClickListener(R.id.controlBtn)
                .addOnClickListener(R.id.orderInfoEntryBtn);
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
