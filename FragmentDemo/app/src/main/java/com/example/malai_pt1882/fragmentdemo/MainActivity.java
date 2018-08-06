package com.example.malai_pt1882.fragmentdemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button textEnableButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEnableButton = findViewById(R.id.textEnableButton);
        textEnableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                TextFragment fragment = new TextFragment();

                //For adding Fragment without UI use method add(Fragment fragment, String tag) - since it does not have UI, it is not provided with a ID.
                fragmentTransaction.add(R.id.mainActivityRoot,fragment);
                fragmentTransaction.addToBackStack("Hello Wrld");
                fragmentTransaction.commit();
            }
        });


    }



}
