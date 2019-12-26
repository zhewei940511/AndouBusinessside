package com.zskjprojectj.andoubusinessside.model;

import com.blankj.utilcode.util.SPUtils;

public class Config {
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private static final String KEY_UID = "KEY_UID";

    public static String getToken() {
        return SPUtils.getInstance().getString(KEY_TOKEN);
    }

    public static String getUid() {
        return SPUtils.getInstance().getString(KEY_UID);
    }

    public static void saveUidAndToken(String uid, String token) {
        SPUtils.getInstance().put(KEY_TOKEN, token);
        SPUtils.getInstance().put(KEY_UID, uid);
    }
}
