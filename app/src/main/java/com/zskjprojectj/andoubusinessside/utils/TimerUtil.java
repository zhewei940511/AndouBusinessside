package com.zskjprojectj.andoubusinessside.utils;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtil {
    public int duration;
    private Timer timer;

    public void start(Runnable runnable, int duration) {
        stop();
        timer = new Timer();
        this.duration = duration;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerUtil.this.duration--;
                if (TimerUtil.this.duration <= 0) {
                    stop();
                }
                runnable.run();
            }
        }, 0, 1000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }
}
