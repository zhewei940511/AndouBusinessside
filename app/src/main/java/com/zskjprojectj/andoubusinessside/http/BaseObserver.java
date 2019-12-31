package com.zskjprojectj.andoubusinessside.http;

import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.gson.stream.MalformedJsonException;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.activity.LoginActivity;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;
import com.zskjprojectj.andoubusinessside.utils.ToastUtil;

import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> extends BaseHandleObserver<BaseResult<T>> implements ProgressCancelListener {

    private BaseActivity activity;
    private Disposable d;
    private boolean showLoading;
    private boolean showNetworkError;
    private ViewGroup contentView;
    private View progressBarContainer;

    public BaseObserver(BaseActivity activity) {
        this(activity, true);
    }

    public BaseObserver(BaseActivity activity, boolean showLoading) {
        this(activity, true, true);
    }

    public BaseObserver(BaseActivity activity, boolean showLoading, boolean showNetworkError) {
        this.activity = activity;
        this.showLoading = showLoading;
        this.showNetworkError = showNetworkError;
        this.contentView = activity.findViewById(R.id.baseContentView);
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
    }

    @Override
    public void onNext(BaseResult<T> result) {
        dismissProgressDialog();
        try {
            if (result.getCode().equals("200")) {
                onSuccess(result);
            } else if (result.getCode().equals("202")) {
                ActivityUtils.startActivity(LoginActivity.class);
            } else {
                onError(new ApiException(result.getCode(), result.getMsg()));
            }
        } catch (Exception e) {
            onError(new ApiException(ApiException.TYPE_SYSTEM, e.getMessage()));
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissProgressDialog();
        if (e instanceof ApiException) {
            onFailure(((ApiException) e).getErrorCode() + " " + e.getMessage());
        } else if (e instanceof MalformedJsonException) {
            onFailure("500 服务器数据格式错误");
        } else {
            onFailure(e.getLocalizedMessage());
        }
    }

    @Override
    public void onCancelProgress() {
        if (d != null && !d.isDisposed()) {
            d.dispose();
        }
        onHandleError(new ApiException(ApiException.TYPE_USER_CANCEL, null));
    }

    public abstract void onSuccess(BaseResult<T> result);

    public void onFailure(String msg) {
        ToastUtil.showToast(msg);
    }
}
