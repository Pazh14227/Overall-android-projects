package com.example.malai_pt1882.downloadimagedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements ImageDownloadService.Callback {
    private ImageView downloadedImage;
    private ProgressBar progressBar;

    private static final String IMAGE_URL = "https://www.hdwallpapers.in/walls/bicycle-wide.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button downloadButton = (Button) findViewById(R.id.downloadButton);
        downloadedImage = (ImageView) findViewById(R.id.downloadedImage);
        progressBar = findViewById(R.id.progress_bar);

        //start the background download service when the button is clicked
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDownloadService imageDownloadService = new ImageDownloadService(IMAGE_URL,MainActivity.this);
                imageDownloadService.execute();
            }
        });


    }


    @Override
    public void imageDownloaded(Bitmap bitmap) {

        if(bitmap!=null) {

            progressBar.setVisibility(View.GONE);

            downloadedImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public void imageSize(int length) {
        if(length>0){
            progressBar.setMax(length);
            Log.d("MainActivity","set progressbar max length");
        }
    }

    @Override
    public void updateProgressBar(int progress) {
        progressBar.setProgress(progress);

        Log.d("MainActivity","updating progress bar");
    }
}