package com.zskjprojectj.andoubusinessside.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.zskjprojectj.andoubusinessside.R;

import butterknife.BindView;

public abstract class BaseActivity extends AppCompatActivity {

    ViewGroup rootView;
    @BindView(R.id.progressBar)
    View progressBar;
    @BindView(R.id.contentView)
    public ViewGroup contentView;
    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_base);
        rootView = findViewById(R.id.rootView);
        LayoutInflater.from(mActivity).inflate(getContentView(), findViewById(R.id.contentView));
        rootView.postDelayed(() -> doCreate(savedInstanceState)
                , 1);
    }

    protected abstract void doCreate(Bundle savedInstanceState);

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

    }

    @LayoutRes
    protected abstract int getContentView();
}
