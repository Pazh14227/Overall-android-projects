package com.example.malai_pt1882.loadersplayground;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SecondaryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<SQLiteDatabase> {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        linearLayout = findViewById(R.id.linear_layout2);

        getSupportLoaderManager().initLoader(0,null,this).forceLoad();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @NonNull
    @Override
    public Loader<SQLiteDatabase> onCreateLoader(int id, @Nullable Bundle args) {
        return new MyCustomLoader(getApplicationContext());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d("SecondaryActivity","called onRestoreInstanceState");
//        scrollView.removeAllViews();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.d("SecondaryActivity","Called onConfigurationChanged");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<SQLiteDatabase> loader, SQLiteDatabase data) {

        Cursor cursor = MainActivity.databaseHelper.getData(data);

        if(cursor!=null && cursor.getCount()>0){

            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                TextView textView = new TextView(this);
                textView.setTextSize(14);
                textView.setTextColor(getResources().getColor(R.color.black));
                textView.setPadding(0,20,0,0);
                textView.setText(cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_NAME)));
                linearLayout.addView(textView);
            }

            data.close();
        } else{

            Toast.makeText(this,R.string.noData,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    public static class MyCustomLoader extends AsyncTaskLoader<SQLiteDatabase> {

        MyCustomLoader(@NonNull Context context) {
            super(context);
        }

        @Nullable
        @Override
        public SQLiteDatabase loadInBackground() {
            return MainActivity.databaseHelper.getReadableDatabase();
        }
    }

}
