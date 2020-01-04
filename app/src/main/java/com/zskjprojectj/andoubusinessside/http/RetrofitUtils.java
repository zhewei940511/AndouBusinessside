package com.zskjprojectj.andoubusinessside.http;

import com.zskjprojectj.andoubusinessside.model.LoginInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitUtils {
    private static final String TAG = RetrofitUtils.class.getSimpleName() + "：";
    public static final int OUT_TIME = 2;
    public static final int READ_WRITE_TIME = 2;

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final RetrofitUtils INSTANCE = new RetrofitUtils();
    }

    //获取单例
    public static RetrofitUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private RetrofitUtils() {
    }

    private static OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(OUT_TIME, TimeUnit.SECONDS)
                .writeTimeout(READ_WRITE_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_WRITE_TIME, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("token", LoginInfo.getToken())
                            .build();
                    return chain.proceed(request);
                })
                .addInterceptor(new HttpLoggingInterceptor(
                        new HttpLogger()).setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder.build();
    }

    /**
     * 获取Retrofit
     *
     * @author ZhongDaFeng
     */
    public Retrofit retrofit(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }
}
