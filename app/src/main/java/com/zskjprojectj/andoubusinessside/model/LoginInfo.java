package com.zskjprojectj.andoubusinessside.model;

import com.blankj.utilcode.util.SPUtils;

public class LoginInfo {
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private static final String KEY_UID = "KEY_UID";
    private static final String KEY_MERCHANT_ID = "KEY_MERCHANT_ID";

    public static String getToken() {
        return SPUtils.getInstance().getString(KEY_TOKEN);
    }

    public static String getUid() {
        return SPUtils.getInstance().getString(KEY_UID);
    }

    public static String getMerchantId() {
        return SPUtils.getInstance().getString(KEY_MERCHANT_ID);
    }

    public static void saveUidAndToken(String uid, String token) {
        SPUtils.getInstance().put(KEY_TOKEN, token);
        SPUtils.getInstance().put(KEY_UID, uid);
    }

    public static void saveMerchantId(String merchantId) {
        SPUtils.getInstance().put(KEY_MERCHANT_ID, merchantId);
    }

    public static void logout() {
        saveUidAndToken("", "");
    }
}
