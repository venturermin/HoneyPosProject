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


    ViewPager pager;
    PagerAdapter adapter;
    String str_device;
    public static DBHelper dbforAnalysis;

    ArrayList<HashMap<String, Integer>> OrderList;
    HashMap<String, Integer> Ordermap;

    ArrayList<Order> Order_menu_List;
    long CurrentTimeCall;
    Date CurrentDateCall;
    SimpleDateFormat CurrentDate;
    String CureentTime;
    int Order_Amount;
    OrderListAdapter orderListAdapter;

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
        init();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        gridView = (GridView) findViewById(R.id.gridview);

        Menulist = new ArrayList<>();

        menuListAdapter = new MenuListAdapter(this, R.layout.order_menu_item, Menulist);
        gridView.setAdapter(menuListAdapter);
        dbforAnalysis = new DBHelper(getApplicationContext(), "menu2.db", null, 1);

        newdbforAnalysis = new DBforAnalysis(this, "POS.db", null,1);

        Cursor cursor = dbforAnalysis.getData("SELECT * FROM MENU_TABLE");
        Menulist.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String cost = cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            Menulist.add(new com.bumslap.bum.DB.Menu(id, name, image, price, cost));
        }
        menuListAdapter.notifyDataSetChanged();
        Billordermenu = new ArrayList<>();


        //binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        //binding.recyclerView.scrollToPosition(itemClass.size() - 1);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //billRecyclerView = (RecyclerView) findViewById(R.id.list_order);
        //billRecyclerView.setLayoutManager(layoutManager);

        //pager = (ViewPager) findViewById(R.id.order_pager);



/*
        pager.setOnTouchListener(new ViewPager.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pager.SCROLL_INDICATOR_RIGHT.
                return false;
            }
        });
*/
        ClickableViewPager viewPager = (ClickableViewPager)findViewById(R.id.order_pager);
        pager.setAdapter(adapter);
        viewPager.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int i = position;
            }
        });
      /*
      pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            int i = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
*/
        OrderList = new ArrayList<HashMap<String, Integer>>();
        Ordermap = null;
        Ordermap = new HashMap<String, Integer>();

        //pager.SimpleOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

        //});
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){

                String Menu = Menulist.get(position).getMenu_name().toString();
                String Price = Menulist.get(position).getMenu_price().toString();
                Billordermenu.add(new RealtimeOrder(Menu));
                int Amount;

                //billRecyclerView.setLayoutManager(layoutManager);
                //billRecyclerView.setItemAnimator(new DefaultItemAnimator());
                Adapter = new BillAdapter(Billordermenu);
                // billRecyclerView.setAdapter(Adapter);
                //billRecyclerView.smoothScrollBy(200, 100);
                Toast.makeText(getApplicationContext(),""+position+"  "+Menu+" "+Price,Toast.LENGTH_LONG).show();
                if(Ordermap.get(Menu)==null){
                    Ordermap.put(Menu, 0);
                }
                Amount = Ordermap.get(Menu);
                Ordermap.put(Menu, ++Amount);
                OrderList.add(Ordermap);
                CurrentTimeCall = System.currentTimeMillis();
                CurrentDateCall = new Date(CurrentTimeCall);
                CurrentDate = new SimpleDateFormat("yyyy-MM-dd");
                CureentTime = CurrentDate.format(CurrentDateCall);
                Order_Amount = Ordermap.get(Menu);

                Order_menu_List = new ArrayList<>();
                orderListAdapter = new OrderListAdapter(Order_menu_List, getApplicationContext());

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public int differentDensityAndScreenSize(Context context) {
        int value = 20;

        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:

                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:

                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_HIGH:

                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:

                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:

                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:

                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_TV:

                    value = 20;
                    break;
                default:

                    value = 20;
                    break;
            }

        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:

                    str_device = "normal-ldpi";
                    value = 82;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:

                    value = 82;
                    str_device = "normal-mdpi";
                    break;
                case DisplayMetrics.DENSITY_HIGH:

                    str_device = "normal-hdpi";
                    value = 82;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:

                    str_device = "normal-xhdpi";
                    value = 90;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:

                    str_device = "normal-xxhdpi";
                    value = 96;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:

                    str_device = "normal-xxxhdpi";
                    value = 96;
                    break;
                case DisplayMetrics.DENSITY_TV:

                    str_device = "normal-tvmdpi";
                    value = 96;
                    break;
                default:

                    str_device = "normal-unknown";
                    value = 82;
                    break;
            }
        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:

                    value = 78;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:

                    value = 78;
                    break;
                case DisplayMetrics.DENSITY_HIGH:

                    value = 78;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:

                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:

                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:

                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_TV:

                    value = 125;
                    break;
                default:

                    value = 78;
                    break;
            }

        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:

                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:

                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_HIGH:

                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:

                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:

                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:

                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_TV:

                    value = 125;
                    break;
                default:

                    value = 125;
                    break;
            }
        }

        return value;
    }
    private List<Fragment> getFragments() {

        List<Fragment> fList = new ArrayList<Fragment>();

        for (int i = 0  ; i < 10; i++){
            fList.add(PageFragment.create(i));
        }

        return fList;

    }

    private void init() {
        pager = (ViewPager) findViewById(R.id.order_pager);
        differentDensityAndScreenSize(getApplicationContext());
        List<Fragment> fragments = getFragments();
        pager.setAdapter(adapter);
        pager.setClipToPadding(false);


        if (str_device.equals("normal-hdpi")){
            pager.setPadding(0, 0, 0, 0);
        }else if (str_device.equals("normal-mdpi")){
            pager.setPadding(0, 0, 0, 0);
        }else if (str_device.equals("normal-xhdpi")){
            pager.setPadding(0, 0, 0, 0);
        }else if (str_device.equals("normal-xxhdpi")){
            pager.setPadding(0, 0, 0, 0);
        }else if (str_device.equals("normal-xxxhdpi")){
            pager.setPadding(0, 0, 0, 0);
        }else if (str_device.equals("normal-unknown")){
            pager.setPadding(0, 0, 0, 0);
        }else {

        }

        adapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        pager.setPageTransformer(true, new ViewpagerTransformer());
        pager.setAdapter(adapter);
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
