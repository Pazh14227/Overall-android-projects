package com.example.malai_pt1882.layoutsdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by malai-pt1882 on 19/03/18.
 */

class DownloadAsyncTask extends AsyncTask<String,Void,Bitmap> {
    private ImageView mainImageView;
    private ProgressBar progressBar;

    DownloadAsyncTask(ImageView imageView, ProgressBar progressBar1){

        mainImageView = imageView;
        progressBar = progressBar1;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
     String url = strings[0];
     Bitmap bitmap = null;
        try {
            URL imageUrl = new URL(url);
            if(!URLUtil.isValidUrl(url)){
                throw new MalformedURLException();
            }
            if(URLUtil.isHttpsUrl(url)){
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection)imageUrl.openConnection();
                httpsURLConnection.connect();

                InputStream inputStream = httpsURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

            } else if(URLUtil.isHttpUrl(url)){
                HttpURLConnection httpURLConnection = (HttpURLConnection)imageUrl.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
        }catch (MalformedURLException e){
            Log.d("Exception","caught");
        }catch (IOException e){
            Log.d("IO Exception","caught");
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        super.onPostExecute(bitmap);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        mainImageView.setImageBitmap(bitmap);
    }
}