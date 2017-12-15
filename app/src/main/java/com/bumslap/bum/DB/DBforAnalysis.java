package com.bumslap.bum.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.kakao.network.INetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oyoun on 17. 12. 6.
 */

public class DBforAnalysis extends SQLiteOpenHelper{

    private Context context;

    public DBforAnalysis(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        this.context = context;
    }
    /**
     * Database 가 존재하지 않을 때, 딱 한 번 실행된다.
     * DB를 만드는 역할을 한다.
     */

    @Override
    public void onCreate(SQLiteDatabase db){
        //String도 가능하지만, StringBuffer 가 Query 만들기 더 편하다.
        StringBuffer sbMenu = new StringBuffer();
        sbMenu.append(" CREATE TABLE MENU_TABLE ( ");
        sbMenu.append(" MENU_ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sbMenu.append(" MENU_NAME TEXT, ");
        sbMenu.append(" MENU_IMAGE TEXT, ");
        sbMenu.append(" MENU_PRICE TEXT,");
        sbMenu.append(" MENU_COST TEXT); ");

        // SQLite Database로 쿼리 실행
        db.execSQL(sbMenu.toString());

        StringBuffer sbOrder = new StringBuffer();
        sbOrder.append(" CREATE TABLE ORDER_TABLE ( ");
        sbOrder.append(" ORDER_AMOUNT TEXT, ");
        sbOrder.append(" ORDER_DATE TEXT, ");
        sbOrder.append(" ORDER_TIME); ");
        sbOrder.append(" ORDER_FK_MENUID INTEGER); ");

        db.execSQL(sbOrder.toString());

        StringBuffer sbCost = new StringBuffer();
        sbCost.append(" CREATE TABLE COST_TABLE (");
        sbCost.append(" COST_ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sbCost.append(" COST_NAME TEXT, ");
        sbCost.append(" COST_PRICE TEXT,");
        sbCost.append(" COST_FK_MENUID INTEGER);");

        db.execSQL(sbCost.toString());

        Toast.makeText(context, "메뉴 정보 생성", Toast.LENGTH_LONG).show();
    }

    /**
     * version이  up되어서 table 구조가 변경되었을 때 실행
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();
    }

    public  void testDB(){
        SQLiteDatabase db = getReadableDatabase();
    }

    //DB Data add
    public void addCost(Cost cost){
        //사용가능한 데이터 베이스 가져오기.

        SQLiteDatabase db = getWritableDatabase();
        Integer menu_Id;

        //Menu Data insert(id는 자동 증가)
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO COST_TABLE ( ");
        sb.append(" COST_NAME, COST_PRICE, COST_FK_MENUID  ) ");
        sb.append(" VALUES ( ?, ? ,? ); ");

        db.execSQL(sb.toString(),
                new Object[]{
                    cost.getCost_name(),
                    cost.getCost_price(),
                    cost.getCost_FK_menuId()
            });
    }

    //DB Data Select
    public ArrayList<Cost> getAllCostData() {

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT COST_ID, COST_NAME, COST_PRICE, COST_FK_MENUID FROM COST_TABLE ");

        //읽기 전용 DB 객체를 생성
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sb.toString(), null);

        ArrayList<Cost> costlist = new ArrayList<>();

        Cost cost = null;
        // moveToNext 다음에 데이터가 없으면 false, 있으면 true
        while( cursor.moveToNext() ) {
            cost = new Cost();
            cost.setCost_id(cursor.getInt(0));
            cost.setCost_name(cursor.getString(1));
            cost.setCost_price(cursor.getString(2));
            cost.setCost_FK_menuId(cursor.getInt(3));
            costlist.add(cost);
        }
        cursor.close();
        return costlist;
    }

    //DB Data delete
    public void deleteCost(Integer i) {
        SQLiteDatabase db = getReadableDatabase();
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM COST_TABLE WHERE COST_ID= ? ;");

        db.execSQL(sb.toString(),
                new Object[]{
                    i
                });
    }

    //DB Data update
    public void updateCost(Cost cost) {
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE COST_TABLE SET COST_NAME= ? , COST_PRICE= ? WHERE COST_ID= ?;");

        ArrayList<Cost> costlist = new ArrayList<>();

        db.execSQL(sb.toString(),
                new Object[]{
                    cost.getCost_name(),
                    cost.getCost_price(),
                    cost.getCost_id()
                });
    }

}
