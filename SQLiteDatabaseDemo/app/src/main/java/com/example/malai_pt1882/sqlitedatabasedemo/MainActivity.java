package com.example.malai_pt1882.sqlitedatabasedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button addToDbButton;
    private Button openDbButton;
    private TextLoaderHelper textLoaderHelper;
    private TextView displayTextView;
    private SQLiteDatabase sqLiteDatabase;
    private Button deleteTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addToDbButton = findViewById(R.id.add_to_db_button);
        editText = findViewById(R.id.editText);
        openDbButton = findViewById(R.id.open_db_button);
        displayTextView = findViewById(R.id.display_text_view);
        deleteTextButton = findViewById(R.id.delete_text_button);

        textLoaderHelper = TextLoaderHelper.getInstance(this);

        addToDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                sqLiteDatabase = textLoaderHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(TextLoaderContract.TextEntry.COL_NAME, text);
                long id = sqLiteDatabase.insert(TextLoaderContract.TextEntry.TABLE_NAME, null, contentValues);

                if (id == -1) {
                    Toast.makeText(MainActivity.this, "Failed to store text", Toast.LENGTH_SHORT).show();
                    textLoaderHelper.onCreate(sqLiteDatabase);
                    Toast.makeText(MainActivity.this, "Created db Now enter text", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Created row with id " + id, Toast.LENGTH_SHORT).show();
                }
            }
        });

        addToDbButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sqLiteDatabase = textLoaderHelper.getWritableDatabase();
                sqLiteDatabase.execSQL("DROP TABLE " + TextLoaderContract.TextEntry.TABLE_NAME);
                Toast.makeText(MainActivity.this, "Table deleted successfully", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        openDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = textLoaderHelper.getReadableDatabase();
                String[] colName = new String[1];
                colName[0] = TextLoaderContract.TextEntry.COL_NAME;
                Cursor cursor = sqLiteDatabase.query(TextLoaderContract.TextEntry.TABLE_NAME, colName, null, null, null, null, null);
                Log.d("Cursor", "column count " + cursor.getColumnCount() + cursor.getColumnIndex(TextLoaderContract.TextEntry.COL_NAME));
                int iRow = cursor.getColumnIndex(TextLoaderContract.TextEntry._ID);
                int iCol = cursor.getColumnIndex(TextLoaderContract.TextEntry.COL_NAME);
                String result = "";
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    result = result + cursor.getString(iCol) + "\n";
                }
                displayTextView.setText(result);
                cursor.close();
            }
        });

        deleteTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                sqLiteDatabase = textLoaderHelper.getWritableDatabase();
                String[] whereArgs = {text};
                sqLiteDatabase.delete(TextLoaderContract.TextEntry.TABLE_NAME, TextLoaderContract.TextEntry.COL_NAME + " LIKE ? ",whereArgs);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqLiteDatabase.close();
    }
}