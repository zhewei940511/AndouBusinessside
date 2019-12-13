package com.zskjprojectj.andoubusinessside.http;


import com.zskjprojectj.andoubusinessside.BuildConfig;


public class ApiUtils {
    private static ApiService apiService;

    public static ApiService getApiService() {
        return getApiService(BuildConfig.API_HOST);
    }

    public static ApiService getApiService(String baseUrl) {
        if (apiService == null) {
            apiService = RetrofitUtils.getInstance().retrofit(baseUrl).create(ApiService.class);
        }
        return apiService;
    }
}
