package com.bumslap.bum.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumslap.bum.R;
import java.util.ArrayList;

public class SnackbarStatisticsActivity extends AppCompatActivity {
    Button AmountStastisticBtn, SalesStatisticBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_statistics2);

        AmountStastisticBtn = (Button)findViewById(R.id.AmountStastisticBtn);
        SalesStatisticBtn = (Button)findViewById(R.id.SalesStatisticBtn);

        AmountStastisticBtn.setOnClickListener(StatisticsClick);
        SalesStatisticBtn.setOnClickListener(StatisticsClick);
    }

    View.OnClickListener StatisticsClick = new View.OnClickListener() {
        Intent mvStaIntent;
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.AmountStastisticBtn:
                    Toast.makeText(SnackbarStatisticsActivity.this,"test",Toast.LENGTH_LONG).show();
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
