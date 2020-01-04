package com.zskjprojectj.andoubusinessside.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.Item;
import com.zskjprojectj.andoubusinessside.model.Order;

public class UiUtil {
    public static void bindOrderGoods(Order item, ViewGroup goodsContainer) {
        goodsContainer.removeAllViews();
        if (ListUtil.isEmpty(item.details)) return;
        for (Order.Goods good : item.details) {
            View goodsView = LayoutInflater.from(
                    goodsContainer.getContext()).inflate(R.layout.layout_order_goods_item, null);
            ((TextView) goodsView.findViewById(R.id.titleTxt)).setText(good.name);
            ((TextView) goodsView.findViewById(R.id.specTxt)).setText(Item.getSpec(good.attr_value));
            ((TextView) goodsView.findViewById(R.id.countTxt)).setText(good.num);
            ((TextView) goodsView.findViewById(R.id.priceTxt)).setText(FormatUtil.getMoneyString(good.price));
            Glide.with(goodsContainer.getContext())
                    .load(UrlUtil.getImageUrl(good.img))
                    .apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(ScreenUtil.dp2px(goodsContainer.getContext(), 2)))
                            .placeholder(R.mipmap.ic_placeholder))
                    .into((ImageView) goodsView.findViewById(R.id.iconImg));
            goodsContainer.addView(goodsView);
        }
    }

}
