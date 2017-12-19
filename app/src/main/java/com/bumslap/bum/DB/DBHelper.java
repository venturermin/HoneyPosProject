package com.bumslap.bum.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import junit.runner.Version;

/**
 * Created by oyoun on 17. 12. 11.
 */

public class DBHelper extends SQLiteOpenHelper {


    private static final String MENU_TABLE = "Menu";
    private static final String DB = "menu2.db";
    private static final Integer ID = 0;
    private static final int VERSION = 6;

    public DBHelper(Context c){
        super(c, DB, null, VERSION);
    }


    // public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
    //      super(context, name, factory, version);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }





    public SQLiteDatabase getDB(){
        SQLiteDatabase database = this.getReadableDatabase();
        return database;
    }


}
