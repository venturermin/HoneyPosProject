package com.bumslap.bum.POSproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.bumslap.bum.R;


public class MainActivity extends AppCompatActivity {
    ImageButton BtnStart;
    ImageButton BtnPrepare;
    ImageButton BtnAnalysis;
    ImageButton BtnSetting;
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
    }

   ImageButton.OnClickListener BtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button_Start:
                    BtnStart.setBackgroundResource(R.drawable.button_color);
                    Intent intent = new Intent(getApplicationContext(), com.bumslap.bum.order.OrderActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_Prepare:
                    BtnStart.setBackgroundResource(R.drawable.button_color);
                    intent = new Intent(getApplicationContext(), com.bumslap.bum.menuedit.MenuSettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_Analysis:
                    BtnStart.setBackgroundResource(R.drawable.button_color);
                    intent = new Intent(getApplicationContext(), com.bumslap.bum.statistics.PieChartDataActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_Setting:
                    BtnStart.setBackgroundResource(R.drawable.button_color);
                    intent = new Intent(getApplicationContext(), com.bumslap.bum.settings.UserSettingActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}