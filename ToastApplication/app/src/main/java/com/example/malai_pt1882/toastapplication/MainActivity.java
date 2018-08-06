package com.example.malai_pt1882.toastapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void toastCalled(View view) {
        //get message from edit text
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();

        //create layoutinflater with the created toast xml
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root), true);


        TextView textView = (TextView) layout.findViewById(R.id.toast_text);
        TextView textView1 = (TextView) layout.findViewById(R.id.toast_text_2);

        Toast toast = new Toast(getApplicationContext());
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        textView.setText(message);
        textView1.setText(message);
        toast.show();
    }
}
