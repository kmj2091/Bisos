package com.kim.bisos.login;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.kim.bisos.utils.HttpComm;
import com.kim.bisos.utils.HttpURL;
import com.kim.bisos.utils.utils;
import com.kim.bisos.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A login screen that offers login via email/password.
 */
public class ActivityRegister extends AppCompatActivity {
    Context  mContext;
    EditText mPHET;
    EditText mEmailET;
    EditText mNameET;
    EditText mPwdET;
    EditText mPwdCheckET;

    Button mCommitBT;
    Button mCancleBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        initUI();
    }

    private void initUI(){
        mPHET       = (EditText) findViewById(R.id.ph_et);
        mEmailET    = (EditText) findViewById(R.id.email_et);
        mNameET     = (EditText) findViewById(R.id.name_et);
        mPwdET      = (EditText) findViewById(R.id.pwd_et);
        mPwdCheckET = (EditText) findViewById(R.id.pwd_check_et);

        mCommitBT = (Button) findViewById(R.id.commit_bt);
        mCancleBT = (Button) findViewById(R.id.cancel_bt);

        mCommitBT.setOnClickListener(mClickListener);
        mCancleBT.setOnClickListener(mClickListener);

        TelephonyManager tMgr = (TelephonyManager)
        mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();
        Log.e("gm","mPhoneNumber"+mPhoneNumber);
        if(mPhoneNumber != null)
        if(mPhoneNumber.length() > 0){
            mPHET.setText(mPhoneNumber);
        }
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.commit_bt) {
                registerUser();
            }
            if (view.getId() == R.id.cancel_bt) {
                finish();
            }
        }
    };

    public void registerUser(){
        JSONObject mDataJO = new JSONObject();
        String PNumber  = mPHET.getText().toString();
        String Email    = mEmailET.getText().toString();
        String Name     = mNameET.getText().toString();
        String Pwd      = mPwdET.getText().toString();
        String PwdCheck = mPwdCheckET.getText().toString();

        if(!Pwd.equals(PwdCheck)){
            Toast.makeText(mContext, R.string.desc_pwd_compare_fails,Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(PNumber.length() > 0)){
            Toast.makeText(mContext, R.string.desc_ph_need,Toast.LENGTH_SHORT).show();
            return;
        }
        if(!utils.isEmailValid(Email)){
            Toast.makeText(mContext, R.string.desc_email_check,Toast.LENGTH_SHORT).show();
        }

        try {
            mDataJO.put("phoneNumber",PNumber);
            mDataJO.put("userId",Email);
            mDataJO.put("name",Name);
            mDataJO.put("pwd",Pwd);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        final HttpComm mHttpComm = new HttpComm(mContext);
        //서버 통신에 필요한 url 셋팅
        mHttpComm.setUrl(HttpURL.UserRegist);

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
                    ActivityRegister.this.finish();
                }else{
                    //실패시
                    Toast.makeText(mContext, mHttpComm.mBodyS, Toast.LENGTH_SHORT).show();
                }
            }
        });
        mHttpComm.start();
    }
}

