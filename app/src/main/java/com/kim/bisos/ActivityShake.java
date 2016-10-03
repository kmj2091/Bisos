package com.kim.bisos;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import java.io.IOException;

public class ActivityShake extends Activity implements SensorEventListener,
        OnCheckedChangeListener {

    private final static float ACC = 18;
    private CheckBox switchButton;

    private MediaPlayer mplayer;

    private SensorManager sensor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        sensor = (SensorManager) getSystemService(SENSOR_SERVICE);

        switchButton = (CheckBox) findViewById(R.id.checkBox1);
        switchButton.setOnCheckedChangeListener(this);

        mplayer = MediaPlayer.create(this, R.raw.siren);
        try {
            mplayer.prepare();
            mplayer.setVolume(1.0f, 1.0f);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // mplayer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (switchButton.isChecked()) {
            registerSenser();
        }

    }

    @Override
    protected void onStop() {
        unregisterSenser();
        if (mplayer != null) {
            mplayer.release();
        }
        super.onStop();
    }

    @Override
    protected void onPause() {
        if (switchButton.isChecked()) {
            unregisterSenser();
        }
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        int sensorType = event.sensor.getType();

        float[] values = event.values;

        if (sensorType == Sensor.TYPE_ACCELEROMETER) {

            if ((Math.abs(values[0]) > ACC || Math.abs(values[1]) > ACC || Math
                    .abs(values[2]) > ACC)) {

                Log.i("sensor", "running");
                mplayer.start();

            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            registerSenser();
        } else {
            unregisterSenser();
        }

    }

    private void registerSenser() {
        boolean done;
        done = sensor.registerListener(this,
                sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        if (!done) {
            Toast.makeText(this,
                    getResources().getString(R.string.sensor_unsupported),
                    Toast.LENGTH_SHORT).show();
            switchButton.setChecked(false);
        }
        Log.i("sensor", "register");
    }

    private void unregisterSenser() {
        sensor.unregisterListener(this);
        Log.i("sensor", "unregister");
    }

}