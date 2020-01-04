package com.zskjprojectj.andoubusinessside.utils;

import com.zskjprojectj.andoubusinessside.BuildConfig;

public class UrlUtil {

    public static String getImageUrl(String path) {
        return BuildConfig.API_HOST + path.substring(1);
    }
}
