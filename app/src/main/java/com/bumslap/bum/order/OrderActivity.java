package com.bumslap.bum.order;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import android.widget.Toast;


import com.bumslap.bum.DB.DBHelper;
import com.bumslap.bum.DB.DBProvider;
import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.DB.MenuListAdapter;
import com.bumslap.bum.DB.Order;
import com.bumslap.bum.POSproject.MainActivity;
import com.bumslap.bum.R;
import com.bumslap.bum.menuedit.MenuSettingActivity;

import com.bumslap.bum.menuedit.MenuUpdateActivity;
import com.bumslap.bum.settings.UserSettingActivity;
import com.bumslap.bum.statistics.BarChartActivity;
import com.bumslap.bum.statistics.SalesStatus2Activity;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class OrderActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    GridView gridView;
    ArrayList<com.bumslap.bum.DB.Menu> Menulist;
    com.bumslap.bum.DB.MenuListAdapter menuListAdapter = null;

    RecyclerView billRecyclerView;
    RecyclerView.Adapter Adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<RealtimeOrder> Billordermenu;
    OrderMenuSelectAdapter orderMenuSelectAdapter;
    DBProvider db;

    String str_device;
    public static DBHelper dbforAnalysis;

    ArrayList<HashMap<String, Integer>> OrderList;
    HashMap<String, Integer> Ordermap;

    ArrayList<Order> Order_menu_List;
    long CurrentTimeCall;
    Date CurrentDateCall;
    SimpleDateFormat CurrentDate;
    SimpleDateFormat CurrentTimeS;
    String CurrentTime;
    int Order_Amount;

    ArrayList<OrderWrapDataSet> orderwraplist;
    OrderWrapAdapter orderWrapAdapter;
    OrderWrapDataSet orderWrapDataSet;

    DBforAnalysis newdbforAnalysis;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 화면을 landscape(가로) 화면으로 고정하고 싶은 경우
        setContentView(R.layout.activity_order);
        // setContentView()가 호출되기 전에 setRequestedOrientation()이 호출되어야 함
        //setTitle("오늘도 달려 보세");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        gridView = (GridView) findViewById(R.id.gridview);

        Menulist = new ArrayList<>();

        menuListAdapter = new MenuListAdapter(this, R.layout.order_menu_item, Menulist);
        gridView.setAdapter(menuListAdapter);
        db = new DBProvider(this);
        db.open();
        dbforAnalysis = new DBHelper(this);

        newdbforAnalysis = new DBforAnalysis(this, "POS.db", null,1);
        try {
            Cursor cursor = db.getData("SELECT * FROM MENU_TABLE");
            Menulist.clear();
            while (cursor.moveToNext()){
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String price = cursor.getString(2);
                String cost = cursor.getString(3);
                byte[] image = cursor.getBlob(4);

                Menulist.add(new com.bumslap.bum.DB.Menu(id, name, image, price, cost));
            }
        }catch (NullPointerException e){
        }
        menuListAdapter.notifyDataSetChanged();

        Billordermenu = new ArrayList<>();


        //binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        //binding.recyclerView.scrollToPosition(itemClass.size() - 1);


        billRecyclerView = (RecyclerView) findViewById(R.id.order_recycler);
        /*
        layoutManager = new LinearLayoutManager(getApplicationContext()); //, LinearLayoutManager.HORIZONTAL, false
        Adapter = new OrderMenuSelectAdapter(Order_menu_List, getApplicationContext());
        billRecyclerView.setLayoutManager(layoutManager);
        billRecyclerView.setAdapter(Adapter);
        */
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false); //, LinearLayoutManager.HORIZONTAL, false
        orderWrapAdapter = new OrderWrapAdapter(orderwraplist, getApplicationContext());
        billRecyclerView.setLayoutManager(layoutManager);
        billRecyclerView.setAdapter(orderWrapAdapter);

        OrderList = new ArrayList<HashMap<String, Integer>>();
        Ordermap = null;
        Ordermap = new HashMap<String, Integer>();
        Order_menu_List = new ArrayList<Order>();

        orderWrapDataSet = new OrderWrapDataSet();
        OrderWrapDataSet orderWrapDataSet1 = new OrderWrapDataSet();
        orderWrapDataSet1.setBillTitleNumber("asdas");
        orderwraplist = new ArrayList<OrderWrapDataSet>();



        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false); //, LinearLayoutManager.HORIZONTAL, false
        orderWrapAdapter = new OrderWrapAdapter(orderwraplist, getApplicationContext());
        billRecyclerView.setLayoutManager(layoutManager);
        billRecyclerView.setAdapter(orderWrapAdapter);

        /*

        billRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_DOWN){
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    //Toast.makeText(getApplicationContext(), position , Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

*/
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){

                String MenuID = Menulist.get(position).getMenu_id();

                String Price = Menulist.get(position).getMenu_price().toString();
                int Amount;

                //billRecyclerView.setLayoutManager(layoutManager);
                //billRecyclerView.setItemAnimator(new DefaultItemAnimator());

                // billRecyclerView.setAdapter(Adapter);
                //billRecyclerView.smoothScrollBy(200, 100);
                Toast.makeText(getApplicationContext(),""+position+"  "+MenuID+" "+Price,Toast.LENGTH_LONG).show();
                if(Ordermap.get(MenuID)==null){
                    Ordermap.put(MenuID, 0);
                }
                Amount = Ordermap.get(MenuID);
                Ordermap.put(MenuID, ++Amount);
                OrderList.add(Ordermap);
                CurrentTimeCall = System.currentTimeMillis();
                CurrentDateCall = new Date(CurrentTimeCall);
                CurrentDate = new SimpleDateFormat("yyyy-MM-dd");
                CurrentTimeS = new SimpleDateFormat("hh-mm-ss");
                CurrentTime = CurrentDate.format(CurrentDateCall);
                Order_Amount = Ordermap.get(MenuID);


                // orderMenuSelectAdapter.add(new Order(String.valueOf(Amount),CurrentTime,CurrentTime, MenuID,"no"));
                //Adapter = new OrderMenuSelectAdapter();
                Order_menu_List.add(new Order(String.valueOf(Amount),CurrentDate.toString(),CurrentTimeS.toString(), MenuID,"no"));
                orderWrapDataSet.setBillAllData(Order_menu_List);

                orderwraplist.add(orderWrapDataSet);

                layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false); //, LinearLayoutManager.HORIZONTAL, false
                orderWrapAdapter = new OrderWrapAdapter(orderwraplist, getApplicationContext());
                billRecyclerView.setLayoutManager(layoutManager);
                billRecyclerView.setAdapter(orderWrapAdapter);

/*
                layoutManager = new LinearLayoutManager(getApplicationContext()); //, LinearLayoutManager.HORIZONTAL, false
                Adapter = new OrderMenuSelectAdapter(Order_menu_List, getApplicationContext());
                billRecyclerView.setLayoutManager(layoutManager);
                billRecyclerView.setAdapter(Adapter);
*/

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onPause(){
        super.onPause();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_start) {
            intent = new Intent(getApplicationContext(), OrderActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_prepare) {
            intent = new Intent(getApplicationContext(), MenuSettingActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_analysis) {
            intent = new Intent(getApplicationContext(), BarChartActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_usersetting) {
            intent = new Intent(getApplicationContext(), UserSettingActivity.class);
            startActivity(intent);
        } else if(id == R.id.nav_finish){
            intent = new Intent(getApplicationContext(), SalesStatus2Activity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
