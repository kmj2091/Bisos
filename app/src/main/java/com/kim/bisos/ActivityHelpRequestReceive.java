package com.kim.bisos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.kim.bisos.Map.NaverMyLocation;

/**
 * Created by kim on 2016-09-05.
 */
public class ActivityHelpRequestReceive extends Activity {

    Button activity_helprequest_receive_confirm_btn;
    Button wichi_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helprequest_receive);

        init();
    }

    private void init() {
        activity_helprequest_receive_confirm_btn = (Button) findViewById(R.id.activity_helprequest_receive_confirm_btn);


        activity_helprequest_receive_confirm_btn.setOnClickListener(mClickListener);
       wichi_btn = (Button) findViewById(R.id.wichi_btn);


        wichi_btn.setOnClickListener(mClickListener);

    }

    OnClickListener mClickListener = new OnClickListener() {
        Intent intent;

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.activity_helprequest_receive_confirm_btn:
                    intent = new Intent(ActivityHelpRequestReceive.this, ActivityNoticeBoard.class);
                    break;

                case R.id.wichi_btn:
                    intent = new Intent(ActivityHelpRequestReceive.this, NaverMyLocation.class);
                    break;
            }
            startActivity(intent);
            finish();
        }
    };
}