package com.zskjprojectj.andoubusinessside.fragment;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.base.BaseFragment;
import com.zskjprojectj.andoubusinessside.model.DealDetail;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;

import java.util.ArrayList;
import java.util.Random;

public class DealDetailListFragment extends BaseFragment {
    private int state;
    private DealDetailListAdapter adapter;

    public DealDetailListFragment(int state) {
        this.state = state;
        adapter = new DealDetailListAdapter(state, R.layout.layout_deal_detail_list_item);
    }

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
        ArrayList<DealDetail> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DealDetail deal = new DealDetail();
            deal.setDate(System.currentTimeMillis());
            deal.setTitle(state == 0 ? "订单完成" : "提现完成");
            deal.setTotal(new Random().nextFloat());
            data.add(deal);
        }
        adapter.setNewData(data);
    }

    class DealDetailListAdapter extends BaseQuickAdapter<DealDetail, BaseViewHolder> {
        private int state;

        public DealDetailListAdapter(int state, int layoutResId) {
            super(layoutResId);
            this.state = state;
        }

        @Override
        protected void convert(BaseViewHolder helper, DealDetail item) {
            helper.setText(R.id.titleTxt, item.getTitle())
                    .setText(R.id.dateTxt, FormatUtil.getDateString2(item.getDate()))
                    .setText(R.id.totalTxt, FormatUtil.getMoneyString(item.getTotal()));
        }
    }
}
