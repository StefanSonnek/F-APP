package com.example.fapp_navi_drawer.DAL;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseHelper extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME = "FAPP_USER";
    // Table columns
    public static final String ID = "_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String AGE = "age";
    public static final String WEIGHT = "weight";
    public static final String HEIGHT = "height";

    public static final String DB_NAME = "HTL_VILLACH_FAPP.DB";
    public static final int DB_VERSION =1;

    private static final String CREATE_TABLE_USER = "create table "+TABLE_NAME+" ("+ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT NOT NULL, "
            + PASSWORD + " TEXT NOT NULL, "
            + AGE + " INTEGER, "
            + WEIGHT + " INTEGER, "
            + HEIGHT + " INTEGER); ";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DatabaseHelper(Context context ){
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
