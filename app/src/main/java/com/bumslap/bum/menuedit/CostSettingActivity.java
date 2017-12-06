package com.bumslap.bum.menuedit;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.bumslap.bum.R;

import java.util.ArrayList;

public class CostSettingActivity extends AppCompatActivity {
    ListView listView_cost;
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

    }
}

