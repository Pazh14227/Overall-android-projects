package com.example.malai_pt1882.downloadimagedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.telecom.Call;
import android.util.Log;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by malai-pt1882 on 16/03/18.
 */

//Intent service always runs in worker thread.
public class ImageDownloadService extends AsyncTask<Void, Integer, Bitmap> {

    private String url;

    WeakReference<Callback> callbackWeakReference;

    ImageDownloadService(String url, Callback callback) {
        this.url = url;
        callbackWeakReference = new WeakReference<Callback>(callback);
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {

        HttpsURLConnection httpsURLConnection = null;
        Bitmap bitmapImage = null;

        try {
            URL imageUrl = new URL(url);
            httpsURLConnection = (HttpsURLConnection) imageUrl.openConnection();

            httpsURLConnection.setReadTimeout(5000);
            httpsURLConnection.setConnectTimeout(5000);
//            httpsURLConnection.connect();

            InputStream inputStream = httpsURLConnection.getInputStream();

            int length = httpsURLConnection.getContentLength();

            byte[] byteArray = new byte[length];


            if (callbackWeakReference != null && callbackWeakReference.get() != null) {
                callbackWeakReference.get().imageSize(length);
            } else {
                if (cancel(true)) {

                    Log.d("Success", "Cancelled task");
                }
            }

            int bytesRead = 0;

            Log.d("Length ", "" + length);

            while (bytesRead < length) {
                int n = inputStream.read(byteArray, bytesRead, length - bytesRead);

                if (n <= 0) {
                    break;
                }
                bytesRead = bytesRead + n;

                publishProgress(bytesRead);

                Log.d("Bytes Read", "" + bytesRead);
            }

            if (byteArray.length > 0) {

                Log.d("Getting Byte array", "Success");

                bitmapImage = BitmapFactory.decodeByteArray(byteArray, 0, length);

                if (bitmapImage != null) {
                    Log.d("Decoding Bitmap ", "Success");
                } else {
                    return null;
                }
            } else {
                return null;
            }

            inputStream.close();


        } catch (java.net.MalformedURLException exception) {
            exception.printStackTrace();
        }
        //IO exception also handles SocketTimeoutException.
        catch (java.io.IOException exception) {
            exception.printStackTrace();
        } finally {
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
        }
        return bitmapImage;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if (callbackWeakReference != null && callbackWeakReference.get() != null) {
            callbackWeakReference.get().imageDownloaded(bitmap);
        } else {
            if (cancel(true)) {

                Log.d("Success", "Cancelled task");
            }
        }
    }

    interface Callback {
        void imageDownloaded(Bitmap bitmap);

        void imageSize(int length);

        void updateProgressBar(int progress);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        if (callbackWeakReference != null && callbackWeakReference.get() != null) {
            callbackWeakReference.get().updateProgressBar(values[0]);
        } else {
            if (cancel(true)) {
                Log.d("Success", "Cancelled task");
            }
        }
    }
}