package com.example.fapp_navi_drawer.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import com.example.fapp_navi_drawer.bll.Uebung;

import java.util.ArrayList;

/**
 * Created by pupil on 4/2/19.
 */

public class DatabaseManagerUebung {
    private DatabaseHelperUebung dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManagerUebung(Context context ) {
        this.context = context;
    }

    public DatabaseManagerUebung open() throws SQLException {
        this.dbHelper = new DatabaseHelperUebung(this.context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        this.dbHelper.close();
    }

    public void insert( Uebung u ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperUebung.BEZEICHNUNG, u.getBezeichnung());
        contentValues.put(DatabaseHelperUebung.BESCHREIBUNG, u.getBeschreibung());
        contentValues.put(DatabaseHelperUebung.SETS, u.getSets());
        contentValues.put(DatabaseHelperUebung.WIEDERHOLUNGEN, u.getWdh());
        contentValues.put(DatabaseHelperUebung.GEWICHT, u.getGwt());
        contentValues.put(DatabaseHelperUebung.MUSKELGRUPPE, u.getMuseklgruppe().toString());
        database.insert( DatabaseHelperUebung.TABLE_NAME, null, contentValues );
    }

    public ArrayList<Uebung> fetch(String selectionArgs) {
        ArrayList<Uebung> result = new ArrayList<Uebung>();

        String[] columns = new String[]{ DatabaseHelperUebung.ID, DatabaseHelperUebung.BEZEICHNUNG, DatabaseHelperUebung.BESCHREIBUNG,DatabaseHelperUebung.SETS,DatabaseHelperUebung.WIEDERHOLUNGEN,DatabaseHelperUebung.GEWICHT, DatabaseHelperUebung.MUSKELGRUPPE};
        Cursor cursor = database.query(DatabaseHelperUebung.TABLE_NAME, columns, selectionArgs, null, null, null, null );

        while(cursor != null && cursor.moveToNext()) {
            //result.add(new Uebung(cursor.getInt(0),cursor.getString(1),cursor.getString(2),MUSKELGRUPPE.valueOf(cursor.getString(3))));
        }

        return result;
    }


    public int update( Uebung u ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperUebung.BEZEICHNUNG, u.getBezeichnung());
        contentValues.put(DatabaseHelperUebung.BESCHREIBUNG, u.getBeschreibung());
        contentValues.put(DatabaseHelperUebung.SETS, u.getSets());
        contentValues.put(DatabaseHelperUebung.WIEDERHOLUNGEN, u.getWdh());
        contentValues.put(DatabaseHelperUebung.GEWICHT, u.getGwt());
        contentValues.put(DatabaseHelperUebung.MUSKELGRUPPE, u.getMuseklgruppe().toString());
        int i = database.update( DatabaseHelperUebung.TABLE_NAME, contentValues, DatabaseHelperUebung.ID + " = " + t.getId(), null );
        return i;
    }

    public void delete( Uebung u){
        database.delete(DatabaseHelperUebung.TABLE_NAME, DatabaseHelperUebung.ID + " =" + u.getId(), null);
    }

}

