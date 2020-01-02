package com.zskjprojectj.andoubusinessside.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.zskjprojectj.andoubusinessside.model.UserT;

public class UserUtil {
    private static String KEY_USER = "KEY_USER";

    public UserT
            userT;

    private static final UserUtil ourInstance = new UserUtil();

    public static UserUtil getInstance() {
        return ourInstance;
    }

    private UserUtil() {
        userT = GsonUtils.fromJson(SPUtils.getInstance().getString(KEY_USER), UserT.class);
        //TODO
        if (userT == null) {
            userT = new UserT();
        }
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(userT.token);
    }
}
