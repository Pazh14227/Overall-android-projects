package com.example.malai_pt1882.verticalscrollview;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(getApplicationContext());

        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.activity_main,(ViewGroup)findViewById(R.id.linears));

        horizontalScrollView.addView(view);

        setContentView(horizontalScrollView);
    }
}
