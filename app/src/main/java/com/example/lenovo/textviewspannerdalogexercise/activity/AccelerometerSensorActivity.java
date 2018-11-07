package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;

/**
 * Created by xue on 2018/11/7.
 */

public class AccelerometerSensorActivity extends Activity implements SensorEventListener, View.OnClickListener {
    private SensorManager sensorManager = null;
    private Sensor accelerometerSensor = null;
    private Sensor gyroscopeSensor = null;
    private Button mAccelerometerBtn = null;
    private Button mUnAccelerometerBtn = null;
    private TextView mShowAccelerometerTv = null;
    private Button mGyroscopeBtn = null;
    private Button mUnGyroscopeBtn = null;
    private TextView mShowGyroscopeTv = null;
    private WindowManager window = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_sensor);
        mAccelerometerBtn = (Button) findViewById(R.id.btn_accelerometer);
        mUnAccelerometerBtn = (Button) findViewById(R.id.btn_un_accelerometer);
        mShowAccelerometerTv = (TextView) findViewById(R.id.tv_show_accelerometer);
        mGyroscopeBtn = (Button) findViewById(R.id.btn_gyroscope);
        mUnGyroscopeBtn = (Button) findViewById(R.id.btn_un_gyroscope);
        mShowGyroscopeTv = (TextView) findViewById(R.id.tv_show_gyroscope);
        mAccelerometerBtn.setOnClickListener(this);
        mUnAccelerometerBtn.setOnClickListener(this);
        mGyroscopeBtn.setOnClickListener(this);
        mUnGyroscopeBtn.setOnClickListener(this);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
//        linearAcceleSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);


        showInfo1("resolution is " + accelerometerSensor.getResolution());

        showInfo1("API为" + Build.VERSION.SDK_INT);
        window = (WindowManager) getSystemService(WINDOW_SERVICE);
        showRotation();
    }


    @Override
    protected void onResume() {
//        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }


    @Override
    protected void onPause() {
//        sensorManager.unregisterListener(this);
        super.onPause();
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
        showInfo1(sensor.getName() + " accuracy changed: " + accuracy);
    }

    private int count = 1;
    private int accelerometerTimes = 0;
    private int gyroscopeTimes = 0;
    private int sensorType = 0;//传感器类型 1为加速器，2为陀螺仪

    @Override
    /* 对于加速器，测量的是x、y、z三个轴向的加速度，分别从values[0]、values[1]、values[2]中读取。*/
    public void onSensorChanged(SensorEvent event) {
        if (sensorType == 1) {
            if (accelerometerTimes < 5) {
                int type = event.sensor.getType();
                if (type == Sensor.TYPE_ACCELEROMETER) {
                    showInfo1("加速器：" + " x:" + event.values[0] + " y:" + event.values[1]
                            + " z:" + event.values[2]);
                    accelerometerTimes++;
                }
            } else {
                sensorManager.unregisterListener(this);
            }
        } else if (sensorType == 2) {
            if (gyroscopeTimes < 5) {
                if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                    gyroscopeShowInfo("事件：" + " x:" + event.values[0] + " y:" + event.values[1]
                            + " z:" + event.values[2]);
                    gyroscopeTimes++;
                }
            } else {
                sensorManager.unregisterListener(this);
            }
        }
    }

    private void showRotation() {
        int rotation = window.getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
                showInfo1("方向：ROTATION 0(" + rotation + ")");
                break;
            case Surface.ROTATION_90:
                showInfo1("方向：ROTATION 90(" + rotation + ")");
                break;
            case Surface.ROTATION_180:
                showInfo1("方向：ROTATION 180(" + rotation + ")");
                break;
            case Surface.ROTATION_270:
                showInfo1("方向：ROTATION 270(" + rotation + ")");
                break;
            default:
                showInfo1("方向：(" + rotation + ")");
                break;
        }

    }

    private void gyroscopeShowInfo(String info) {
        mShowGyroscopeTv.append("\n" + info);
        Log.d("陀螺仪", info);
    }

    private void showInfo1(String info) {
        mShowAccelerometerTv.setText(info + "\n" + mShowAccelerometerTv.getText());
        //tv.append("\n" + info);
        Log.d("加速器", info);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accelerometer:
                accelerometerTimes = 0;
                sensorType = 1;
                sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI);
                break;
            case R.id.btn_un_accelerometer:
                sensorManager.unregisterListener(this);
                break;
            case R.id.btn_gyroscope:
                gyroscopeTimes = 0;
                sensorType = 2;
                sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_UI);
                break;
            case R.id.btn_un_gyroscope:
                sensorManager.unregisterListener(this);
                break;
            default:
                break;
        }
    }
}
