package com.bumslap.bum.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
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
        sbMenu.append(" MENU_IMAGE BLOB, ");
        sbMenu.append(" MENU_PRICE TEXT, ");
        sbMenu.append(" MENU_COST TEXT); ");

        // SQLite Database로 쿼리 실행
        db.execSQL(sbMenu.toString());

        StringBuffer sbOrder = new StringBuffer();
        sbOrder.append(" CREATE TABLE ORDER_TABLE ( ");
        sbOrder.append(" ORDER_AMOUNT TEXT, ");
        sbOrder.append(" ORDER_DATE TEXT, ");
        sbOrder.append(" ORDER_TIME, ");
        sbOrder.append(" ORDER_FK_MENUID INTEGER); ");

        db.execSQL(sbOrder.toString());

        StringBuffer sbCost = new StringBuffer();
        sbCost.append(" CREATE TABLE COST_TABLE (");
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

    //DB 에 데이터를 넣을 메소드 생성
    public void addMenu(Menu menu){

        //사용가능한 데이터 베이스 가져오기.
        SQLiteDatabase db = getWritableDatabase();

        //Menu Data insert(id는 자동 증가)
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO MENU_TABLE ( ");
        sb.append(" MENU_NAME, MENU_IMAGE, MENU_PRICE, MENU_COST ) ");
        sb.append(" VALUES ( ?, ?, ?, ? ); ");

        db.execSQL(sb.toString(),
                new Object[]{
                        menu.getMenu_name(),
                        menu.getMenu_image(),
                        menu.getMenu_price(),
                        menu.getMenu_cost()
                });
        Toast.makeText(context, "Menu 등록 완료.", Toast.LENGTH_LONG).show();
    }

    public List getAllMenuData() {

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT MENU_ID, MENU_NAME, MENU_IMAGE, MENU_PRICE, MENU_COST FROM MENU_TABLE ");

        //읽기 전용 DB 객체를 생성
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sb.toString(), null);

        List menulist = new ArrayList();
        Menu menu = null;

        // moveToNext 다음에 데이터가 없으면 false, 있으면 true
        while( cursor.moveToNext() ) {
            menu = new Menu();
            menu.setMenu_id(cursor.getInt(0));
            menu.setMenu_name(cursor.getString(1));
            menu.setMenu_image(cursor.getBlob(2));
            menu.setMenu_price(cursor.getString((3)));
            menu.setMenu_cost(cursor.getString((4)));

            menulist.add(menu);
        }
        cursor.close();
        return menulist;
    }

    public Menu getMenuById(int Menu_Id){

        StringBuffer sbGetMenu = new StringBuffer();
        sbGetMenu.append(" SELECT MENU_NAME, MENU_IMAGE, MENU_PRICE, MENU_COST FROM MENU_TABLE WHERE MENU_ID = ? ");

        //읽기 전용 DB 객체 생성.
        SQLiteDatabase dbRead = getReadableDatabase();
        Cursor cursor = dbRead.rawQuery(sbGetMenu.toString(), new String[]{Menu_Id + ""});

        Menu menu = new Menu();
        if(cursor.moveToNext()){
            menu.setMenu_name(cursor.getString(0));
            menu.setMenu_image(cursor.getBlob(1));
            menu.setMenu_price(cursor.getString((2)));
            menu.setMenu_cost(cursor.getString((3)));
        }
        cursor.close();
        return menu;
    }
}
