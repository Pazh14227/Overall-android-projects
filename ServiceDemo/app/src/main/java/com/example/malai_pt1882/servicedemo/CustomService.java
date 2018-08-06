package com.example.malai_pt1882.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


public class CustomService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("onBind called","successfully");


        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d("In timerTask onRun","Called succesfully");
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask,0,3000);

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        Log.d("onDestroy called","Successfully");
        super.onDestroy();
    }


    @Override
    public void onCreate() {

        Log.d("Service created","Successfully");
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Log.d("onUnBind called","Successfully");
        return super.onUnbind(intent);
    }


}
