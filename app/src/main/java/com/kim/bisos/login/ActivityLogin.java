package com.kim.bisos.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.kim.bisos.MainApp;
import com.kim.bisos.utils.HttpComm;
import com.kim.bisos.utils.HttpURL;
import com.kim.bisos.R;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A login screen that offers login via email/password.
 */
public class ActivityLogin extends AppCompatActivity {
    public static int LOGIN_RESULT_SUCCESS = 5;

    Context mContext;

    EditText mEmailET;
    EditText mPwdET;

    Button mLoginBT;
    Button mRegisterBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        initUI();
    }

    private void initUI() {
        // Set up the login form.
        mEmailET = (EditText) findViewById(R.id.email);
        mPwdET = (EditText) findViewById(R.id.password);

        mLoginBT = (Button) findViewById(R.id.login_btn);
        mLoginBT.setOnClickListener(mClickListener);

        mRegisterBT = (Button) findViewById(R.id.register_btn);
        mRegisterBT.setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.login_btn) {
                login();
            }
            if (view.getId() == R.id.register_btn) {
                startRegisterActivity();
            }
        }
    };
    public void startRegisterActivity() {
        startActivity(new Intent(this, ActivityRegister.class));
    }
    public void login() {
        if(!(mEmailET.length() > 0)){
            Toast.makeText(mContext, R.string.desc_pz_enter_email,Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(mPwdET.length() > 0)){
            Toast.makeText(mContext, R.string.desc_pz_enter_pwd,Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject mDataJO = new JSONObject();
        String Email    = mEmailET.getText().toString();
        String Pwd      = mPwdET.getText().toString();

        try {
            mDataJO.put("userId",Email);
            mDataJO.put("pwd",Pwd);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        final HttpComm mHttpComm = new HttpComm(mContext);
        mHttpComm.setUrl(HttpURL.UserLogin);
        mHttpComm.setQeryJO(mDataJO);
        mHttpComm.setRunnable(new Runnable() {
            @Override
            public void run() {
                if(mHttpComm.isSuccess()) {
                    Toast.makeText(mContext, R.string.desc_success, Toast.LENGTH_SHORT).show();
                    try {
                        MainApp.mApp.userId = new JSONObject(mHttpComm.mRecvDataS).getJSONObject("Data").getString("userId");

                        Intent intent = new Intent();
                        ActivityLogin.this.setResult(LOGIN_RESULT_SUCCESS, intent);//RESULT_OK를 돌려주면 MainActivity 에서 받는다.
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(mContext, mHttpComm.mBodyS, Toast.LENGTH_SHORT).show();
                }
            }
        });
        mHttpComm.start();
    }
}

