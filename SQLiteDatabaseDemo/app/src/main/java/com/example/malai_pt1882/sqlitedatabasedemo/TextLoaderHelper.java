package com.example.malai_pt1882.sqlitedatabasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TextLoaderHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Mydatabase";
    public static int DB_VERSION = 1;
    private static TextLoaderHelper textLoaderHelper;

    public static TextLoaderHelper getInstance(Context context){
        if(textLoaderHelper == null){
            textLoaderHelper = new TextLoaderHelper(context);
        }
        return textLoaderHelper;
    }

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TextLoaderContract.TextEntry.TABLE_NAME + " (" +
                    TextLoaderContract.TextEntry._ID + " INTEGER PRIMARY KEY, " +
                    TextLoaderContract.TextEntry.COL_NAME + " TEXT) ";

    private static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TextLoaderContract.TextEntry.TABLE_NAME;

    private TextLoaderHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
}
