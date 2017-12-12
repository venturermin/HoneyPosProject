package com.bumslap.bum.menuedit;

import android.content.Intent;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.R;
import com.bumslap.bum.statistics.BarChartActivity;
import com.bumslap.bum.statistics.PieChartDataActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostSettingActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    Button MenuSetBtn, CostSetBtn;
    Intent mvSetIntent;
    private GestureDetector gestureDetector;
    ListView LIstViewIngradient;
    FloatingActionButton floatingActionButton_cost;
    ArrayList<HashMap<String, String>> list;
    HashMap<String, String> firIngradient;
    DBforAnalysis dBforAnalysis;
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

        Spinner spinnerMenu = (Spinner)findViewById(R.id.spinnerMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                Menu);
        spinnerMenu.setAdapter(adapter);
        spinnerMenu.setSelection(0);

        //listview_ingradient
        LIstViewIngradient = (ListView) findViewById(R.id.listView_Ingradient);

        list = new ArrayList<HashMap<String, String>>();
        dBforAnalysis = new DBforAnalysis(this, "test.db", null,1);
        ArrayList costlist = new ArrayList();
        //costlist = dBforAnalysis.getAllCostData();
        Cost cost = new Cost();

        for(int i = 0; i <costlist.size(); i++) {
            cost = (Cost) costlist.get(i);
            firIngradient = new HashMap<String, String>();
            firIngradient.put("IngradientName", cost.getCost_name());
            firIngradient.put("IngradientPrice", cost.getCost_price());
            list.add(i,firIngradient);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        IngradientAdapter IngradientAdapter = new IngradientAdapter(this, list);
        LIstViewIngradient.setAdapter(IngradientAdapter);
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
            firIngradient = new HashMap<String, String>();
            firIngradient.put("IngradientName", "");
            firIngradient.put("IngradientPrice", "");
            list.add(firIngradient);
            onResume();
        }
    };
}

