package com.kim.bisos;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kim.bisos.fragment.FragmentTabHome;
import com.kim.bisos.fragment.FragmentTabSetting;
import com.kim.bisos.fragment.FragmentTabSound;
import com.kim.bisos.login.ActivityLogin;

public class ActivityTabhost extends ActionBarActivity {
    private static String TAG = "ActivityTabhost";
    Context mContext;
    private static int LOGIN_RESULT = 0;
    private FragmentTabHost mNavFTH;


    ActivitySensorDetection mDetection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);


        mContext = this;

        startActivity(new Intent(mContext, ActivitySplash.class));

        getSupportActionBar().setTitle("BISOS");
        // ActionBar의 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));


        setInitUI();


        mDetection = new ActivitySensorDetection(mContext);
        mDetection.onCreate();
    }




    private void setInitUI() {
        mNavFTH = (FragmentTabHost) findViewById(R.id.nav_fth);
        mNavFTH.setup(this, getSupportFragmentManager(), R.id.tab_container);

        mNavFTH.addTab(mNavFTH.newTabSpec("tab1").setIndicator("Home"),
                FragmentTabHome.class, null);
        mNavFTH.addTab(mNavFTH.newTabSpec("tab2").setIndicator("Setting"),
                FragmentTabSetting.class, null);
        mNavFTH.addTab(mNavFTH.newTabSpec("tab3").setIndicator("Sound"),
                FragmentTabSound.class, null);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(ActivityTabhost.this, "한번 더 누르면 종료됩니다", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e(TAG, "onOptionsItemSelected");
        int id = item.getItemId();


        if (id == R.id.action_login) {
            startLoginActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void startLoginActivity() {
        startActivityForResult(new Intent(this, ActivityLogin.class), LOGIN_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


}
