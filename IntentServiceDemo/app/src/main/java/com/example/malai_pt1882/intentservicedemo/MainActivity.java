package com.example.malai_pt1882.intentservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testCustomIntentService();
        testCustomJobIntentService();

    }

    private void testCustomIntentService(){

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("In onReceive","Result success from CustomIntentService");
            }
        };

        IntentFilter intentFilter = new IntentFilter(CustomIntentService.CUSTOM_FILTER);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,intentFilter);

        Intent intent = new Intent(this,CustomIntentService.class);
        startService(intent);

    }

    private void testCustomJobIntentService(){

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("In onReceive","Result success from CustomJobIntentService");
            }
        };

        IntentFilter intentFilter = new IntentFilter(CustomJobIntentService.CUSTOM_JOB_FILTER);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,intentFilter);

        Intent intent = new Intent(this,CustomJobIntentService.class);
        startService(intent);
    }
}