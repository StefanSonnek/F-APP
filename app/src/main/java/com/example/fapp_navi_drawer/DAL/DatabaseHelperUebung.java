package com.example.fapp_navi_drawer.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pupil on 4/2/19.
 */

public class DatabaseHelperUebung extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "WORKOUT";
    // Table columns
    public static final String ID = "_id";
    public static final String BEZEICHNUNG = "bezeichnung";
    public static final String BESCHREIBUNG = "beschreibung";
    public static final String SETS = "sets";
    public static final String WIEDERHOLUNGEN = "wiederholung";
    public static final String GEWICHT = "gewicht";
    public static final String MUSKELGRUPPE = "muskelgruppe";
    public static final String DB_NAME = "HTL_VILLACH_WORKOUT.DB";
    public static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_STUDENT = "create table " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + BEZEICHNUNG
            + " TEXT NOT NULL, " + BESCHREIBUNG + " TEXT NOT NULL, "+SETS+ " TEXT NOT NULL, "+WIEDERHOLUNGEN+ " TEXT NOT NULL, "+GEWICHT+ " TEXT NOT NULL, "
            + MUSKELGRUPPE + " TEXT NOT NULL);";

    public DatabaseHelperUebung(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DatabaseHelperUebung(Context context ){
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}