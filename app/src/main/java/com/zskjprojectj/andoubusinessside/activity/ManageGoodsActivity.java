package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.adapter.BaseAdapter;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.Goods;
import com.zskjprojectj.andoubusinessside.model.UserT;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ScreenUtil;
import com.zskjprojectj.andoubusinessside.utils.UserUtil;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;

public class ManageGoodsActivity extends BaseActivity {
    ManageGoodsAdapter adapter = new ManageGoodsAdapter();

    @BindView(R.id.selectedAllCbx)
    CheckBox selectedAllCbx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter.bindToRecyclerView(findViewById(R.id.recyclerView));
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener =
                (buttonView, isChecked) -> adapter.setSelectedAll(isChecked);
        adapter.onSelectedStateChangedListener = () -> {
            selectedAllCbx.setOnCheckedChangeListener(null);
            selectedAllCbx.setChecked(adapter.isSelectedAll);
            selectedAllCbx.setOnCheckedChangeListener(onCheckedChangeListener);
        };
        selectedAllCbx.setOnCheckedChangeListener(onCheckedChangeListener);
        ArrayList<Goods> data = new ArrayList<>();
        for (int i = 0; i < 48; i++) {
            Goods goods = new Goods();
            goods.setCount(new Random().nextInt());
            goods.setDescription("这是商品描述吗？");
            goods.setIcon("https://himg2.huanqiucdn.cn/attachment2010/2019/1214/20191214071048532.jpg");
            goods.setPrice(new Random().nextFloat());
            goods.setSpec("120g");
            goods.setTitle("云南竹荪干货竹笙特级笙特级笙特级笙特级笙特级笙特级");
            goods.setState("正常");
            data.add(goods);
        }
        adapter.setNewData(data);
        adapter.loadMoreEnd();
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

    @Override
    protected int getContentView() {
        return R.layout.activity_manage_goods;
    }

    public class ManageGoodsAdapter extends BaseAdapter<Goods> {

        public ManageGoodsAdapter() {
            super(R.layout.layout_goods_list_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, Goods item) {
            helper.setText(R.id.titleTxt, item.getTitle())
                    .setText(R.id.descriptionTxt, item.getDescription())
                    .setText(R.id.priceTxt, FormatUtil.getMoneyString(item.getPrice()))
                    .setText(R.id.specTxt, item.getSpec())
                    .setText(R.id.countTxt, item.getCount() + "")
                    .setChecked(R.id.checkbox, selectMap.get(item));
            Glide.with(helper.itemView.getContext())
                    .load(item.getIcon())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(helper.itemView.getContext(), 2))))
                    .into((ImageView) helper.itemView.findViewById(R.id.iconImg));
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelected(item, !selectMap.get(item));
                }
            });
        }
    }
}
