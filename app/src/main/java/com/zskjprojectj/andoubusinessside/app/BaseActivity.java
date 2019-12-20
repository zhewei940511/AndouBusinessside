package com.zskjprojectj.andoubusinessside.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.zskjprojectj.andoubusinessside.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.rootView)
    protected ViewGroup rootView;

    @BindView(R.id.progressBar)
    View progressBar;

    protected ViewGroup contentView;
    protected BaseActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_base);
        contentView = findViewById(R.id.contentView);
        LayoutInflater.from(mActivity).inflate(getContentView(), contentView);
        ButterKnife.bind(mActivity);
    }

    @LayoutRes
    protected abstract int getContentView();
}
