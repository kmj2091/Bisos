package com.kim.bisos;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import com.kim.bisos.utils.Spre;

public class MainApp extends Application {
    public static MainApp mApp;
    public static Spre mPre;
    public String userId = "";

    Handler mHandler = new Handler();
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        mPre = new Spre(this);
        Log.i("gm", "start Main app");
    }
}
