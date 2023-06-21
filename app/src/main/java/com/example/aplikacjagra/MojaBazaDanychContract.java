package com.example.aplikacjagra;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class MojaBazaDanychContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private MojaBazaDanychContract() {}

    /* Inner class that defines the table contents */
    /*
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "rekordy";
        public static final String COLUMN_NAME_NICK = "nazwa";
        public static final String COLUMN_NAME_TIME = "czas";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_NICK + " TEXT," +
                    FeedEntry.COLUMN_NAME_TIME + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

*/
}

