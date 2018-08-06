package com.example.malai_pt1882.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class CustomIntentService extends IntentService {

    public static final String CUSTOM_FILTER = "com.example.malai_pt1882.intentservicedemo";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public CustomIntentService() {
        super("Custom intent service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d("Started onHandleIntent","Successfully");

        if(intent!=null) {
            Intent newIntent = new Intent(CUSTOM_FILTER);
            LocalBroadcastManager.getInstance(this).sendBroadcast(newIntent);
        }
    }

    @Override
    public void onDestroy() {

        Log.d("onDestroy called","Destroyed service successfully");
        super.onDestroy();
    }
}
