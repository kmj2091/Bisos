package com.kim.bisos.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kim.bisos.ActivityHelpRequestReceive;
import com.kim.bisos.ActivitySensorDetection;
import com.kim.bisos.R;
import com.kim.bisos.sos.ActivitySOS;

public class FragmentTabSetting extends Fragment {

    static final String TAG = "FragmentTabSetting";

    Button crime_btn;
    Button save_btn;
    Button question_btn;
    Button dasan_btn;
    Button sos_btn;
    Button helprequest_after_btn;
    Button sensordetection_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.e(TAG,"onCreateView");

        //Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_tab_setting, container, false);


        TextView local = (TextView) v.findViewById(R.id.local);

        crime_btn = (Button) v.findViewById(R.id.crime_btn);
        save_btn = (Button) v.findViewById(R.id.save_btn);
        question_btn = (Button) v.findViewById(R.id.question_btn);
        dasan_btn = (Button) v.findViewById(R.id.dasan_btn);
        sos_btn = (Button) v.findViewById(R.id.sos_btn);
        helprequest_after_btn = (Button)v.findViewById(R.id.helprequest_after_btn);
        sensordetection_btn = (Button) v.findViewById(R.id.sensordetection_btn);

        crime_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:112"));
                startActivity(intent);
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:119"));
                startActivity(intent);
            }
        });


        question_btn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:110"));
                startActivity(intent);
            }
        });


        dasan_btn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:120"));
                startActivity(intent);
            }
        });


        sos_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ActivitySOS.class);
                startActivity(intent);


            }
        });

        helprequest_after_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityHelpRequestReceive.class);
                startActivity(intent);

            }
        });

        sensordetection_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("FragmentTabSetting","sensordetection_btn");
                Intent intent = new Intent(getActivity(), ActivitySensorDetection.class);
                startActivity(intent);
            }
        });


        return v;
    }
}


