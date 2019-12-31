package com.zskjprojectj.andoubusinessside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

import butterknife.BindView;

public class EditInfoActivity extends BaseActivity {

    public static final String KEY_TITLE = "KEY_TITLE";
    public static final String KEY_HINT = "KEY_HINT";
    public static final String KEY_CONFIRM_TXT = "KEY_CONFIRM_TXT";
    public static final String KEY_INFO = "KEY_INFO";
    public static final String KEY_CONTENT = "KEY_INFO";

    @BindView(R.id.infoEdt)
    EditText infoEdt;

    @BindView(R.id.confirmBtn)
    TextView confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getStringExtra(KEY_TITLE);
        String hint = getIntent().getStringExtra(KEY_HINT);
        String confirmText = getIntent().getStringExtra(KEY_CONFIRM_TXT);
        String content = getIntent().getStringExtra(KEY_CONTENT);
        ActionBarUtil.setTitle(mActivity, title);
        infoEdt.setHint(hint);
        infoEdt.setText(content);
        infoEdt.setSelection(infoEdt.length());
        confirmBtn.setText(confirmText);
        confirmBtn.setOnClickListener(v -> {
            String infoStr = infoEdt.getText().toString().trim();
            if (infoStr.isEmpty()) {
                ToastUtil.showToast("内容不能为空!");
                return;
            }
            Intent intent = new Intent();
            intent.putExtra(KEY_INFO, infoStr);
            setResult(Activity.RESULT_OK, intent);
        });
        KeyboardUtils.showSoftInput(mActivity);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_edit_info;
    }

    public static void start(Activity activity, String title, String hint, String content, int requestCode) {
        start(activity, title, hint, "确定", content, requestCode);
    }

    public static void start(Activity activity, String title, String hint, String confirmText
            , String content, int requestCode) {
        Intent intent = new Intent(activity, EditInfoActivity.class);
        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_HINT, hint);
        intent.putExtra(KEY_CONTENT, content);
        intent.putExtra(KEY_CONFIRM_TXT, confirmText);
        ActivityUtils.startActivityForResult(activity, intent, requestCode);
    }
}
