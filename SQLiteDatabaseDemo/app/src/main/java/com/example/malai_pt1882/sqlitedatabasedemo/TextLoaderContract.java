package com.example.malai_pt1882.sqlitedatabasedemo;

import android.provider.BaseColumns;

public class TextLoaderContract {

    private TextLoaderContract(){

    }

    /**
     * Base columns will provide _ID as default primary key and some class such as contact adapters expect basecolumns.
     */
    public static class TextEntry implements BaseColumns{
        public static final String TABLE_NAME = "Text";
        public static final String COL_NAME = "Description";
    }
}
