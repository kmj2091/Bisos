package com.kim.bisos;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ActivitySplash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler() {

            public void handleMessage(Message msg) {
                finish();
            }
        };
//TODO Auto-generated method stub
        handler.sendEmptyMessageDelayed(0, 2000);
    }

}