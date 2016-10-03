package com.kim.bisos.sos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.kim.bisos.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivitySOSDetail extends Activity {
    Context mContext;

    TextView TitleTV;
    TextView BodyTV;
    TextView StatusTV;

    Button CallBT;
    Button CalcelBT;
    RadioGroup RG;

    String title;
    String body;
    String status;
    String call;
    String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_detail);

        mContext = this;
        initUI();
    }

    public void initUI() {

        TitleTV = (TextView) findViewById(R.id.activity_sos_detail_title_et);
        BodyTV = (TextView) findViewById(R.id.activity_sos_detail_body_et);
        StatusTV = (TextView) findViewById(R.id.activity_sos_detail_body_et);

        CallBT = (Button) findViewById(R.id.activity_sos_detail_call_bt);
        CalcelBT = (Button) findViewById(R.id.activity_sos_detail_cancel_bt);


        String data = getIntent().getExtras().getString("data");
        try {
            JSONObject dataJO = new JSONObject(data);
            title = dataJO.getString("title");
            body = dataJO.getString("body");
            status = dataJO.getString("status");
            call = dataJO.getJSONObject("user").getString("hpNumber");
            userId = dataJO.getJSONObject("user").getString("userId");

            TitleTV.setText(title);
            BodyTV.setText(body);
            StatusTV.setText(makeStatus(status));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        CallBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + call));
                if (ActivityCompat.checkSelfPermission(ActivitySOSDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });
        CalcelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

	}
    public String makeStatus(String data){
        String Status = "";
        if(data == "wait"){
            Status = "대기중";
        }
        if(data == "progress"){
            Status = "진행중";
        }
        if(data == "complete"){
            Status = "완료";
        }
        if(data == "expire"){
            Status = "만료";
        }
        return Status;

    }
}




