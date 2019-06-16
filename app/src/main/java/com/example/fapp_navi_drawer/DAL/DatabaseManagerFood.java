package com.example.fapp_navi_drawer.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.fapp_navi_drawer.bll.Food;

import java.util.ArrayList;

public class DatabaseManagerFood {
    private DatabaseHelperFood dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManagerFood( Context context ) {
        this.context = context;
    }

    public DatabaseManagerFood open() throws SQLException {
        this.dbHelper = new DatabaseHelperFood(this.context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        this.dbHelper.close();
    }

    public void insert( Food f ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperFood.NAME, f.getName());
        contentValues.put(DatabaseHelperFood.CALORIES, f.getCalories());
        contentValues.put(DatabaseHelperFood.PROTEIN, f.getProtein());
        contentValues.put(DatabaseHelperFood.CARBS, f.getCarbs());
        contentValues.put(DatabaseHelperFood.FAT, f.getFat());
        database.insert( DatabaseHelperFood.TABLE_NAME, null, contentValues );
    }

    public ArrayList<Food> fetch() {
        ArrayList<Food> result = new ArrayList<Food>();

        String[] columns = new String[]{ DatabaseHelperFood.ID, DatabaseHelperFood.NAME, DatabaseHelperFood.CALORIES, DatabaseHelperFood.PROTEIN, DatabaseHelperFood.CARBS,DatabaseHelperFood.FAT};
        Cursor cursor = database.query(DatabaseHelperFood.TABLE_NAME, columns, null, null, null, null, null );

        while(cursor != null && cursor.moveToNext()) {
            result.add(new Food(cursor.getString(1), cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5)));
        }
        return result;
    }

/*
    public int update( Food g ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperToDo.BEZEICHNUNG, t.getBezeichnung());
        contentValues.put(DatabaseHelperToDo.BESCHREIBUNG, t.getBeschreibung());
        contentValues.put(DatabaseHelperToDo.PRIORITAET, t.getPrioritaet().toString());
        contentValues.put(DatabaseHelperToDo.ERLEDIGT, String.valueOf(t.isErledigt()));
        int i = database.update( DatabaseHelperToDo.TABLE_NAME, contentValues, DatabaseHelperToDo.ID + " = " + t.getId(), null );
        return i;
    }

    public void delete( ToDo t){
        database.delete(DatabaseHelperToDo.TABLE_NAME, DatabaseHelperToDo.ID + " =" + t.getId(), null);
    }
    */

}
