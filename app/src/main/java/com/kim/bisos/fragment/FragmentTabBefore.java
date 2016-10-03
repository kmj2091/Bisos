package com.kim.bisos.fragment;

/**
 * Created by kim on 2016-08-21.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kim.bisos.R;
import com.kim.bisos.ActivityEtc;
import com.kim.bisos.viewpager.ActivityGear;
import com.kim.bisos.viewpager.ActivitySaddle;
import com.kim.bisos.viewpager.ActivityWheel;

public class FragmentTabBefore extends Fragment {

        Button gear_btn;
        Button wheel_btn;
        Button saddle_btn;
        Button etc_btn;


    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_tab_before, container, false);
            TextView local = (TextView) v.findViewById(R.id.local);

        gear_btn = (Button) v.findViewById(R.id.gear_btn);


        gear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(FragmentTabBefore.this.getActivity(), ActivityGear.class);

//     intent.setData();
                startActivity(Intent);
                getActivity().finish();

            }

        });


        wheel_btn = (Button) v.findViewById(R.id.wheel_btn);


        wheel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(FragmentTabBefore.this.getActivity(), ActivityWheel.class);

//     intent.setData();
                startActivity(Intent);
                getActivity().finish();

            }

        });

        saddle_btn = (Button) v.findViewById(R.id.saddle_btn);


        saddle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(FragmentTabBefore.this.getActivity(), ActivitySaddle.class);

//     intent.setData();
                startActivity(Intent);
                getActivity().finish();

            }

        });


        etc_btn = (Button) v.findViewById(R.id.etc_btn);


        etc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(FragmentTabBefore.this.getActivity(), ActivityEtc.class);

//     intent.setData();
                startActivity(Intent);
                getActivity().finish();

            }

        });
            return v;


    }
}
