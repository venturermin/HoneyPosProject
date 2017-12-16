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
    Button MenuSetBtn, CostSetBtn;
    Intent mvSetIntent;
    private GestureDetector gestureDetector;
    FloatingActionButton floatingActionButton_cost;
    DBforAnalysis dBforAnalysis;
    IngradientAdapter IngradientAdapter;
    Spinner spinnerMenu;
    RecyclerView recyclerView;
    CostAdapter costAdapter;
    ArrayList<Cost> arrayList;
    SQLiteDatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_update);
/*
        arrayList = new ArrayList<Cost>();

        dBforAnalysis = new DBforAnalysis(this, "postest.db", null,1);
        mdb = dBforAnalysis.getWritableDatabase();

        Cost cost = new Cost();
        arrayList = dBforAnalysis.getAllCostData();

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        costAdapter = new CostAdapter(arrayList, this);

        costAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(costAdapter);*/
    }
}
