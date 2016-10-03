package com.kim.bisos;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityCountdown extends Activity {
    private TextView mCountDown;
    private int count = 10; // 카운트 값
    Button sendsms_btn;
    Button end_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        sendsms_btn = (Button) findViewById(R.id.sendsms_btn);
        sendsms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                String smsBody = "오늘 저녁 어때!";
                sendIntent.putExtra("행이", smsBody); // 보낼 문자
                sendIntent.putExtra("address", "01093265268"); // 받는사람 번호
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);



            }
        });

        end_btn =(Button)findViewById(R.id.end_btn);
        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mCountDown = (TextView) findViewById(R.id.tv_countdown);
        mCountDown.setText(String.valueOf(count));

        autoCountHandler.postDelayed(autoCountRunnable, 1000);
    }


    private Handler autoCountHandler = new Handler();

    public Runnable autoCountRunnable = new Runnable() {
        public void run() {
            count--;
            mCountDown.setText(String.valueOf(count));
            if (count > 0) {
                autoCountHandler.postDelayed(autoCountRunnable, 1000);

            } else {

                if (autoCountHandler != null) {
                    autoCountHandler.removeCallbacks(autoCountRunnable);

                }


                if (autoCountHandler != null) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-4019-2390"));


                    if (ActivityCompat.checkSelfPermission(ActivityCountdown.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Log.e("ActivityCountdwon","if (ActivityCompat.checkSelfPermission(ActivityCountdown.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) ");
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                }
            }

        }

    };


    @Override
    public void onPause() {
        super.onPause();
        if (autoCountHandler != null) {
            autoCountHandler.removeCallbacks(autoCountRunnable);


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
