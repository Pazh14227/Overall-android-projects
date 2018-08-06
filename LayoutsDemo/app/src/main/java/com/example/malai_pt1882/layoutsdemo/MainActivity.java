package com.example.malai_pt1882.layoutsdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
private ImageView imageView;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button busButtton = findViewById(R.id.busImage);
        Button carButton = findViewById(R.id.carImage);
        Button trainButton = findViewById(R.id.trainImage);
        Button bikeButton = findViewById(R.id.bikeImage);

        imageView = findViewById(R.id.async_image);
        progressBar = findViewById(R.id.downloadImageProgress);

        View.OnClickListener listener = new View.OnClickListener(){
            String url;

            public void onClick(View view){
                Button button = findViewById(view.getId());
                imageView.setImageBitmap(null);
                progressBar.setVisibility(ProgressBar.VISIBLE);
                if(view.getId() == R.id.busImage){
                    url="https://www.findyourwallpaper.com/wp-content/uploads/2013/10/volvo-bus-HD-wallpaper-For-Desktop.jpeg";
                } else if(view.getId() == R.id.bikeImage){
                    url = "http://diarioveaonline.com/wp-content/uploads/2018/01/awesome-hd-bike-wallpapers-for-desktop-49.jpg";
                } else if(view.getId() == R.id.carImage){
                    url = "https://wallpapercave.com/wp/MU86UdA.jpg";
                } else{
                    url = "https://wallpaperscraft.com/image/train_motion_nature_fall_91415_1920x1080.jpg";
                }

                new DownloadAsyncTask(imageView,progressBar).execute(url);

            }
        };

      busButtton.setOnClickListener(listener);
      carButton.setOnClickListener(listener);
      trainButton.setOnClickListener(listener);
      bikeButton.setOnClickListener(listener);
    }
}
