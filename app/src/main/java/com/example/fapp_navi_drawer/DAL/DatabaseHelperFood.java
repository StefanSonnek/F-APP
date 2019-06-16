package com.example.fapp_navi_drawer.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperFood extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME = "Food";
    // Table columns
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String CALORIES = "calories";
    public static final String PROTEIN = "protein";
    public static final String CARBS = "carbs";
    public static final String FAT = "fat";
    public static final String DB_NAME = "HTL_VILLACH_FOOD.DB";
    public static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_FOOD = "create table " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
            + " TEXT NOT NULL, " + CALORIES + " INTEGER NOT NULL, "
            + PROTEIN + " INTEGER NOT NULL," + CARBS + " INTEGER NOT NULL," + FAT + " INTEGER NOT NULL);";

    public DatabaseHelperFood(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DatabaseHelperFood(Context context ){
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_TABLE_FOOD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
