package com.bumslap.bum.menuedit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumslap.bum.R;


public class MenuSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_setting);
        ImageView BTNMenuUpdate = (ImageView)findViewById(R.id.imageView);
        BTNMenuUpdate.setOnClickListener(updatemenu);

        Button BTNMenuUpdate2 = (Button)findViewById(R.id.button);
        BTNMenuUpdate2.setOnClickListener(updatemenu2);

    }

    Button.OnClickListener updatemenu2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), MenuUpdateActivity.class);
            startActivity(intent);
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
