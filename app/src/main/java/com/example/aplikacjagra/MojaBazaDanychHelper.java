package com.example.aplikacjagra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MojaBazaDanychHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RekordyBazaDanych.db";

    private static final String TABLE_NAME = "rekordy";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "nazwa";
    private static final String COLUMN_TIME = "czas";

    MojaBazaDanychHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT," +
                        COLUMN_TIME + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    void dodajRekord(String nazwa, String czas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, nazwa);
        cv.put(COLUMN_TIME, czas);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) Toast.makeText(context, "Próba dodania rekordu nieudana", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Rekord został pomyślnie dodany", Toast.LENGTH_SHORT).show();
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
