package com.zskjprojectj.andoubusinessside.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zhuosongkj.android.library.app.BaseFragment;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.model.DealDetail;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zhuosongkj.android.library.util.PageLoadUtil;

public class DealDetailListFragment extends BaseFragment {
    private int state;
    private DealDetailListAdapter adapter = new DealDetailListAdapter();

    public DealDetailListFragment(int state) {
        this.state = state;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PageLoadUtil<DealDetail> pageLoadUtil = PageLoadUtil.get(mActivity,
                view.findViewById(R.id.recyclerView),
                adapter,
                view.findViewById(R.id.refreshLayout));
        pageLoadUtil.load(() -> ApiUtils.getApiService().balanceDetail(
                LoginInfo.getUid(),
                LoginInfo.getMerchantId(),
                LoginInfo.getMerchantTypeId(),
                state,
                pageLoadUtil.page
        ));
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_order_list;
    }

    class DealDetailListAdapter extends BaseQuickAdapter<DealDetail, BaseViewHolder> {

        public DealDetailListAdapter() {
            super(R.layout.layout_deal_detail_list_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, DealDetail item) {
            helper.setText(R.id.titleTxt, item.msg)
                    .setText(R.id.dateTxt, item.created)
                    .setText(R.id.totalTxt, FormatUtil.getMoneyString(item.price));
        }
    }
}
