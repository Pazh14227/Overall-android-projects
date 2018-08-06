package com.example.malai_pt1882.taskwithalllayouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import App.constant.Constant;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button relativeButton = (Button)findViewById(R.id.button2);
        relativeButton.setAllCaps(false);
    }

    public void startActivity(View view){
        Intent intent;
        intent = new Intent(getApplicationContext(), pazhamalai.layout.LayoutActivity.class);
        if(view.getId() == com.example.malai_pt1882.taskwithalllayouts.Constant.LINEAR_LAYOUT_BUTTON_ID){
            intent.putExtra(Constant.LAYOUT_TYPE,Constant.LINEAR_LAYOUT);
        }
        else if(view.getId() == com.example.malai_pt1882.taskwithalllayouts.Constant.RELATIVE_LAYOUT_BUTTON_ID){
            intent.putExtra(Constant.LAYOUT_TYPE,Constant.RELATIVE_LAYOUT);
        }
        else{
            intent.putExtra(Constant.LAYOUT_TYPE,Constant.FRAME_LAYOUT);
        }
        startActivity(intent);
    }
}