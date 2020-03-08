package com.zhuosongkj.android.library.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.bugtags.library.Bugtags;
import com.zhuosongkj.android.library.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected ViewGroup rootView;

    protected ViewGroup contentView;
    protected BaseActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_base);
        contentView = findViewById(R.id.baseContentView);
        LayoutInflater.from(mActivity).inflate(getContentView(), contentView);
        rootView = findViewById(R.id.rootView);
        ButterKnife.bind(mActivity);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }
    @LayoutRes
    protected abstract int getContentView();
}
