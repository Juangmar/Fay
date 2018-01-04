package com.theredwolfstudio.fay.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Koldur on 04/01/2018.
 */

public class DbController extends SQLiteOpenHelper {

    private static final String DB_NAME = "Fay";
    public static final String DB_TAB = "SimpleTasks";
    private static final int DB_VERS = 1;
    public static final String DB_EXAMP_COLUMN = "Name";

    public DbController(Context c){
        super(c, this.DB_NAME, null, this.DB_VERS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL)", this.DB_TAB, this.DB_EXAMP_COLUMN);
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
    }
}
