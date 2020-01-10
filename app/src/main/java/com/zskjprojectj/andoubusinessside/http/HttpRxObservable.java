package com.zskjprojectj.andoubusinessside.http;

import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.stream.MalformedJsonException;
import com.zhuosongkj.android.library.model.BaseResult;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.activity.LoginActivity;
import com.zhuosongkj.android.library.app.BaseActivity;
import com.zhuosongkj.android.library.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.HttpException;

public class HttpRxObservable {
    /**
     * 获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 无管理生命周期,容易导致内存溢出
     */
    public static <T> Observable<? extends BaseResult<T>> getObservable(
            BaseActivity activity, boolean showLoading, boolean showRetry,
            Observable<? extends BaseResult<T>> apiObservable,
            OnSuccessListener<T> onSuccessListener,
            OnFailureListener onFailureListener) {
        final PublishSubject<Object> retrySubject = PublishSubject.create();
        return apiObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    dismissNetworkError(activity);
                    if (showLoading) {
                        showProgressDialog(activity);
                    }
                }).doOnError(throwable ->
                        handleError(throwable, activity, onFailureListener, showRetry, retrySubject))
                .doOnNext((Consumer<BaseResult<T>>) result -> {
                    dismissProgressDialog(activity);
                    if (result.getCode().equals("200")) {
                        onSuccessListener.onSuccess(result);
                    } else if (result.getCode().equals("202")) {
                        LoginActivity.start(activity);
                    } else {
                        throw Exceptions.propagate(new ApiException(result.getCode(), result.getMsg()));
                    }
                }).retryWhen(throwableObservable ->
                        throwableObservable.flatMap(throwable ->
                                Observable.just(throwable).
                                        zipWith(retrySubject, (o, o2) -> o)));
    }

    private static void handleError(Throwable throwable,
                                    BaseActivity activity,
                                    OnFailureListener onFailureListener,
                                    boolean showRetry,
                                    PublishSubject<Object> retrySubject) {
        dismissProgressDialog(activity);
        if (onFailureListener != null) {
            onFailureListener.onFailure(throwable.getMessage());
        }
        String msg = convertErrorMsg(throwable);
        if (showRetry) {
            showNetworkError(activity, retrySubject, msg);
        } else {
            ToastUtils.showShort(msg);
        }
    }

    private static String convertErrorMsg(Throwable throwable) {
        if (throwable instanceof ApiException) {
            return ((ApiException) throwable).getErrorCode() + " " + throwable.getMessage();
        } else if (throwable instanceof MalformedJsonException) {
            return "500 服务器数据格式错误";
        } else if (throwable instanceof HttpException) {
            if (((HttpException) throwable).code() == 500) {
                return "500 服务器内部错误";
            }
        }
        return "400 访问错误,请稍后重试!";
    }

    public interface OnSuccessListener<T> {
        void onSuccess(BaseResult<T> result);
    }

    public interface OnFailureListener {
        void onFailure(String msg);
    }

    public static <T> Observable<? extends BaseResult<T>> getObservable(
            BaseActivity activity, boolean showLoading, boolean showRetry,
            Observable<? extends BaseResult<T>> apiObservable,
            OnSuccessListener<T> onSuccessListener) {
        return getObservable(activity, showLoading, showRetry, apiObservable, onSuccessListener, null);
    }

    private static void dismissProgressDialog(BaseActivity activity) {
        ViewGroup contentView = activity.findViewById(R.id.baseContentView);
        View progressBarContainer = activity.findViewById(R.id.progressBarContainer);
        contentView.removeView(progressBarContainer);
    }

    private static void dismissNetworkError(BaseActivity activity) {
        if (activity == null) return;
        ((ViewGroup) activity.findViewById(R.id.baseContentView))
                .removeView(activity.findViewById(R.id.networkErrorContainer));
    }

    private static void showNetworkError(BaseActivity
                                                 activity, PublishSubject<Object> retrySubject, String errorMsg) {
        if (activity == null) return;
        if (activity.findViewById(R.id.networkErrorContainer) == null) {
            final View networkErrorContainer = LayoutInflater.from(activity).inflate(R.layout.layout_network_error, null);
            ((TextView) networkErrorContainer.findViewById(R.id.msgTxt)).setText(errorMsg);
            ViewGroup baseContentView = activity.findViewById(R.id.baseContentView);
            baseContentView.postDelayed(() -> baseContentView.addView(networkErrorContainer), 1000);
            networkErrorContainer.setOnClickListener(view -> retrySubject.onNext(new Object()));
        }
    }

    private static void showProgressDialog(BaseActivity activity) {
        setupProgressBar(activity);
        ImageView progressBar = activity.findViewById(R.id.progressBar);
        ((AnimationDrawable) progressBar.getDrawable()).start();
    }

    private static void setupProgressBar(BaseActivity activity) {
        View progressBarContainer = activity.findViewById(R.id.progressBarContainer);
        ViewGroup contentView = activity.findViewById(R.id.baseContentView);
        if (progressBarContainer == null) {
            progressBarContainer = LayoutInflater.from(activity).inflate(R.layout.layout_progress_bar, null);
            contentView.addView(progressBarContainer);
        }
        progressBarContainer.setOnClickListener(view -> {
        });
    }
    /**
     * 获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 传入LifecycleProvider自动管理生命周期,避免内存溢出
     * 备注:需要继承RxActivity.../RxFragment...
     */
//    public static <T> Observable<BaseResult<T>> getObservable(Observable<BaseResult<T>> apiObservable, LifecycleProvider<activity android:screenOrientation="portrait"Event> lifecycle) {
//        Observable<BaseResult<T>> observable;
//
//        if (lifecycle != null) {
//            observable = apiObservable
//                    //.compose(lifecycle.bindToLifecycle())
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread());
//        } else {
//            observable = getObservable(apiObservable);
//        }
//        return observable;
//    }

    /**
     * 获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 传入LifecycleProvider<activity android:screenOrientation="portrait"Event>手动管理生命周期,避免内存溢出
     * 备注:需要继承RxActivity,RxAppCompatActivity,RxFragmentActivity
     */
//    public static <T> Observable<BaseResult<T>> getObservable(Observable<BaseResult<T>> apiObservable, LifecycleProvider<activity android:screenOrientation="portrait"Event> lifecycle, ActivityEvent event) {
//        Observable<BaseResult<T>> observable;
//        if (lifecycle != null) {
//            observable = apiObservable
//                   // .compose(lifecycle.bindUntilEvent(event))
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread());
//        } else {
//            observable = getObservable(apiObservable);
//        }
//        return observable;
//    }


    /**
     * 获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 传入LifecycleProvider<FragmentEvent>手动管理生命周期,避免内存溢出
     * 备注:需要继承RxFragment,RxDialogFragment
     */
//    public static <T> Observable<BaseResult<T>> getObservable(Observable<BaseResult<T>> apiObservable, LifecycleProvider<FragmentEvent> lifecycle, FragmentEvent event) {
//        Observable<BaseResult<T>> observable;
//        if (lifecycle != null) {
//            observable = apiObservable
//                    //.compose(lifecycle.bindUntilEvent(event))
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread());
//        } else {
//            observable = getObservable(apiObservable);
//        }
//        return observable;
//    }
}
