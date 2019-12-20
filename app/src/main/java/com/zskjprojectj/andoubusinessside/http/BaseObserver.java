package com.zskjprojectj.andoubusinessside.http;

import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;

import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> extends BaseHandleObserver<BaseResult<T>> implements ProgressCancelListener {
    private BaseActivity activity;
    private Disposable d;
    private BaseResult<T> mData;
    private boolean showLoading;
    private ViewGroup contentView;
    private View progressBarContainer;

    public BaseObserver(BaseActivity aty) {
        this(aty, true);
    }

    public BaseObserver(BaseActivity activity, boolean showLoading) {
        this.activity = activity;
        this.showLoading = showLoading;
        this.contentView = activity.findViewById(R.id.contentView);
    }

    private void showProgressDialog() {
        setupProgressBar();
        ImageView progressBar = activity.findViewById(R.id.progressBar);
        ((AnimationDrawable) progressBar.getDrawable()).start();
    }

    private void setupProgressBar() {
        progressBarContainer = activity.findViewById(R.id.progressBarContainer);
        if (progressBarContainer == null) {
            progressBarContainer = LayoutInflater.from(activity).inflate(R.layout.layout_progress_bar, null);
            contentView.addView(progressBarContainer);
        }
        progressBarContainer.setOnClickListener(view -> {
        });
    }

    private void dismissProgressDialog() {
        contentView.removeView(progressBarContainer);
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        if (showLoading) {
            showProgressDialog();
        }
        onStart();
    }

    @Override
    public void onNext(BaseResult<T> t) {
        mData = t;
        try {
//            if (t.getCode().equals("200")) { // 请求成功
//                onHandleSuccess(t.getData());
//            } else {
//                onError(new ApiException(t.getCode(), t.getMsg()));
//            }
            if (t.getResultcode().equals("200")) {
                onHandleSuccess(t.getResult());
            } else {
                onError(new ApiException(t.getResultcode(), t.getReason()));
            }
        } catch (Exception e) {
            onError(new ApiException(ApiException.TYPE_SYSTEM, e.getMessage()));
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        onFinish();
    }

    @Override
    public void onCancelProgress() {
        //如果处于订阅状态，则取消订阅
        if (d != null && !d.isDisposed()) {
            d.dispose();
        }
        onHandleError(new ApiException(ApiException.TYPE_USER_CANCEL, null));
    }

    public void onStart() {
    }

    public void onFinish() {
    }

    /**
     * 成功处理
     *
     * @param t
     */
    public abstract void onHandleSuccess(T t);

    public void onHandleSuccess(BaseResult<T> t) {
    }


    public String getMsg() {
        if (mData != null) {
            //return mData.getMsg();
            return mData.getReason();
        }
        return null;
    }
}
