package com.zskjprojectj.andoubusinessside.model;

import com.blankj.utilcode.util.SPUtils;

public class LoginInfo {
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private static final String KEY_UID = "KEY_UID";
    private static final String KEY_MERCHANT_ID = "KEY_MERCHANT_ID";
    private static final String KEY_MERCHANT_TYPE_ID = "KEY_MERCHANT_TYPE_ID";

    public static String getToken() {
        return SPUtils.getInstance().getString(KEY_TOKEN);
    }

    public static String getUid() {
        return SPUtils.getInstance().getString(KEY_UID);
    }

    public static String getMerchantId() {
        return SPUtils.getInstance().getString(KEY_MERCHANT_ID);
    }

    public static int getMerchantTypeId() {
        return SPUtils.getInstance().getInt(KEY_MERCHANT_TYPE_ID, 0);
    }

    public static void saveUidAndToken(String uid, String token) {
        SPUtils.getInstance().put(KEY_TOKEN, token);
        SPUtils.getInstance().put(KEY_UID, uid);
    }

    public static void saveMerchantTypeAndId(String merchantId, int merchantTypeId) {
        SPUtils.getInstance().put(KEY_MERCHANT_ID, merchantId);
        SPUtils.getInstance().put(KEY_MERCHANT_TYPE_ID, merchantTypeId);
    }

    public static void logout() {
        saveUidAndToken("", "");
    }
}
