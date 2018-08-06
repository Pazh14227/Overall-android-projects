package com.example.malai_pt1882.imageviewdemo;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private String line1 = "This is a winter painting";
    private String line2 = "Landscape category painting";
    private String line3 = "Painter bob ross";
    private String line4 = "Beautiful painting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);

        textView1.setText(line1);
        textView2.setText(line2);
        textView3.setText(line3);
        textView4.setText(line4);
    }

    public void onClick(View view) {
        //converting xml to view object
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, null);

        String textEntered = textView1.getText().toString() + " " + textView2.getText().toString() + " " + textView3.getText().toString() + " " + textView4.getText().toString();

        //creating new toast
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        //adding text to toast
        TextView textViewForToast = (TextView) layout.findViewById(R.id.textViewForToast);
        textViewForToast.setText(textEntered);

        //TODO Adding image to toast
//        ImageView imageView = (ImageView) layout.findViewById(R.id.image_for_toast);
//        imageView.setImageResource(R.drawable.dice_image);
        toast.show();
    }


}
