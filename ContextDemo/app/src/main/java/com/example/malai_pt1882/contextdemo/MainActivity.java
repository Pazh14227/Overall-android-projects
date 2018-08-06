package com.example.malai_pt1882.contextdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = new TextView(this);
        textView.setText("Hello world");

        TextView textView1 = new TextView(getApplicationContext());
        textView1.setText("Hello world");

        LinearLayout linearLayout = findViewById(R.id.linear_layout_id);
        linearLayout.addView(textView);
        linearLayout.addView(textView1);
    }
}
