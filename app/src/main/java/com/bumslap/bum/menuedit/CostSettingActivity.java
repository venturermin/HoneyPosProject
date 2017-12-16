package com.bumslap.bum.menuedit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.bumslap.bum.DB.DBHelper;
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
    Button IngradientUpdatBtn, IngradientAddBtn , IngradientDeleteBtn;
    View layout;
    ArrayList<String> Menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_setting);

        dBforAnalysis = new DBforAnalysis(this, "POS.db", null,1);
        //DBHelper dbHelper = new DBHelper(this, "postest.db", null,1);
        costAdapter = new CostAdapter(arrayList, this);


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

        TextView menuPrice = (TextView)findViewById(R.id.textView3);
        //spinner
        Menu = new ArrayList<>();
        Menu = dBforAnalysis.getAllMnuData();

        spinnerMenu = (Spinner)findViewById(R.id.spinnerMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                Menu);
        spinnerMenu.setAdapter(adapter);
        spinnerMenu.setSelection(1);

        //Get Menu price
        String menuname = spinnerMenu.getSelectedItem().toString();
        String price =  dBforAnalysis.getMenuprice(menuname);
        menuPrice.setText(price);

        costAdapter.notifyDataSetChanged();


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
        //RecyclerView
        arrayList = new ArrayList<Cost>();
        mdb = dBforAnalysis.getWritableDatabase();

        Cost cost = new Cost();
        arrayList = dBforAnalysis.getAllCostData();

        recyclerView = (RecyclerView)findViewById(R.id.RecyclerView);
        costAdapter = new CostAdapter(arrayList, this);
        int CostTotal = 0;
        for(int i=0; i<arrayList.size(); i++){
            if(isNumber(arrayList.get(i).getCost_price()) == true)
                CostTotal = CostTotal + Integer.parseInt(arrayList.get(i).getCost_price());
        }

        EditText editText = (EditText)findViewById(R.id.editText2);
        editText.setText(Integer.toString(CostTotal));

        costAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(costAdapter);

    }

    public static boolean isNumber(String str){
        boolean result = false;
        try{
            Double.parseDouble(str) ;
            result = true ;
        }catch(Exception e){}


        return result ;
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

        }
    };

    private void initiatePopupWindow() {
        try {
            //modal 창
            //  LayoutInflater 객체와 시킴
            Cost cost = new Cost();
            LayoutInflater inflater = (LayoutInflater) CostSettingActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.activity_cost_update, (ViewGroup)findViewById(R.id.view));
            //arrayList = new ArrayList<Cost>();


            arrayList = dBforAnalysis.getAllCostData();

            recyclerView2 = (RecyclerView)layout.findViewById(R.id.rv);
            IngradientUpdatBtn = (Button)layout.findViewById(R.id.IngradientUpdatBtn);
            IngradientAddBtn = (Button)layout.findViewById(R.id.IngradientAddBtn);
            IngradientDeleteBtn = (Button)layout.findViewById(R.id.IngradientDeleteBtn);

            IngradientUpdatBtn.setOnClickListener(clickBtn);
            IngradientAddBtn.setOnClickListener(clickBtn);
            IngradientDeleteBtn.setOnClickListener(clickBtn);

            costUpdateAdapter = new CostUpdateAdapter(arrayList, this);

            costUpdateAdapter.notifyDataSetChanged();
            recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView2.setAdapter(costUpdateAdapter);

            pwindo = new PopupWindow(layout, mWidthPixels, mHeightPixels, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener clickBtn = new View.OnClickListener() {
        Cost firIngradient;
        @Override
        public void onClick(View view) {
            //update

            switch (view.getId()){
                case R.id.IngradientUpdatBtn :
                    //DB update
                    arrayList = new ArrayList<Cost>();

                    Cost cost = new Cost();
                    View v;
                    EditText Ingradient_name;
                    EditText Ingradient_price;
                    recyclerView2 = (RecyclerView)layout.findViewById(R.id.rv);
                    arrayList = dBforAnalysis.getAllCostData();

                    Ingradient_name = (EditText) findViewById(R.id.editText);
                    Ingradient_price = (EditText) findViewById(R.id.editText3);
                    int lengthOfRec = recyclerView2.getChildCount();
                    for (int i=0;i< lengthOfRec; i++){
                        v = recyclerView2.getChildAt(i);
                        Ingradient_name = v.findViewById(R.id.editText);
                        Ingradient_price = v.findViewById(R.id.editText3);
                        String name = Ingradient_name.getText().toString();
                        String price = Ingradient_price.getText().toString();
                        firIngradient = new Cost();
                        firIngradient.setCost_id(arrayList.get(i).getCost_id());

                        firIngradient.setCost_name(name);
                        firIngradient.setCost_price(price);
                        dBforAnalysis.updateCost(firIngradient);
                    }
                    break;
                case R.id.IngradientAddBtn :
                    //DB add
                    firIngradient = new Cost();
                    String menu = spinnerMenu.getSelectedItem().toString();

                    firIngradient.setCost_name("재료 명");
                    firIngradient.setCost_price("재료 가격");
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
                    firIngradient.setCost_FK_menuId(menu_Id);
                    dBforAnalysis.addCost(firIngradient);
                    costAdapter = new CostAdapter(arrayList, CostSettingActivity.this);
                    costAdapter.notifyDataSetChanged();
                    break;
                case R.id.IngradientDeleteBtn :
                    //DB delete

                    recyclerView2 = (RecyclerView)layout.findViewById(R.id.rv);
                    arrayList = dBforAnalysis.getAllCostData();

                    CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
                    int lengthOfcheck = recyclerView2.getChildCount();
                    for (int i=0;i< lengthOfcheck; i++){
                        v = recyclerView2.getChildAt(i);
                        checkBox = v.findViewById(R.id.checkBox);
                        checkBox.setVisibility(View.VISIBLE);
                        boolean checked =  checkBox.isChecked();
                        if (checked == true){
                            int id = arrayList.get(i).getCost_id();
                            dBforAnalysis.deleteCost(id);
                        }
                    }
                    costAdapter.notifyDataSetChanged();
            }


        }
    };
}