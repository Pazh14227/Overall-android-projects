package com.example.malai_pt1882.loadersplayground;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static DatabaseHelper databaseHelper;
    private static SQLiteDatabase sqLiteDatabase = null;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = DatabaseHelper.getInstance(getApplicationContext());

        DatabaseTask databaseTask = new DatabaseTask();
        databaseTask.execute(databaseHelper);

        final TextInputEditText editText = findViewById(R.id.entry_edit_text);

        Button button1 = findViewById(R.id.first_button);

        TextView button2 = findViewById(R.id.second_button);

        button1.clearFocus();
        button2.clearFocus();

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setFocusableInTouchMode(true);
                editText.setFocusable(true);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Button1 onClickListener", editText.getText().toString());
                if(!editText.getText().toString().equals("")) {
                        databaseHelper.insertData(editText.getText().toString(), sqLiteDatabase);
                        editText.setText("");
                        Toast.makeText(MainActivity.this, R.string.dataAdded, Toast.LENGTH_SHORT).show();
                        editText.setFocusable(false);
                } else{
                    Toast.makeText(MainActivity.this,R.string.enterText,Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);
                startActivity(intent);

                editText.setFocusable(false);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
    }

    private static class DatabaseTask extends AsyncTask<DatabaseHelper,Void,SQLiteDatabase>{

        @Override
        protected SQLiteDatabase doInBackground(DatabaseHelper... databaseHelpers) {
            return databaseHelpers[0].getWritableDatabase();
        }

        @Override
        protected void onPostExecute(SQLiteDatabase sqLiteDatabase) {
            super.onPostExecute(sqLiteDatabase);

            MainActivity.sqLiteDatabase =sqLiteDatabase;
        }
    }

}