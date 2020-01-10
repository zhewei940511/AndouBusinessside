package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.ActivityUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.adapter.CategoryAdapter;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.Category;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zhuosongkj.android.library.util.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

import static com.zskjprojectj.andoubusinessside.activity.EditInfoActivity.KEY_INFO;

public class CategoryActivity extends BaseActivity {
    private static final String KEY_SELECTABLE = "KEY_SELECTABLE";
    CategoryAdapter adapter = new CategoryAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "商品分类");
        adapter.bindToRecyclerView(findViewById(R.id.recyclerView));
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            switch (view.getId()) {
                case R.id.newCategoryBtn:
                    showNewCategoryDialog();
                    break;
                case R.id.deleteBtn:
                    adapter.remove(position);
                    break;
                case R.id.rootView:
                    if (getIntent().getBooleanExtra(KEY_SELECTABLE, false)) {
                        Intent intent = new Intent();
                        intent.putExtra(KEY_INFO, adapter.getItem(position));
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                    break;
            }
        });
        adapter.addData(new Category(-1));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_category;
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
            HttpRxObservable.getObservable(mActivity, true, false,
                    ApiUtils.getApiService().newCategory(
                            LoginInfo.getUid(),
                            LoginInfo.getMerchantId(),
                            LoginInfo.getMerchantTypeId(),
                            name
                    ), result -> {
                        dialog.dismiss();
                        ToastUtil.showToast("添加成功!");
                        Category category = new Category(1);
                        category.name = name;
                        adapter.addData(category);
                    }).subscribe();
        });
    }

    public static void start(Activity activity, int requestCode) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_SELECTABLE, true);
        ActivityUtils.startActivityForResult(bundle, activity, CategoryActivity.class, requestCode);
    }

    public static void start() {
        ActivityUtils.startActivity(CategoryActivity.class);
    }
}
