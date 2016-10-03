package com.kim.bisos.fragment;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kim.bisos.R;


public class FragmentTabSound extends Fragment {


    FragmentActivity mActivityTabhost;
    Button siren_btn;
    Button fire_btn;
    Button scream_btn;
    Button whisle_btn;
    MediaPlayer MP;

    int mCurrentMedia = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_tab_sound, container, false);

        mActivityTabhost = getActivity();
        siren_btn = (Button) v.findViewById(R.id.siren_btn);
        fire_btn = (Button) v.findViewById(R.id.fire_btn);
        scream_btn = (Button) v.findViewById(R.id.scream_btn);
        whisle_btn = (Button) v.findViewById(R.id.whisle_btn);


        siren_btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (MP != null) {
                    if (MP.isPlaying()) {
                        siren_btn.setText("경찰사이렌");
                        MP.pause();
                        MP = null;
                        if (mCurrentMedia == R.raw.siren) {
                            mCurrentMedia = 0;
                            return;
                        }
                    }
                }
                mCurrentMedia = R.raw.siren;
                MP = MediaPlayer.create(mActivityTabhost, R.raw.siren);
                MP.seekTo(0); // 맨 처음 부터 재생하도록 이동하는 것입니다.
                siren_btn.setText("경찰사이렌");
                MP.start();


            }
        });


        fire_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MP != null) {
                    if (MP.isPlaying()) {
                        fire_btn.setText("화재경보");
                        MP.pause();
                        MP = null;
                        if (mCurrentMedia == R.raw.fire) {
                            mCurrentMedia = 0;
                            return;
                        }
                    }
                }
                mCurrentMedia = R.raw.fire;
                MP = MediaPlayer.create(mActivityTabhost, R.raw.fire);
                MP.seekTo(0); // 맨 처음 부터 재생하도록 이동하는 것입니다.
                fire_btn.setText("화재경보");
                MP.start();


            }

        });


        scream_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (MP != null) {
                    if (MP.isPlaying()) {
                        scream_btn.setText("비명소리");
                        MP.pause();
                        MP = null;
                        if (mCurrentMedia == R.raw.scream) {
                            mCurrentMedia = 0;
                            return;
                        }
                    }
                }

                mCurrentMedia = R.raw.scream;
                MP = MediaPlayer.create(mActivityTabhost, R.raw.scream);
                MP.seekTo(0); // 맨 처음 부터 재생하도록 이동하는 것입니다.
                scream_btn.setText("비명소리");
                MP.start();


            }
        });


        whisle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MP != null) {
                    if (MP.isPlaying()) {
                        whisle_btn.setText("호각소리");
                        MP.pause();
                        MP = null;
                        if (mCurrentMedia == R.raw.whisle) {
                            mCurrentMedia = 0;
                            return;
                        }
                    }
                }
                mCurrentMedia = R.raw.whisle;
                MP = MediaPlayer.create(mActivityTabhost, R.raw.whisle);
                MP.seekTo(0); // 맨 처음 부터 재생하도록 이동하는 것입니다.
                whisle_btn.setText("호각소리");
                MP.start();

            }
        });


        return v;


    }
}