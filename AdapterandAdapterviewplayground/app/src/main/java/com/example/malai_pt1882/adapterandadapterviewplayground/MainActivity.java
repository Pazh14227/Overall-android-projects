package com.example.malai_pt1882.adapterandadapterviewplayground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] items = {"Item1","Item2","Item3","Item4","Item5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.view_for_adapter,R.id.text_view_id,items);

        ListView listView = new ListView(this);
        listView.setAdapter(arrayAdapter);
        listView.setMinimumHeight(50);
        setContentView(listView);
    }
}
