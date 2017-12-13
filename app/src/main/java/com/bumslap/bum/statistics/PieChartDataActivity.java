package com.bumslap.bum.statistics;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bumslap.bum.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class PieChartDataActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    static final String[] LIST_MENU = {"Steak x 8", "Juice x 1", "Cola x 2"};
    Button AmountStaBtn, SalesStaBtn;
    PieChart mChart;
    private int[] yValues = {8,1,1};
    private String[] xValues = {"Steak","Juice","Cola"};


    private GestureDetector gestureDetector;
    Intent mvStaIntent;
    Button AmountStastisticBtn, SalesStatisticBtn;

    // colors for different sections in pieChart
    public static final int[] MY_COLORS = {
             Color.rgb(240,133,44),Color.rgb(27,204,133),Color.rgb(245,231,190)};

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_salesstatus_goh);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1 ,LIST_MENU) ;

        ListView listview = (ListView) findViewById(R.id.graph_listview) ;
        listview.setAdapter(adapter);

// creating data values

        mChart = (PieChart) findViewById(R.id.piechart);

        //   mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setHoleRadius(50);

        mChart.setRotationEnabled(false);
       // mChart.setCenterText("50%");
       // mChart.setCenterTextSize(20);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;

                Toast.makeText(PieChartDataActivity.this,
                        xValues[e.getXIndex()] + " is " + e.getVal() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        // setting sample Data for Pie Chart
        setDataForPieChart();

        this.gestureDetector = new GestureDetector(this,this);
    }

    public void setDataForPieChart() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yValues.length; i++)
            yVals1.add(new Entry(yValues[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xValues.length; i++)
            xVals.add(xValues[i]);

        // create pieDataSet
        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // adding colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        // Added My Own colors
        for (int c : MY_COLORS)
            colors.add(c);


        dataSet.setColors(colors);

        //  create pie data object and set xValues and yValues and set it to the pieChart
        PieData data = new PieData(xVals, dataSet);
        //   data.setValueFormatter(new DefaultValueFormatter());
        //   data.setValueFormatter(new PercentFormatter());

        data.setValueFormatter(new MyValueFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);

        // undo all highlights
       // mChart.highlightValues(null);

        // refresh/update pie chart
        mChart.invalidate();

        // animate piechart
        mChart.animateY( 1400);


        // Legends to show on bottom of the graph
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        float diffY = motionEvent1.getY() - motionEvent.getY();
        if (diffY < 0) {
            // Create the Snackbar
            LayoutInflater mInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = findViewById(R.id.pie_statistics_layout);
            ConstraintLayout.LayoutParams objLayoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
            // Get the Snackbar's layout view
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
            layout.setPadding(0,0,0,0);


            // Inflate our custom view
            View snackView = getLayoutInflater().inflate(R.layout.activity_snackbar_statistics2, null);
            // Configure the view
            AmountStastisticBtn = (Button) snackView.findViewById(R.id.AmountStastisticBtn);

            AmountStastisticBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mvStaIntent = new Intent(getApplication(), PieChartDataActivity.class);
                    startActivity(mvStaIntent);
                }
            });

            SalesStatisticBtn = (Button) snackView.findViewById(R.id.SalesStatisticBtn);
            SalesStatisticBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mvStaIntent = new Intent(getApplication(), BarChartActivity.class);
                    startActivity(mvStaIntent);
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

    public class MyValueFormatter implements ValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0"); // use one decimal if needed
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            // write your logic here
            return mFormat.format(value) + ""; // e.g. append a dollar-sign
        }

    }
}