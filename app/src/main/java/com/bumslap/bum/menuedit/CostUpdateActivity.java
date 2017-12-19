package com.bumslap.bum.menuedit;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.R;

import java.util.ArrayList;

public class CostUpdateActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_update);

    }


}
