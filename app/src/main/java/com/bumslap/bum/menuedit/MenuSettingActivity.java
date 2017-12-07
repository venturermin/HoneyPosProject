package com.bumslap.bum.menuedit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumslap.bum.R;


public class MenuSettingActivity extends AppCompatActivity {
    Button MenuSetBtn, CostSetBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_setting);
        ImageView BTNMenuUpdate = (ImageView)findViewById(R.id.imageView);
        BTNMenuUpdate.setOnClickListener(updatemenu);

        Button MenuSetBtn = (Button)findViewById(R.id.MenuSetBtn);
        Button CostSetBtn = (Button)findViewById(R.id.CostSetBtn);
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

    ImageView.OnClickListener updatemenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), MenuUpdateActivity.class);
            startActivity(intent);
        }
    };
}
