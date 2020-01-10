package com.zskjprojectj.andoubusinessside.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuosongkj.android.library.adapter.BaseAdapter;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.Item;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;
import com.zskjprojectj.andoubusinessside.utils.ScreenUtil;
import com.zskjprojectj.andoubusinessside.utils.UrlUtil;

public class ManageGoodsAdapter extends BaseAdapter<Item> {
    public ManageGoodsAdapter() {
        super(R.layout.layout_goods_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, Item item) {
        helper.setText(R.id.titleTxt, item.name)
                .setText(R.id.descriptionTxt, item.desc)
                .setText(R.id.priceTxt, FormatUtil.getMoneyString(item.price))
                .setText(R.id.specTxt, Item.getSpec(item.attr_value))
                .setText(R.id.countTxt, item.store_num)
                .setChecked(R.id.checkbox, selectMap.get(item));
        Glide.with(helper.itemView.getContext())
                .load(UrlUtil.getImageUrl(item.img))
                .apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(helper.itemView.getContext(), 2)))
                        .placeholder(R.mipmap.ic_placeholder))
                .into((ImageView) helper.itemView.findViewById(R.id.iconImg));
        helper.itemView.setOnClickListener(v -> setSelected(item, !selectMap.get(item)));
        helper.setText(R.id.stateTxt, item.is_sale == Item.STATE_ON ? "上架" : "下架");
    }
}
