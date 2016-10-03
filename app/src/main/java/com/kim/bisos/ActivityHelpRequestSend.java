package com.kim.bisos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kim.bisos.CustomListView.Listview;

/**
 * Created by kim on 2016-09-05.
 */
public class ActivityHelpRequestSend extends Activity {


    Button enrollment_btn;
    Button canceld_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helprequest_send);

        enrollment_btn = (Button) findViewById(R.id.enrollment_btn);
        enrollment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHelpRequestSend.this, Listview.class);
                startActivity(intent);
                Toast toast = Toast.makeText(ActivityHelpRequestSend.this, "게시판에 글이 등록되었습니다.", Toast.LENGTH_SHORT);
                toast.show();


                canceld_btn = (Button) findViewById(R.id.canceld_btn);
                canceld_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
            }
        });
    }
}
