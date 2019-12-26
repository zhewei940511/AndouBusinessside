package com.zskjprojectj.andoubusinessside.activity;

import android.content.Intent;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.model.Notice;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.FormatUtil;

import java.util.ArrayList;
import java.util.Random;

import static com.zskjprojectj.andoubusinessside.activity.NoticeDetailActivity.KEY_NOTICE;

public class NoticeListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "通知信息");
        NoticeAdapter adapter = new NoticeAdapter(R.layout.layout_notice_list_item);
        adapter.bindToRecyclerView(findViewById(R.id.recyclerView));
        ArrayList<Notice> data = new ArrayList<>();
        for (int i = 0; i < 48; i++) {
            Notice notice = new Notice();
            notice.setChecked(new Random().nextBoolean());
            notice.setTitle("消息的标题哦~");
            notice.setContent("消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,消息的的内容非常多,");
            notice.setDate(System.currentTimeMillis());
            data.add(notice);
        }
        adapter.setNewData(data);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Intent intent = new Intent(NoticeListActivity.this, NoticeDetailActivity.class);
            intent.putExtra(KEY_NOTICE, adapter.getItem(position));
            startActivity(intent);
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_notice_list;
    }

    class NoticeAdapter extends BaseQuickAdapter<Notice, BaseViewHolder> {
        public NoticeAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Notice item) {
            helper.setText(R.id.titleTxt, item.getTitle())
                    .setText(R.id.dateTxt, FormatUtil.getDateString2(item.getDate()));
            if (item.isChecked()) {
                helper.setGone(R.id.checkedImg, true)
                        .setGone(R.id.uncheckedImg, false);
            } else {
                helper.setGone(R.id.checkedImg, false)
                        .setGone(R.id.uncheckedImg, true);
            }
        }
    }
}
