package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.model.Notice;

public class NoticeDetailActivity extends AppCompatActivity {

    public static final String KEY_NOTICE = "KEY_NOTICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        ((TextView) findViewById(R.id.actionBarTitleTxt)).setText("信息详情");
        findViewById(R.id.backBtn).setOnClickListener(view -> finish());
        Notice notice = (Notice) getIntent().getSerializableExtra(KEY_NOTICE);
        ((TextView) findViewById(R.id.titleTxt)).setText(notice.getTitle());
        ((TextView) findViewById(R.id.contentTxt)).setText(notice.getContent());
    }
}
