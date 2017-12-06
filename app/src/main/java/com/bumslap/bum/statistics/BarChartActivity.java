package com.bumslap.bum.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumslap.bum.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {
    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    XAxis xAxis;
    int[] color = {Color.rgb(117,224,233)};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        chart = (BarChart) findViewById(R.id.chart1);

        BARENTRY = new ArrayList<>();
        BarEntryLabels = new ArrayList<String>();
        AddValuesToBARENTRY();
        AddValuesToBarEntryLabels();
        Bardataset  = new BarDataSet(BARENTRY, "Sales");
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
    }

    public void AddValuesToBARENTRY(){
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

    public void AddValuesToBarEntryLabels(){
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
}
