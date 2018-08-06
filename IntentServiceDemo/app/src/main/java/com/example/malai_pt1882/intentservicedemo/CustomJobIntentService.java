package com.example.malai_pt1882.intentservicedemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class CustomJobIntentService extends JobIntentService {

    public static final String CUSTOM_JOB_FILTER = "com.example.malai_pt1882.intentservicedemo.CustomJobIntentService";

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        Log.d("onHandleWork called"," Successfully");

        Intent intent1 = new Intent(CUSTOM_JOB_FILTER);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);

    }

    @Override
    public void onDestroy() {

        Log.d("onDestroy called","Destroyed CustomJobIntentService successfully");

        super.onDestroy();
    }
}
