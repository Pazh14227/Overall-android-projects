package com.example.malai_pt1882.imagedownloadsingleclass;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static android.content.Context.MODE_PRIVATE;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    Handler mainHandler;

    private static final String IMAGE_URL = "http://2.bp.blogspot.com/-RoQQ5fACBTI/U1t3hEO8w3I/AAAAAAAAL4k/4gSg7FiYGqg/s1600/beautiful-images-wallpapers-(1).jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        Button downloadButton = findViewById(R.id.imageDownloadButton);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("In onclick function"," Clicked me");
                DownloadService downloadService = new DownloadService();
                Thread thread = new Thread(downloadService);
                thread.start();
            }
        });
    }

    public class DownloadService implements Runnable {
        Handler mainActivity;
        Bitmap bitmap;

        @Override
        public void run() {
            HttpURLConnection httpsURLConnection = null;
            try {

                URL url = new URL(IMAGE_URL);
                httpsURLConnection = (HttpURLConnection) url.openConnection();
                httpsURLConnection.setConnectTimeout(5000);
                httpsURLConnection.setReadTimeout(5000);

                httpsURLConnection.connect();

                InputStream inputStream = httpsURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                FileOutputStream fileOutputStream = openFileOutput("image.jpeg",MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                String fullPath = getApplicationContext().getFileStreamPath("image.jpeg").getAbsolutePath();

                inputStream.close();
            } catch (java.net.MalformedURLException e) {
                Toast.makeText(MainActivity.this, "Check url", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "IO exception", Toast.LENGTH_SHORT).show();
            } finally {
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
            }
        }

    }


}
