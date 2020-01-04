package com.zskjprojectj.andoubusinessside.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.Category;

public class CategoryAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {

    public CategoryAdapter() {
        super(R.layout.layout_goods_category_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        if (item.id == -1) {
            helper.setVisible(R.id.categoryNameTxt, false)
                    .setVisible(R.id.newCategoryBtn, true)
                    .setVisible(R.id.deleteBtn, false)
                    .addOnClickListener(R.id.newCategoryBtn);
        } else {
            helper.setVisible(R.id.categoryNameTxt, true)
                    .setVisible(R.id.newCategoryBtn, false)
                    .setVisible(R.id.deleteBtn, true)
                    .setText(R.id.categoryNameTxt, item.name)
                    .addOnClickListener(R.id.deleteBtn)
                    .addOnClickListener(R.id.rootView);
        }
    }
}
