package com.bumslap.bum.menuedit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.R;

public class CostSettingActivity extends AppCompatActivity {
    ListView listView_cost;
    Button MenuSetBtn, CostSetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_setting);

        String[] Menu = {
                "피자", "짜장면", "라면", "숯불 김밥"
        };

        Spinner spinnerMenu = (Spinner)findViewById(R.id.spinnerMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                Menu);
        spinnerMenu.setAdapter(adapter);
        spinnerMenu.setSelection(0);

        MenuSetBtn = (Button)findViewById(R.id.MenuSetBtn);
        CostSetBtn = (Button)findViewById(R.id.CostSetBtn);
        MenuSetBtn.setOnClickListener(MenuCostClick);
        CostSetBtn.setOnClickListener(MenuCostClick);

    }

    Button.OnClickListener MenuCostClick = new View.OnClickListener() {
        Intent mvSettingIntent;
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.MenuSetBtn:
                    mvSettingIntent = new Intent(getApplicationContext(), MenuSettingActivity.class);
                    startActivity(mvSettingIntent);
                    break;
                case R.id.CostSetBtn:
                    mvSettingIntent = new Intent(getApplicationContext(), CostSettingActivity.class);
                    startActivity(mvSettingIntent);
                    break;
            }

        }
    };
}

