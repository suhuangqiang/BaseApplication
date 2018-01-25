package com.shq.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public String TAG = "math";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        double z = PointUtils.getInstance().getStandardDeviation(PointUtils.getInstance().getoData());
        Log.d(TAG,"标准差:" + z);
        double z1 = PointUtils.getInstance().getStandardDeviation(PointUtils.getInstance().getnData());
        Log.d(TAG,"标准差:" + z1);
    }
}
