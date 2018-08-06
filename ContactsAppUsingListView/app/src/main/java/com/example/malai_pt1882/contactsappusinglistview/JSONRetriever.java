package com.example.malai_pt1882.contactsappusinglistview;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

class JSONRetriever extends AsyncTask<ListView, Integer, ListView> {

    private String JSONData;

    @Override
    protected ListView doInBackground(ListView... listViews) {
        String url = "https://api.androidhive.info/contacts/";
        ListView listView = listViews[0];
        LayoutInflater layoutInflater = LayoutInflater.from(listView.getContext());
        JSONData = "";

        try {
            JSONData = getJSONFromURL(url);

            return listView;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ListView listView) {
        super.onPostExecute(listView);

        ArrayList<HashMap<String, String>> contactList = new ArrayList<>();

        try {

            JSONArray jsonArray = getJSONArrayFromJSONObject();

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject contactObject = jsonArray.getJSONObject(i);
                contactList.add(createHashMapFromJSONObject(contactObject));

            }

            listView.setAdapter(new ContactAdapter(contactList));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private HttpURLConnection setUpHTTPUrlConnection(String url) throws IOException {
        URL jsonUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) jsonUrl.openConnection();
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setConnectTimeout(5000);
        return httpURLConnection;
    }

    private BufferedReader readerForHTTPUrl(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = httpURLConnection.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private String readDataUsingBufferedReader(BufferedReader bufferedReader) throws IOException {
        String line = "";
        String data = "";

        while (line != null) {
            line = bufferedReader.readLine();
            data = data + line + "\n";
        }
        return data;
    }

    private String getJSONFromURL(String url) throws IOException {
        HttpURLConnection httpURLConnection = setUpHTTPUrlConnection(url);
        BufferedReader bufferedReader = readerForHTTPUrl(httpURLConnection);
        return readDataUsingBufferedReader(bufferedReader);
    }

    private JSONArray getJSONArrayFromJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject(JSONData);
        return jsonObject.getJSONArray("contacts");
    }

    private HashMap<String, String> createHashMapFromJSONObject(JSONObject contactObject) throws JSONException {
        HashMap<String, String> contacts = new HashMap<>();
        contacts.put("name", contactObject.getString("name"));
        contacts.put("email", contactObject.getString("email"));
        return contacts;
    }
}