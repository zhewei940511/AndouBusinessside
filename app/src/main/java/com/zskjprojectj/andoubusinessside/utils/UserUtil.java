package com.zskjprojectj.andoubusinessside.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.zskjprojectj.andoubusinessside.model.User;

public class UserUtil {
    private static String KEY_USER = "KEY_USER";

    public User
            user;

    private static final UserUtil ourInstance = new UserUtil();

    public static UserUtil getInstance() {
        return ourInstance;
    }

    private UserUtil() {
        user = GsonUtils.fromJson(SPUtils.getInstance().getString(KEY_USER), User.class);
        //TODO
        if (user == null) {
            user = new User();
        }
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(user.token);
    }
}
