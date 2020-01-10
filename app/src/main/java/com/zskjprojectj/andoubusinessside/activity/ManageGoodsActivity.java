package com.zskjprojectj.andoubusinessside.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.adapter.ManageGoodsAdapter;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.Item;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zskjprojectj.andoubusinessside.model.UserT;
import com.zhuosongkj.android.library.util.ActionBarUtil;
import com.zhuosongkj.android.library.util.PageLoadUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "商品管理");
        ActionBarUtil.setRightAction(mActivity, "商品分类", v -> CategoryActivity.start());
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
        String selectedIds = getSelectedIds();
        if (TextUtils.isEmpty(selectedIds)) {
            ToastUtil.showToast("请选择要删除的商品!");
            return;
        }
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
                                    adapter.setSelectedAll(false);
                                }).subscribe()).show();
    }

    @OnClick(R.id.goodsOnBtn)
    void onGoodsOnBtnClick() {
        String selectedIds = getSelectedIds();
        if (TextUtils.isEmpty(selectedIds)) {
            ToastUtil.showToast("请选择要上架的商品!");
            return;
        }
        new AlertDialog.Builder(mActivity)
                .setTitle("上架商品")
                .setMessage("确定上架这些商品吗？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) ->
                        HttpRxObservable.getObservable(mActivity, true, false
                                , ApiUtils.getApiService().goodsOn(
                                        LoginInfo.getUid(),
                                        getSelectedIds()
                                ), result -> {
                                    for (Item item : adapter.selectMap.keySet()) {
                                        if (adapter.selectMap.get(item)) {
                                            item.is_sale = Item.STATE_ON;
                                            adapter.notifyItemChanged(adapter.getData().indexOf(item));
                                        }
                                    }
                                    adapter.setSelectedAll(false);
                                    ToastUtil.showToast(result.getMsg());
                                }).subscribe()).show();
    }

    @OnClick(R.id.goodsOffBtn)
    void onGoodsOffBtnClick() {
        String selectedIds = getSelectedIds();
        if (TextUtils.isEmpty(selectedIds)) {
            ToastUtil.showToast("请选择要下架的商品!");
            return;
        }
        new AlertDialog.Builder(mActivity)
                .setTitle("下架商品")
                .setMessage("确定下架这些商品吗？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) ->
                        HttpRxObservable.getObservable(mActivity, true, false
                                , ApiUtils.getApiService().goodsOff(
                                        LoginInfo.getUid(),
                                        getSelectedIds()
                                ), result -> {
                                    for (Item item : adapter.selectMap.keySet()) {
                                        if (adapter.selectMap.get(item)) {
                                            item.is_sale = Item.STATE_OFF;
                                            adapter.notifyItemChanged(adapter.getData().indexOf(item));
                                        }
                                    }
                                    adapter.setSelectedAll(false);
                                    ToastUtil.showToast(result.getMsg());
                                }).subscribe()).show();
    }

    private String getSelectedIds() {
        StringBuilder builder = new StringBuilder();
        for (Item item : adapter.selectMap.keySet()) {
            if (adapter.selectMap.get(item)) {
                builder.append(item.id).append(",");
            }
        }
        if (TextUtils.isEmpty(builder)) {
            return "";
        }
        return builder.substring(0, builder.length() - 1);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_manage_goods;
    }
}
