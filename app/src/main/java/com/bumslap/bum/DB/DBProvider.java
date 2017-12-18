package com.bumslap.bum.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
