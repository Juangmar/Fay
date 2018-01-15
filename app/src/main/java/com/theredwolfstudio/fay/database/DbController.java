package com.theredwolfstudio.fay.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Koldur on 04/01/2018.
 */

public class DbController extends SQLiteOpenHelper {

    private static final String DB_NAME = "Fay";
    public static final String DB_TAB = "SimpleTasks";
    private static final int DB_VERS = 1;
    public static final String DB_EXAMP_COLUMN = "Name";

    public DbController(Context c){

        super(c, DB_NAME, null, DB_VERS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL);", this.DB_TAB, this.DB_EXAMP_COLUMN);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = String.format("DELETE TABLE IF EXISTS %s", this.DB_TAB);
        db.execSQL(query);
        onCreate(db);
    }

    public void insertSimpleTask(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_EXAMP_COLUMN, name);
        db.insertWithOnConflict(DB_TAB, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteSimpleTask(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TAB, DB_EXAMP_COLUMN + " = ?", new String[]{name});
        db.close();
    }

    public ArrayList<String> getTasksList(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> tasks = new ArrayList<String>();
        Cursor c = db.query(DB_TAB, new String[]{DB_EXAMP_COLUMN}, null, null, null, null, null);

        while(c.moveToNext()){
            int index = c.getColumnIndex(DB_EXAMP_COLUMN);
            tasks.add(c.getString(index));
        }
        c.close();
        db.close();

        return tasks;
    }
}
