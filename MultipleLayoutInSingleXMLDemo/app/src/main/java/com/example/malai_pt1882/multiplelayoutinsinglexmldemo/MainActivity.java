package com.example.malai_pt1882.multiplelayoutinsinglexmldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View view){
        Intent intent = new Intent(getApplicationContext(),DisplayView.class);
        startActivity(intent);
    }
}
