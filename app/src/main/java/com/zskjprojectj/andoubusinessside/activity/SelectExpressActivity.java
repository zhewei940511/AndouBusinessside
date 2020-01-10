package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zhuosongkj.android.library.adapter.BaseAdapter;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.http.ApiUtils;
import com.zskjprojectj.andoubusinessside.http.Express;
import com.zskjprojectj.andoubusinessside.http.HttpRxObservable;
import com.zskjprojectj.andoubusinessside.model.LoginInfo;
import com.zhuosongkj.android.library.util.ActionBarUtil;

public class SelectExpressActivity extends BaseActivity {
    private static final String KEY_DATA = "KEY_DATA";
    ExpressAdapter adapter = new ExpressAdapter();

    public static Express getResult(Intent data) {
        return (Express) data.getSerializableExtra(KEY_DATA);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "选择快递公司");
        adapter.bindToRecyclerView(findViewById(R.id.recyclerView));
        HttpRxObservable.getObservable(mActivity, true, true,
                ApiUtils.getApiService().express(LoginInfo.getUid()),
                result -> adapter.setNewData(result.data))
                .subscribe();
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Intent intent = new Intent();
            intent.putExtra(KEY_DATA, adapter.getItem(position));
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_select_express;
    }

    class ExpressAdapter extends BaseAdapter<Express> {
        public ExpressAdapter() {
            super(R.layout.layout_express_list_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, Express item) {
            helper.setText(R.id.nameTxt, item.name);
        }
    }
}
