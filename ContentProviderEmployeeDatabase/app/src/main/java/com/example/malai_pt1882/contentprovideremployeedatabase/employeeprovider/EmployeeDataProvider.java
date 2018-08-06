package com.example.malai_pt1882.contentprovideremployeedatabase.employeeprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.malai_pt1882.contentprovideremployeedatabase.employee_database.EmployeeDatabaseContract;
import com.example.malai_pt1882.contentprovideremployeedatabase.employee_database.EmployeeDatabaseHelper;

public class EmployeeDataProvider extends ContentProvider {

    private static final String AUTHORITIES = "com.example.malai_pt1882.contentprovideremployeedatabase.employeeprovider";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int EMPLOYEE_TABLE_URI = 1;

    private SQLiteDatabase employeeDatabase;

    private static final String FULL_EMPLOYEE_TABLE_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + "vnd." + AUTHORITIES + "." + EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME;

    static{
        uriMatcher.addURI(AUTHORITIES,EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME,EMPLOYEE_TABLE_URI);
    }

    @Override
    public boolean onCreate() {

        EmployeeDatabaseHelper employeeDatabaseHelper = new EmployeeDatabaseHelper(getContext());
        employeeDatabase = employeeDatabaseHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        if(uriMatcher.match(uri) == EMPLOYEE_TABLE_URI){
            return employeeDatabase.query(EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
        }

        int column = (int)ContentUris.parseId(uri);

        if(column!=-1){
            return employeeDatabase.query(EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME,null,EmployeeDatabaseContract._ID + " = " + column,null,null,null,null);
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        if(uriMatcher.match(uri) == EMPLOYEE_TABLE_URI){
            return FULL_EMPLOYEE_TABLE_MIME_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        Log.d("match uri","successful");
        if(uriMatcher.match(uri) == EMPLOYEE_TABLE_URI) {
            if(values != null) {
                int id = (int)employeeDatabase.insert(EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME,null,values);
                if(id!=-1) {
                    return ContentUris.withAppendedId(uri, id);
                }
            }
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        if(uriMatcher.match(uri) == EMPLOYEE_TABLE_URI){
            return employeeDatabase.delete(EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME,selection,selectionArgs);
        }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        if(uriMatcher.match(uri) == EMPLOYEE_TABLE_URI){
            if(values!=null) {
                return employeeDatabase.update(EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME, values, selection, selectionArgs);
            }
        }
        return 0;
    }
}
