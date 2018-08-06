package com.example.malai_pt1882.loadersplayground;

import android.provider.BaseColumns;

public final class DatabaseContract implements BaseColumns{

    private DatabaseContract(){

    }

    public static final String DB_NAME = "MyDatabase";
    public static final String TABLE_NAME = "MyTable";
    public static final String COLUMN_NAME = "userText";
}