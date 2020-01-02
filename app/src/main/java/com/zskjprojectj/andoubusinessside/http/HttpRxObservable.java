package com.zskjprojectj.andoubusinessside.http;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.app.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
            Observable<? extends BaseResult<T>> apiObservable) {
        return getObservable(null, apiObservable);
    }

    public static <T> Observable<? extends BaseResult<T>> getObservable(
            BaseActivity activity,
            Observable<? extends BaseResult<T>> apiObservable) {
        final PublishSubject<Object> retrySubject = PublishSubject.create();
        return apiObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> dismissNetworkError(activity))
                .doOnError(throwable -> {
                    if (throwable instanceof HttpException) {
                        switch (((HttpException) throwable).code()) {
                            case 500:
                                showNetworkError(activity, retrySubject, "服务器错误,请点击刷新重试!");
                                break;
                        }
                    } else {
                        showNetworkError(activity, retrySubject, "暂无网络连接,请点击刷新重试!");
                    }
                })
                .retryWhen(throwableObservable ->
                        throwableObservable.flatMap(throwable ->
                                Observable.just(throwable).zipWith(retrySubject, (o, o2) -> o)));
    }

    private static void dismissNetworkError(BaseActivity activity) {
        if (activity == null) return;
        ((ViewGroup) activity.findViewById(R.id.baseContentView))
                .removeView(activity.findViewById(R.id.networkErrorContainer));
    }

    private static void showNetworkError(BaseActivity activity, PublishSubject<Object> retrySubject, String errorMsg) {
        if (activity == null) return;
        if (activity.findViewById(R.id.networkErrorContainer) == null) {
            final View networkErrorContainer = LayoutInflater.from(activity).inflate(R.layout.layout_network_error, null);
            ((TextView) networkErrorContainer.findViewById(R.id.msgTxt)).setText(errorMsg);
            ViewGroup baseContentView = activity.findViewById(R.id.baseContentView);
            baseContentView.postDelayed(() -> baseContentView.addView(networkErrorContainer), 1000);
            networkErrorContainer.setOnClickListener(view -> retrySubject.onNext(new Object()));
        }
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
