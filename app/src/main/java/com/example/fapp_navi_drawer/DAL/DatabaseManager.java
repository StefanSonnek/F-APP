package com.example.fapp_navi_drawer.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;


public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager( Context context ) {
        this.context = context;
    }

    public DatabaseManager open() throws SQLException {
        this.dbHelper = new DatabaseHelper(this.context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        this.dbHelper.close();
    }

    public void insert( String username, String password, int age, int weight, int height ){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.USERNAME, username);
        contentValues.put( DatabaseHelper.PASSWORD, password);
        contentValues.put( DatabaseHelper.AGE, age);
        contentValues.put( DatabaseHelper.WEIGHT, weight);
        contentValues.put( DatabaseHelper.HEIGHT, height);

        database.insert( DatabaseHelper.TABLE_NAME, null, contentValues );
    }

    public Cursor fetch(){
        String[] columns = new String[]{ DatabaseHelper.ID, DatabaseHelper.USERNAME, DatabaseHelper.PASSWORD, DatabaseHelper.AGE, DatabaseHelper.WEIGHT, DatabaseHelper.HEIGHT};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);

        if( cursor != null ){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update( long id, String username, String password, int age, int weight, int height){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USERNAME, username);
        contentValues.put( DatabaseHelper.PASSWORD, password);
        contentValues.put( DatabaseHelper.AGE, age);
        contentValues.put( DatabaseHelper.WEIGHT, weight);
        contentValues.put( DatabaseHelper.HEIGHT, height);

        int i = database.update( DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.ID + " = " + id, null );
        return i;
    }

    public void delete( long id){
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.ID + " ="+id, null);
    }
}
