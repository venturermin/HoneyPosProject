package com.bumslap.bum.statistics;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumslap.bum.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    BarChart chart;
    ArrayList<BarEntry> BARENTRY;
    ArrayList<String> BarEntryLabels;
    BarDataSet Bardataset;
    BarData BARDATA;
    XAxis xAxis;
    int[] color = {Color.rgb(117, 224, 233)};
    private GestureDetector gestureDetector;
    Button AmountStastisticBtn, SalesStatisticBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        chart = (BarChart) findViewById(R.id.chart1);

        BARENTRY = new ArrayList<>();
        BarEntryLabels = new ArrayList<String>();
        AddValuesToBARENTRY();
        AddValuesToBarEntryLabels();
        Bardataset = new BarDataSet(BARENTRY, "Sales");
        Bardataset.setColors(color);

        Bardataset.setBarSpacePercent(30f);

        BARDATA = new BarData(BarEntryLabels, Bardataset);
        chart.setData(BARDATA);

        chart.animateY(3000);
        // xAxis.setTextSize(23.0f);
        xAxis = chart.getXAxis();
        xAxis.setTextSize(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setLabelRotationAngle();
        this.gestureDetector = new GestureDetector(this,this);

    }

    public void AddValuesToBARENTRY() {
        BARENTRY.add(new BarEntry(2f, 0));
        BARENTRY.add(new BarEntry(4f, 1));
        BARENTRY.add(new BarEntry(6f, 2));
        BARENTRY.add(new BarEntry(8f, 3));
        BARENTRY.add(new BarEntry(7f, 4));
        BARENTRY.add(new BarEntry(3f, 5));
        BARENTRY.add(new BarEntry(2f, 6));
        BARENTRY.add(new BarEntry(4f, 7));
        BARENTRY.add(new BarEntry(6f, 8));
        BARENTRY.add(new BarEntry(8f, 9));
        BARENTRY.add(new BarEntry(7f, 10));
        BARENTRY.add(new BarEntry(3f, 11));
    }

    public void AddValuesToBarEntryLabels() {
        BarEntryLabels.add("2");
        BarEntryLabels.add("4");
        BarEntryLabels.add("6");
        BarEntryLabels.add("8");
        BarEntryLabels.add("10");
        BarEntryLabels.add("12");
        BarEntryLabels.add("14");
        BarEntryLabels.add("16");
        BarEntryLabels.add("18");
        BarEntryLabels.add("20");
        BarEntryLabels.add("22");
        BarEntryLabels.add("24");
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
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
            //ConstraintLayout.LayoutParams ConstraintLayoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Snackbar snackbar =  Snackbar.make( getWindow().getDecorView().getRootView(), "", Snackbar.LENGTH_LONG);
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

            View snackView = getLayoutInflater().inflate(R.layout.activity_snackbar_statistics2, null);

            AmountStastisticBtn = (Button)snackView.findViewById(R.id.AmountStastisticBtn);
            SalesStatisticBtn = (Button)snackView.findViewById(R.id.SalesStatisticBtn);

            AmountStastisticBtn.setOnClickListener(StatisticsClick);
            SalesStatisticBtn.setOnClickListener(StatisticsClick);

            View view = snackbar.getView();

            layout.addView(snackView);

            snackbar.setAction("", StatisticsClick).show();
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.gestureDetector.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    View.OnClickListener StatisticsClick = new View.OnClickListener() {
        Intent mvStaIntent;
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.AmountStastisticBtn:
                    mvStaIntent = new Intent(getApplication(), PieChartDataActivity.class);
                    startActivity(mvStaIntent);
                    break;
                case R.id.SalesStatisticBtn:
                    mvStaIntent = new Intent(getApplication(), BarChartActivity.class);
                    startActivity(mvStaIntent);
                    break;
            }
        }
    };

}

