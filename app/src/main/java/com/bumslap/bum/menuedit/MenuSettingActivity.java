package com.bumslap.bum.menuedit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumslap.bum.R;

import java.util.ArrayList;
import java.util.HashMap;


public class MenuSettingActivity extends AppCompatActivity {
    Button MenuSetBtn, CostSetBtn;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_setting);

        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> hashMap = null;
        hashMap = new HashMap<String, Object>();
        hashMap.put("title", "rollrice");
        hashMap.put("detail", "3,000원");
        hashMap.put("image", R.drawable.rollrice);
        arrayList.add(hashMap);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        //mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this,3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(arrayList);
        mRecyclerView.setAdapter(mAdapter);


        Button MenuSetBtn = (Button)findViewById(R.id.MenuSetBtn);
        Button CostSetBtn = (Button)findViewById(R.id.CostSetBtn);
        MenuSetBtn.setOnClickListener(MenuCostClick);
        CostSetBtn.setOnClickListener(MenuCostClick);

    }

    Button.OnClickListener MenuCostClick = new View.OnClickListener() {
        Intent mvSettingIntent;
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.MenuSetBtn:
                    mvSettingIntent = new Intent(getApplicationContext(), MenuSettingActivity.class);
                    startActivity(mvSettingIntent);
                    break;
                case R.id.CostSetBtn:
                    mvSettingIntent = new Intent(getApplicationContext(), CostSettingActivity.class);
                    startActivity(mvSettingIntent);
                    break;
            }

        }
    };

    ImageView.OnClickListener updatemenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), MenuUpdateActivity.class);
            startActivity(intent);
        }
    };
}
