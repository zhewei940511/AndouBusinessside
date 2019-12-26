package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.Notice;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;

public class NoticeDetailActivity extends BaseActivity {

    public static final String KEY_NOTICE = "KEY_NOTICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "信息详情");
        Notice notice = (Notice) getIntent().getSerializableExtra(KEY_NOTICE);
        ((TextView) findViewById(R.id.titleTxt)).setText(notice.getTitle());
        ((TextView) findViewById(R.id.contentTxt)).setText(notice.getContent());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_notice_detail;
    }
}
