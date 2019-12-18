package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.Goods;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ScreenUtil;
import com.zskjprojectj.andoubusinessside.utils.UserUtil;

import java.util.ArrayList;
import java.util.Random;

public class ManageGoodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_goods);
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        GoodsAdapter adapter = new GoodsAdapter(R.layout.layout_goods_list_item);
        adapter.bindToRecyclerView(findViewById(R.id.recyclerView));
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
        findViewById(R.id.goodsCategoryEntryBtn)
                .setOnClickListener(view ->
                        startActivity(new Intent(ManageGoodsActivity.this, GoodsCategoryActivity.class)));
        findViewById(R.id.newGoodsBtn)
                .setOnClickListener(view -> {
                    if (UserUtil.user.getType() == 0) {
                        startActivity(new Intent(ManageGoodsActivity.this, NewGoodsActivity.class));
                    } else if (UserUtil.user.getType() == 1) {
                        startActivity(new Intent(ManageGoodsActivity.this, NewRoomActivity.class));
                    }
                });
    }

    class GoodsAdapter extends BaseQuickAdapter<Goods, BaseViewHolder> {

        public GoodsAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Goods item) {
            helper.setText(R.id.titleTxt, item.getTitle())
                    .setText(R.id.descriptionTxt, item.getDescription())
                    .setText(R.id.priceTxt, FormatUtil.getMoneyString(item.getPrice()))
                    .setText(R.id.specTxt, item.getSpec())
                    .setText(R.id.countTxt, item.getCount() + "");
            Glide.with(helper.itemView.getContext())
                    .load(item.getIcon())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(helper.itemView.getContext(), 2))))
                    .into((ImageView) helper.itemView.findViewById(R.id.iconImg));
        }
    }
}
