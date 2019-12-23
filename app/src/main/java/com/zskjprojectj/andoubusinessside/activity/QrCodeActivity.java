package com.zskjprojectj.andoubusinessside.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.king.zxing.CaptureHelper;
import com.king.zxing.ViewfinderView;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.utils.ActionBarUtil;

import butterknife.BindView;

public class QrCodeActivity extends BaseActivity {

    CaptureHelper mCaptureHelper;

    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;

    @BindView(R.id.viewfinderView)
    ViewfinderView viewfinderView;

    @BindView(R.id.ivTorch)
    View ivTorch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarUtil.setTitle(mActivity, "扫一扫");
        mCaptureHelper = new CaptureHelper(this, surfaceView, viewfinderView, ivTorch);
        mCaptureHelper.setOnCaptureCallback(result -> {
            ToastUtils.showShort(result);
            return true;
        });
        mCaptureHelper.onCreate();
        mCaptureHelper.vibrate(true)
                .fullScreenScan(true)//全屏扫码
                .continuousScan(false);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_qr_code;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCaptureHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCaptureHelper.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCaptureHelper.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCaptureHelper.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
