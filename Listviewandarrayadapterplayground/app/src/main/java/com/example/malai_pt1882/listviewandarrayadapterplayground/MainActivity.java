package com.example.malai_pt1882.listviewandarrayadapterplayground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_layout,R.id.list_text_view_id,days);
        ListView listView = new ListView(this);
        listView.setAdapter(arrayAdapter);

        LinearLayout frameLayout = findViewById(R.id.main_frame_layout_id);
        frameLayout.addView(listView);

        setContentView(frameLayout);
    }
}
