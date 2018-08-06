package com.example.malai_pt1882.contactsusingrecyclerview;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

class DownloadJSON extends AsyncTask<RecyclerView,Integer,RecyclerView> {

    private String JSONData;
    @Override
    protected RecyclerView doInBackground(RecyclerView... recyclerViews) {
        String url = "https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=ab275994cb4b46e5964cadef4c633c82";
        RecyclerView recyclerView = recyclerViews[0];

        try {
            URL jsonUrl = new URL(url);
            HttpsURLConnection httpURLConnection = (HttpsURLConnection) jsonUrl.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            JSONData = "";
            String line = "";

            JSONData = bufferedReader.readLine();

            Log.d("tag",JSONData);

            return recyclerView;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(RecyclerView recyclerView) {
        super.onPostExecute(recyclerView);

//        try {
//            ArrayList<HashMap<String,String>> contactList = new ArrayList<>();
//            JSONObject jsonObject = new JSONObject(JSONData);
//            JSONArray jsonArray = jsonObject.getJSONArray("contacts");
//
//            for(int i=0;i<jsonArray.length();i++){
//                JSONObject contactObject = jsonArray.getJSONObject(i);
//                HashMap<String,String> contactDetails = new HashMap<>();
//                contactDetails.put("name",contactObject.getString("name"));
//                contactDetails.put("email",contactObject.getString("email"));
//                contactList.add(contactDetails);
//            }
//
//            ContactAdapter contactAdapter = new ContactAdapter(contactList);
//            recyclerView.setAdapter(contactAdapter);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}
