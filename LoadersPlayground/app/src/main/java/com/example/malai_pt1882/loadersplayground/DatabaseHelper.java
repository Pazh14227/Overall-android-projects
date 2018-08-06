package com.example.malai_pt1882.loadersplayground;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String CREATE_TABLE = "CREATE TABLE " + DatabaseContract.TABLE_NAME +
            "( " + DatabaseContract.COLUMN_NAME + " TEXT)";

    private static DatabaseHelper databaseHelper = null;


    private DatabaseHelper(Context context) {
        super(context, DatabaseContract.DB_NAME, null, 1);
    }

    public static DatabaseHelper getInstance(Context context){

        if(databaseHelper == null){
            databaseHelper = new DatabaseHelper(context);
        }

        return databaseHelper;
    }

    public static DatabaseHelper getInstance(){
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String text,SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.COLUMN_NAME,text);

        db.insert(DatabaseContract.TABLE_NAME,null,contentValues);
    }

    public int deleteData(SQLiteDatabase db, String whereClause, String[] whereArgs){
        return db.delete(DatabaseContract.TABLE_NAME,whereClause,whereArgs);
    }

    public Cursor getData(SQLiteDatabase db){
        return db.query(DatabaseContract.TABLE_NAME,null,null,null,null,null,null);
    }
}
