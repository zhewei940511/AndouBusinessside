package com.zskjprojectj.andoubusinessside.listener;

import android.os.Bundle;

public interface LifeCycleListener {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
