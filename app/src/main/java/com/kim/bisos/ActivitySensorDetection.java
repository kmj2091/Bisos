package com.kim.bisos;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.util.Log;
import android.widget.EditText;
//public class ActivitySensorDetection extends Activity implements SensorEventListener {
public class ActivitySensorDetection implements SensorEventListener {
    public static Context mContext;

    private static final String TAG = "ActivitySensorDetection";
    private static final int SENSOR_DELAY_CUSTOM = 1000;


    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;

    private EditText etX;
    private EditText etY;
    private EditText etZ;

    private float x, y, z;
    private static final int SHAKE_THRESHOLD = 800;

    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;


    // sensor _ gm
    boolean SensorStop = false;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float vibrateThreshold = 0;

    public Vibrator v;
    private float deltaX;
    private float deltaY;
    private float deltaZ;

    ActivitySensorDetection(Context context){
        this.mContext = context;
    }

    protected void onCreate() {
  //     super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sensordetection);


//        mContext=this;
        Log.e(TAG, "onCreate");

        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // success! we have an accelerometer

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() / 2;
        } else {
            // fai! we dont have an accelerometer!
        }

        //initialize vibration
        v = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
//        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onResume() {
 //       super.onResume();
        Log.i(TAG, "onResume");
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        SensorStop = false;
    }

    protected void onPause() {
//        super.onPause();
        sensorManager.unregisterListener(this);
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        SensorChangeLog(event);

//        displayCleanValues();
//        displayCurrentValues();
//        displayMaxValues();

        // get the change of the x,y,z values of the accelerometer
//        deltaX = Math.abs(lastX - event.values[0]);
//        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);

        // if the change is below 2, it is just plain noise
//        if (deltaX < 2)
//            deltaX = 0;
//        if (deltaY < 2)
//            deltaY = 0;
        if (deltaZ < 2)
            deltaZ = 0;

        // set the last know values of x,y,z
//        lastX = event.values[0];
//        lastY = event.values[1];
        lastZ = event.values[2];


    }

    private void SensorChangeLog(SensorEvent event) {
//        Log.e(TAG,"event: X_"+ event.values[0]+"Y_"+ event.values[1]+"Z_"+ event.values[2]);
//        if (event.values[0] > 2){
//            Log.e(TAG,"event: X_"+ event.values[0]);
//        }
//        if (event.values[1] > 2){
//            Log.e(TAG,"event: Y_"+ event.values[1]);
//        }
        if (event.values[2] > 2) {
            Log.e(TAG, "Z:" + event.values[2] + "\n" + "lastZ:" + lastZ + "\n" + "Max:" + deltaZ + "\n");
        }

        if (deltaZ > 25 && SensorStop == false) {
            mContext.startActivity(new Intent(mContext, ActivityCountdown.class));
            SensorStop = true;
        }
    }


    public void displayAlert() {
    }
}