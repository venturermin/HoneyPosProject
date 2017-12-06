package com.bumslap.bum.menuedit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumslap.bum.R;

public class MenuUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_update);
        setTitle("메뉴 등록");
    }
}
