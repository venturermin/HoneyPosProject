package com.bumslap.bum.menuedit;

import android.content.Context;
import android.content.Intent;
import android.database.CursorJoiner;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.POSproject.SignFuntion.FontFuntion;
import com.bumslap.bum.R;

import java.util.ArrayList;

public class CostSettingActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    Button MenuSetBtn, CostSetBtn;
    Intent mvSetIntent;
    private GestureDetector gestureDetector;
    FloatingActionButton floatingActionButton_cost;
    DBforAnalysis dBforAnalysis;
    Spinner spinnerMenu;
    RecyclerView recyclerView, recyclerView2;
    CostAdapter costAdapter;
    ArrayList<Cost> arrayList;
    SQLiteDatabase mdb;
    Typeface mTypeface;
    static Context context;
    private PopupWindow pwindo;
    private int mWidthPixels, mHeightPixels;
    CostUpdateAdapter costUpdateAdapter;
    Button IngradientUpdatBtn;
    View layout;
    CostUpdateAdapterGet costUpdateAdapterget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_setting);

        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);

        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;

        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
        // 상태바와 메뉴바의 크기를 포함
        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception ignored) { }
        this.gestureDetector = new GestureDetector(this,this);
        context = this;
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


        //RecyclerView
        arrayList = new ArrayList<Cost>();

        dBforAnalysis = new DBforAnalysis(this, "postest.db", null,1);
        mdb = dBforAnalysis.getWritableDatabase();

        Cost cost = new Cost();
        arrayList = dBforAnalysis.getAllCostData();

        recyclerView = (RecyclerView)findViewById(R.id.RecyclerView);
        costAdapter = new CostAdapter(arrayList, this);

        costAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(costAdapter);

        Button button = (Button)findViewById(R.id.button_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiatePopupWindow();
            }
        });

    }
    public void onResume() {
        super.onResume();
        FontFuntion fontFuntion = new FontFuntion();
        mTypeface = Typeface.createFromAsset(getAssets(), "fonts/NanumSquareRoundL.ttf");
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        fontFuntion.setGlobalFont(root,mTypeface);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        super.dispatchTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) { return false; }

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
            //DB추가
            Cost firIngradient = new Cost();
            String menu = spinnerMenu.getSelectedItem().toString();

            firIngradient.setCost_name("재");
            firIngradient.setCost_price("가");
            Integer menu_Id;

            if(menu == "피자"){
                menu_Id = 1;
            }
            else if (menu == "짜장면") {
                menu_Id = 2;
            }
            else if (menu == "라면"){
                menu_Id = 3;
            }else {
                menu_Id = 4;
            }

            firIngradient.setCost_FK_menuId(menu_Id);;
            dBforAnalysis.addCost(firIngradient);
            costAdapter = new CostAdapter(arrayList, CostSettingActivity.this);
            costAdapter.notifyDataSetChanged();
            /*
            arrayList = dBforAnalysis.getAllCostData();
            costAdapter = new CostAdapter(arrayList, CostSettingActivity.this);
            recyclerView.setAdapter(costAdapter);*/
        }
    };

    private void initiatePopupWindow() {
        try {
            //modal 창
            //  LayoutInflater 객체와 시킴
            LayoutInflater inflater = (LayoutInflater) CostSettingActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            layout = inflater.inflate(R.layout.activity_cost_update, (ViewGroup)findViewById(R.id.view));

            arrayList = new ArrayList<Cost>();

            Cost cost = new Cost();
            arrayList = dBforAnalysis.getAllCostData();

            recyclerView2 = (RecyclerView)layout.findViewById(R.id.rv);
            IngradientUpdatBtn = (Button)layout.findViewById(R.id.IngradientUpdatBtn);

            IngradientUpdatBtn.setOnClickListener(clickUpdateBtn);

            costUpdateAdapter = new CostUpdateAdapter(arrayList, this);

            costUpdateAdapter.notifyDataSetChanged();
            recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView2.setAdapter(costUpdateAdapter);

            pwindo = new PopupWindow(layout, mWidthPixels-100, mHeightPixels-500, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener  clickUpdateBtn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //update

            arrayList = new ArrayList<Cost>();

            //arrayList = dBforAnalysis.getAllCostData();

            Cost cost = new Cost();

            recyclerView2 = (RecyclerView)layout.findViewById(R.id.rv);

            costUpdateAdapterget = new CostUpdateAdapterGet(arrayList, context);

            ViewHolderCostUpdateGet viewHolderCostUpdateget = new ViewHolderCostUpdateGet(layout);


            String n = viewHolderCostUpdateget.Ingradient_name.getText().toString();
            String v = viewHolderCostUpdateget.Ingradient_price.getText().toString();

            recyclerView2.setAdapter(costUpdateAdapterget);
            costUpdateAdapterget.notifyDataSetChanged();

        }
    };
}