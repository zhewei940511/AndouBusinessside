package com.zskjprojectj.andoubusinessside.base;

import android.app.Application;
import android.widget.ImageView;

import com.bugtags.library.Bugtags;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;
import com.zskjprojectj.andoubusinessside.BuildConfig;
import com.zskjprojectj.andoubusinessside.R;
import com.zskjprojectj.andoubusinessside.http.ApiService;
import com.zskjprojectj.andoubusinessside.utils.SharedPreferencesManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseApplication extends Application {
    private ApiService api;
    private static BaseApplication application;
    private static final String TAG = "FloatWindow";

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        SharedPreferencesManager.init(application);
        initHttp();
        Logger.addLogAdapter(new AndroidLogAdapter());
        Bugtags.start("fe9d0bd31e3b494987858e14f13bb402", this, Bugtags.BTGInvocationEventBubble);
        Bugly.init(getApplicationContext(), "00eade2039", BuildConfig.DEBUG);
    }

    public static BaseApplication getInstance() {
        return application;
    }

    void initHttp() {
        /**
         * 初始化悬浮窗
         */
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.icon);

//        FloatWindow
//                .with(getApplicationContext())
//                .setView(imageView)
//                .setWidth(Screen.width,0.2f)
//                .setHeight(Screen.width,0.2f)
//                .setX(Screen.width,0.7f)
//                .setY(Screen.height,0.2f)
//                .setMoveType(MoveType.back)
//                .setMoveStyle(300,null)
//              //  .setFilter(true, OnlinemallActivity.class)
//                .build();
        /*
        初始化网络请求
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ApiService.class);
    }

    public ApiService API() {
        return api;
    }
//    public static Context getMyAppContext(){
//        return application;
//    }

}
