package com.bumslap.bum.POSproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


// hi
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton BtnStart = (ImageButton)findViewById(R.id.button_Start);
        BtnStart.setOnClickListener(BtnClick);

        ImageButton BtnPrepare = (ImageButton)findViewById(R.id.button_Prepare);
        BtnPrepare.setOnClickListener(BtnClick);

        ImageButton BtnAnalysis = (ImageButton)findViewById(R.id.button_Analysis);
        BtnAnalysis.setOnClickListener(BtnClick);

        ImageButton BtnSetting = (ImageButton)findViewById(R.id.button_Setting);
        BtnSetting.setOnClickListener(BtnClick);
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