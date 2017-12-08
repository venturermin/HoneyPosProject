package com.bumslap.bum.POSproject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.DB.Menu;
import com.bumslap.bum.R;


public class MainActivity extends AppCompatActivity {
    ImageButton BtnStart;
    ImageButton BtnPrepare;
    ImageButton BtnAnalysis;
    ImageButton BtnSetting;
    SQLiteDatabase mdb;
    DBforAnalysis dbHelper;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnStart = (ImageButton)findViewById(R.id.button_Start);
        BtnStart.setOnClickListener(BtnClick);

        BtnPrepare = (ImageButton)findViewById(R.id.button_Prepare);
        BtnPrepare.setOnClickListener(BtnClick);

        BtnAnalysis = (ImageButton)findViewById(R.id.button_Analysis);
        BtnAnalysis.setOnClickListener(BtnClick);

        BtnSetting = (ImageButton)findViewById(R.id.button_Setting);
        BtnSetting.setOnClickListener(BtnClick);

        dbHelper = new DBforAnalysis(this, "test.db", null,1);
        mdb = dbHelper.getWritableDatabase();

        menu = new Menu();
        dbHelper.addMenu(menu);
    }

    ImageButton.OnClickListener BtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button_Start:
                    Intent intent = new Intent(getApplicationContext(), com.bumslap.bum.order.OrderActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_Prepare:
                    intent = new Intent(getApplicationContext(), com.bumslap.bum.menuedit.MenuSettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_Analysis:
                    intent = new Intent(getApplicationContext(), com.bumslap.bum.statistics.PieChartDataActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_Setting:
                    intent = new Intent(getApplicationContext(), com.bumslap.bum.settings.UserSettingActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}

