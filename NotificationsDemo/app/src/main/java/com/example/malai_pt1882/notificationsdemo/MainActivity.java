package com.example.malai_pt1882.notificationsdemo;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"sdflsld")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My custom notification")
                .setContentText("This is the first notification I have made")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setStyle(new NotificationCompat.MessagingStyle("new User"));

        NotificationManagerCompat.from(this)
                .notify(0,notificationBuilder.build());

    }
}
