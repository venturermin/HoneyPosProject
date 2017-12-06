package com.bumslap.bum.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumslap.bum.R;

public class UserSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        setTitle("설정");
    }
}
