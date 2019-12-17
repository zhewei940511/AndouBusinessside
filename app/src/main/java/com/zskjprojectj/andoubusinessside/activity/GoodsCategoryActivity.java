package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.GoodsCategory;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

import java.util.ArrayList;

public class GoodsCategoryActivity extends AppCompatActivity {
    private View progressBar;
    private GoodsCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_category);
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("商品分类");
        progressBar = findViewById(R.id.progressBar);
        adapter = new GoodsCategoryAdapter(R.layout.layout_goods_category_list_item);
        adapter.bindToRecyclerView(findViewById(R.id.recyclerView));
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            switch (view.getId()) {
                case R.id.newCategoryBtn:
                    showNewCategoryDialog();
                    break;
                case R.id.deleteBtn:
                    adapter.remove(position);
                    break;
            }
        });
        ArrayList<GoodsCategory> data = new ArrayList<>();
        for (int i = -1; i < 8; i++) {
            GoodsCategory category = new GoodsCategory();
            category.setName("商品分类" + i);
            category.setId(i);
            data.add(category);
        }
        adapter.setNewData(data);
    }

    private void showNewCategoryDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_new_category_dialog, null);
        AlertDialog dialog = new AlertDialog.Builder(this).setView(view).show();
        view.findViewById(R.id.saveBtn).setOnClickListener(view1 -> {
            String name = ((EditText) view.findViewById(R.id.categoryNameEdt)).getText().toString();
            if (name.isEmpty()) {
                ToastUtil.showToast("商品分类名称不能为空!");
                return;
            }
            dialog.dismiss();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.postDelayed(() -> {
                ToastUtil.showToast("添加成功!");
                GoodsCategory category = new GoodsCategory();
                category.setName(name);
                adapter.addData(category);
                progressBar.setVisibility(View.GONE);
            }, 1000);
        });
    }

    class GoodsCategoryAdapter extends BaseQuickAdapter<GoodsCategory, BaseViewHolder> {
        public GoodsCategoryAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodsCategory item) {
            if (item.getId() == -1) {
                helper.setVisible(R.id.categoryNameTxt, false)
                        .setVisible(R.id.newCategoryBtn, true)
                        .setVisible(R.id.deleteBtn, false)
                        .addOnClickListener(R.id.newCategoryBtn);
            } else {
                helper.setVisible(R.id.categoryNameTxt, true)
                        .setVisible(R.id.newCategoryBtn, false)
                        .setVisible(R.id.deleteBtn, true)
                        .setText(R.id.categoryNameTxt, item.getName())
                        .addOnClickListener(R.id.deleteBtn);
            }
        }
    }
}
