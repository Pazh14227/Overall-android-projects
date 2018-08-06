package com.example.malai_pt1882.contentprovideremployeedatabase.employee_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmployeeDatabaseHelper extends SQLiteOpenHelper {

    private static int version = 1;

    private static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " +
            EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME + " ( " +
            EmployeeDatabaseContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            EmployeeDatabaseContract.EMPLOYEE_NAME_COLUMN + " TEXT, " +
            EmployeeDatabaseContract.EMPLOYEE_SALARY_COLUMN + " INTEGER)";

    private static final String DELETE_EMPLOYEE_TABLE = "DROP TABLE IF EXISTS " +EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME ;

    public EmployeeDatabaseHelper(Context context) {
        super(context, EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_EMPLOYEE_TABLE);
        db.execSQL(CREATE_EMPLOYEE_TABLE);
    }
}
