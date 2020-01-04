package com.zskjprojectj.andoubusinessside.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.adapter.ManageGoodsAdapter;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.Item;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.UserT;
import com.zskjprojectj.andoubusinessside.utils.PageLoadUtil;
import com.zskjprojectj.andoubusinessside.utils.UserUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ManageGoodsActivity extends BaseActivity {
    ManageGoodsAdapter adapter = new ManageGoodsAdapter();

    @BindView(R.id.selectedAllCbx)
    CheckBox selectedAllCbx;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @OnClick(R.id.backBtn)
    void onBackBtnClick() {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener =
                (buttonView, isChecked) -> adapter.setSelectedAll(isChecked);
        adapter.onSelectedStateChangedListener = () -> {
            selectedAllCbx.setOnCheckedChangeListener(null);
            selectedAllCbx.setChecked(adapter.isSelectedAll);
            selectedAllCbx.setOnCheckedChangeListener(onCheckedChangeListener);
        };
        selectedAllCbx.setOnCheckedChangeListener(onCheckedChangeListener);
        PageLoadUtil<Item> pageLoadUtil = PageLoadUtil.get(mActivity, recyclerView, adapter, refreshLayout);
        pageLoadUtil.load(() -> ApiUtils.getApiService().itemList(
                LoginInfo.getUid(),
                LoginInfo.getMerchantId(),
                LoginInfo.getMerchantTypeId(),
                pageLoadUtil.page
        ));
        findViewById(R.id.goodsCategoryEntryBtn)
                .setOnClickListener(view -> CategoryActivity.start());
        findViewById(R.id.newGoodsBtn)
                .setOnClickListener(view -> {
                    if (UserUtil.getInstance().userT.currentType == UserT.Type.MALL) {
                        startActivity(new Intent(ManageGoodsActivity.this, NewGoodsActivity.class));
                    } else if (UserUtil.getInstance().userT.currentType == UserT.Type.HOTEL) {
                        startActivity(new Intent(ManageGoodsActivity.this, NewRoomActivity.class));
                    } else if (UserUtil.getInstance().userT.currentType == UserT.Type.RESTAURANT) {
                        startActivity(new Intent(ManageGoodsActivity.this, NewDishActivity.class));
                    }
                });
    }

    @OnClick(R.id.deleteBtn)
    void onDeleteBtnClick() {
        new AlertDialog.Builder(mActivity)
                .setTitle("删除商品")
                .setMessage("确定删除这些商品吗？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) ->
                        HttpRxObservable.getObservable(mActivity, true, false
                                , ApiUtils.getApiService().delItem(
                                        LoginInfo.getUid(),
                                        getSelectedIds()
                                ), result -> {
                                    for (Item item : adapter.selectMap.keySet()) {
                                        if (adapter.selectMap.get(item)) {
                                            adapter.remove(adapter.getData().indexOf(item));
                                        }
                                    }
                                }).subscribe()).show();
    }

    private String getSelectedIds() {
        StringBuilder builder = new StringBuilder();
        for (Item item : adapter.selectMap.keySet()) {
            if (adapter.selectMap.get(item)) {
                builder.append(item.id).append(",");
            }
        }
        return builder.substring(0, builder.length() - 1);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_manage_goods;
    }
}
