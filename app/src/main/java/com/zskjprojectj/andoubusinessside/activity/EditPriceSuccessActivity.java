package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.os.Bundle;

import com.zskjprojectj.andoubusinessside.R;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zhuosongkj.android.library.util.ActionBarUtil;

public class EditPriceSuccessActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(Activity.RESULT_OK);
        ActionBarUtil.setTitle(mActivity, "修改成功");
        findViewById(R.id.checkOrderInfoBtn).setOnClickListener(view -> finish());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_edit_price_success;
    }
}
