package com.bumslap.bum.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by min on 12/15/17.
 */

public class DBProvider {
    private Context context;
    private SQLiteDatabase db ;
    private DBHelper dbHelper;


    public DBProvider(Context ctx){
        context = ctx;
    }


    public DBProvider open() throws SQLException {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public Cursor getData(String sql){
        return db.rawQuery(sql, null);
    }

    public void queryData(String sql){
        db  = dbHelper.getWritableDatabase();
        db.execSQL(sql);
    }

    public void insertData(String name, String price, String cost, byte[] image){
         db = dbHelper.getWritableDatabase();
        String sql = "INSERT INTO MENU_TABLE VALUES (NULL, ?, ?, ?, ?)";

        SQLiteStatement statement = db.compileStatement(sql);

        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindString(3, cost);
        statement.bindBlob(4, image);

        statement.executeInsert();
    }

    public void deleteData(String id) {
        //String sql = "DELETE FROM MENU_TABLE WHERE id = ?";
        //SQLiteStatement statement = db.compileStatement(sql);
        //statement.clearBindings();
       // statement.bindDouble(1, (double) id);
        //statement.execute();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DELETE FROM MENU_TABLE WHERE ID = ? ");
        db.execSQL(stringBuffer.toString(), new Object[]{id});
        db.delete("MENU_TABLE", id   + " = ? ", new String[] { id });
        //}
    }
    public void close(){
        dbHelper.close();
    }

}
