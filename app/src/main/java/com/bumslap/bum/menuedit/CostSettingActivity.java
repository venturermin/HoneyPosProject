package com.bumslap.bum.menuedit;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.R;

import java.util.ArrayList;

public class CostSettingActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    Button MenuSetBtn, CostSetBtn;
    Intent mvSetIntent;
    private GestureDetector gestureDetector;
    ListView LIstViewIngradient;
    FloatingActionButton floatingActionButton_cost;
    DBforAnalysis dBforAnalysis;
    Button button_save;
    IngradientAdapter IngradientAdapter;
    ArrayList<Cost> costlist;
    Spinner spinnerMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_setting);
        this.gestureDetector = new GestureDetector(this,this);

        floatingActionButton_cost = (FloatingActionButton)findViewById(R.id.floatingActionButton_cost);
        floatingActionButton_cost.setOnClickListener(AddIngradient);

        //spinner
        String[] Menu = {
                "피자", "짜장면", "라면", "숯불 김밥"
        };

        spinnerMenu = (Spinner)findViewById(R.id.spinnerMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                Menu);
        spinnerMenu.setAdapter(adapter);
        spinnerMenu.setSelection(0);

        //listview_ingradient
        LIstViewIngradient = (ListView) findViewById(R.id.listView_Ingradient);

        dBforAnalysis = new DBforAnalysis(this,"test.db", null,1);
        costlist = new ArrayList<>();

        costlist = dBforAnalysis.getAllCostData();
        IngradientAdapter = new IngradientAdapter(this, costlist);
        LIstViewIngradient.setAdapter(IngradientAdapter);
        IngradientAdapter.notifyDataSetChanged();



        button_save = (Button)findViewById(R.id.button_edit);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String Name = IngradientAdapter.IngradientName.getText().toString();
                String Price = IngradientAdapter.IngradientPrice.getText().toString();
                Integer menu_ID = 3;
                //costclass.setCost_name(Name);
                //costclass.setCost_name(Price);
                //DBforAnalysis.addCost(InsertCost);*/
            }
        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        super.dispatchTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) { }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) { return false; }

    @Override
    public void onLongPress(MotionEvent motionEvent) { }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        float diffY = motionEvent1.getY() - motionEvent.getY();
        if (diffY < 0) {
            // Create the Snackbar
            LayoutInflater mInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = findViewById(R.id.cost_setting_layout);
            ConstraintLayout.LayoutParams objLayoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
            // Get the Snackbar's layout view
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
            layout.setPadding(0,0,0,0);

            // Inflate our custom view
            View snackView = getLayoutInflater().inflate(R.layout.activity_snackbar_setting, null);
            // Configure the view
            MenuSetBtn = (Button)snackView.findViewById(R.id.MenuSetBtn);
            CostSetBtn = (Button)snackView.findViewById(R.id.CostSetBtn);

            MenuSetBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mvSetIntent = new Intent(getApplication(), MenuSettingActivity.class);
                    startActivity(mvSetIntent);
                }
            });

            CostSetBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mvSetIntent = new Intent(getApplication(), CostSettingActivity.class);
                    startActivity(mvSetIntent);
                }
            });

            // Add the view to the Snackbar's layout
            layout.addView(snackView, objLayoutParams);
            // Show the Snackbar
            snackbar.show();
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.gestureDetector.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    View.OnClickListener AddIngradient = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Cost firIngradient = new Cost();
            String menu = spinnerMenu.getSelectedItem().toString();
            firIngradient.setCost_name("");
            firIngradient.setCost_price("");
            costlist.add(firIngradient);
            dBforAnalysis.addCost(menu);
            IngradientAdapter.notifyDataSetChanged();


        }
    };
}

