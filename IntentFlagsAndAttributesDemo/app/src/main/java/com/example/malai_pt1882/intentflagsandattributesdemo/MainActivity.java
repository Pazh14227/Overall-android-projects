package com.example.malai_pt1882.intentflagsandattributesdemo;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newActivityButtonClicked(View view){
        Intent intent = new Intent(this,SecondaryActivity.class);
        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onStart() {
        super.onStart();

        createLog("onStart method");
    }

    @Override
    protected void onPause() {
        super.onPause();

        createLog("onPause method");
    }

    @Override
    protected void onResume() {
        super.onResume();

        createLog("onResume method");
    }

    @Override
    protected void onStop() {
        super.onStop();

        createLog("onStop method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        createLog("onDestroy method");
    }

    private void createLog(String msg){
        Log.d("LifeCycle tag ",msg);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("In onNewIntent","Called due to launchmode specified in android manifest");

        super.onNewIntent(intent);

    }
}
