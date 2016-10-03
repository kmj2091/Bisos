package com.kim.bisos.sos;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.kim.bisos.utils.HttpComm;
import com.kim.bisos.utils.HttpURL;
import com.kim.bisos.R;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A login screen that offers login via email/password.
 */
public class ActivitySOS extends AppCompatActivity {
    Context  mContext;

    EditText    mTilteET;
    EditText    mBodyET;
    RadioGroup  mStatusRG;

    Button mCommitBT;
    Button mCancelBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        mContext = this;
        initUI();
    }

    private void initUI(){
        mTilteET        = (EditText) findViewById(R.id.activity_sos_title_et);
        mBodyET         = (EditText) findViewById(R.id.activity_sos_body_et);

        mStatusRG       = (RadioGroup) findViewById(R.id.activity_sos_status_rg);

        mCommitBT = (Button) findViewById(R.id.activity_sos_commit_bt);
        mCancelBT = (Button) findViewById(R.id.activity_sos_cancel_bt);

        mCommitBT.setOnClickListener(mClickListener);
        mCancelBT.setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.e("gm","button click");
            if (view.getId() == R.id.activity_sos_commit_bt) {
                registerSOS();
            }
            if (view.getId() == R.id.activity_sos_cancel_bt) {
                finish();
            }
        }
    };

    public void registerSOS(){
        JSONObject mDataJO = new JSONObject();
        String Title    = mTilteET.getText().toString();
        String Body     = mBodyET.getText().toString();

        int id = mStatusRG.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) findViewById(id);

        String lat     = "37.493443";
        String lon     = "126.836594";

        String Status   = rb.getText().toString();

        if(!(Title.length() > 0)){
            Toast.makeText(mContext, R.string.desc_ph_need,Toast.LENGTH_SHORT).show();
            return;
        }

        if(!(Body.length() > 0)){
            Toast.makeText(mContext, R.string.desc_ph_need,Toast.LENGTH_SHORT).show();
            return;
        }
        Log.e("gm","conversionState(Status):"+Status);
        Log.e("gm","conversionState(Status):"+conversionState(Status));
        try {
            mDataJO.put("title"   ,Title);
            mDataJO.put("body"        ,Body);
            mDataJO.put("status"        ,conversionState(Status));
            mDataJO.put("lat"           ,lat);
            mDataJO.put("lon"           ,lon);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final HttpComm mHttpComm = new HttpComm(mContext);
        //서버 통신에 필요한 url 셋팅
        mHttpComm.setUrl(HttpURL.SOSRegist);

        //서버 통신에 필요한 데이터
        mHttpComm.setQeryJO(mDataJO);

        //서버 통신 후 할일을 등록
        mHttpComm.setRunnable(new Runnable() {
            @Override
            public void run() {
                // 성공 여부 확인
                if(mHttpComm.isSuccess()) {
                    //성공시
                    Toast.makeText(mContext, R.string.desc_success, Toast.LENGTH_SHORT).show();
                    ActivitySOS.this.finish();
                }else{
                    //실패시
                    Toast.makeText(mContext, mHttpComm.mBodyS, Toast.LENGTH_SHORT).show();
                }
            }
        });
        mHttpComm.start();
    }


    public String conversionState(String type){

        return "";
    }

}

