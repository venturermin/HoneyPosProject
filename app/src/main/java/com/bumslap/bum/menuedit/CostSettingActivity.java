package com.bumslap.bum.menuedit;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.MenuPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.DB.Menu;
import com.bumslap.bum.DB.DBforAnalysis;
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
    ArrayList<Cost> arrayList, costAllData;
    SQLiteDatabase mdb;
    Typeface mTypeface;
    static Context context;
    private PopupWindow pwindo;
    private int mWidthPixels, mHeightPixels;
    CostUpdateAdapter costUpdateAdapter;
    Button IngradientUpdatBtn, IngradientAddBtn , IngradientDeleteBtn, colseBtn;
    View layout;
    ArrayList<String> MenuName, MenuPrice, MenuId;
    ArrayList<Menu> MenuallData;
    TextView menuPrice;
    String price, name, menu = "";
    TextView sumCost, margin;
    String menu_name, menu_id;


    FloatingActionButton fab1, fab2, fab3, fab4;
    Animation fabOpen, fabClose, rotateForward, rotateBackward, costanim;
    boolean isOpen = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_setting);

        //snackbar
        this.gestureDetector = new GestureDetector(this,this);
        arrayList = new ArrayList<Cost>();  //이름수정
        costAllData = new ArrayList<Cost>();
        dBforAnalysis = new DBforAnalysis(this, "POS.db", null,1);
        //mdb = dBforAnalysis.getWritableDatabase();
        costAdapter = new CostAdapter(arrayList, this);
        context = this;

        menuPrice = (TextView)findViewById(R.id.textView3);
        sumCost = (TextView)findViewById(R.id.Sumcost);
        margin =(TextView)findViewById(R.id.textView6);
        //spinner
        MenuallData = dBforAnalysis.getMenuAllData();
        MenuName = new ArrayList<>();
        MenuPrice = new ArrayList<>();
        MenuId = new ArrayList<>();
        for(int i = 0 ; i <MenuallData.size(); i++) {
            MenuName.add(MenuallData.get(i).getMenu_name().toString());
            MenuPrice.add(MenuallData.get(i).getMenu_price());
            MenuId.add(MenuallData.get(i).getMenu_id());
            //Menu = dBforAnalysis.getMenuname();
        }

        spinnerMenu = (Spinner)findViewById(R.id.spinnerMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                MenuName);
        spinnerMenu.setAdapter(adapter);
        spinnerMenu.setSelection(0);

        spinnerMenu.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onResume();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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


        //costAllData = dBforAnalysis.getAllCostData();
        /*
        menu_name = spinnerMenu.getSelectedItem().toString();
        menu_id = dBforAnalysis.getMenuIdData(menu_name);
        costAllData = dBforAnalysis.getMenuMatchCostData(menu_id);*/

        Menu_id();
        costAllData = dBforAnalysis.getMenuMatchCostData(menu_id);
        //Get Menu price
        Integer position;
        String menuprice;
        position = spinnerMenu.getSelectedItemPosition();
        try {
            menuprice = MenuallData.get(position).getMenu_price();
        }
        catch (IndexOutOfBoundsException e){
            menuprice ="";
        }
            //price = dBforAnalysis.getMenuprice(menuname);
        try {
            menuPrice.setText(menuprice);
        }
        catch (IndexOutOfBoundsException e){
            menuPrice.setText("");
        }


        //modal dialog
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

        //RecyclerView
        //Cost cost = new Cost();
        recyclerView = (RecyclerView)findViewById(R.id.RecyclerView);
        costAdapter = new CostAdapter(costAllData, this);

        int CostTotal = 0;
        for(int i=0; i<costAllData.size(); i++){
            if(isNumber(costAllData.get(i).getCost_price()) == true)
                CostTotal = CostTotal + Integer.parseInt(costAllData.get(i).getCost_price());
        }

        sumCost.setText(Integer.toString(CostTotal)+" 원");
        int mar;
        try {
            mar = Integer.parseInt(menuprice) - CostTotal;
        }
        catch (NumberFormatException e){
            menuprice = "0";
            mar = Integer.parseInt(menuprice) - CostTotal;

        }
        margin.setText(Integer.toString(mar)+" 원");

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(costAdapter);


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

    private void initiatePopupWindow() {
        try {
            //modal 창
            Cost cost = new Cost();
            LayoutInflater inflater = (LayoutInflater) CostSettingActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.activity_cost_update, (ViewGroup)findViewById(R.id.view));
            pwindo = new PopupWindow(layout, mWidthPixels, mHeightPixels, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
            //recyclerview
            arrayList = dBforAnalysis.getAllCostData();
            recyclerView2 = (RecyclerView)layout.findViewById(R.id.rv);
            /*
            IngradientUpdatBtn = (Button)layout.findViewById(R.id.IngradientUpdatBtn);
            IngradientAddBtn = (Button)layout.findViewById(R.id.IngradientAddBtn);
            IngradientDeleteBtn = (Button)layout.findViewById(R.id.IngradientDeleteBtn);*/
            colseBtn = (Button)layout.findViewById(R.id.button2);

            costUpdateAdapter = new CostUpdateAdapter(costAllData, this);
            recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView2.setAdapter(costUpdateAdapter);

            fab1 = (FloatingActionButton)layout.findViewById(R.id.fab1);
            fab2 = (FloatingActionButton) layout.findViewById(R.id.fab2);
            fab3 = (FloatingActionButton) layout.findViewById(R.id.fab3);
            fab4 = (FloatingActionButton) layout.findViewById(R.id.fab4);


            fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
            fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

            rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
            rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

            fab1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    animateFab();
                }
            });
            fab2.setOnClickListener(new View.OnClickListener(){
                Cost firIngradient;
                @Override
                public void onClick(View view) {
                    animateFab();
                    //DB add
                    firIngradient = new Cost();
                    try {
                        menu = spinnerMenu.getSelectedItem().toString();
                    }
                    catch (NullPointerException e){
                        menu = "";
                    }

                    firIngradient.setCost_name("");
                    firIngradient.setCost_price("");

                    Menu_id();
                    firIngradient.setCost_FK_menuId(Integer.parseInt(menu_id));
                    dBforAnalysis.addCost(firIngradient);

                    costAllData = dBforAnalysis.getMenuMatchCostData(menu_id);
                    costUpdateAdapter = new CostUpdateAdapter(costAllData, CostSettingActivity.this);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(CostSettingActivity.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView2.setAdapter(costUpdateAdapter);
                }
            });
            fab3.setOnClickListener(new View.OnClickListener() {
                Cost firIngradient;
                @Override
                public void onClick(View view) {
                    animateFab();
                    Cost cost = new Cost();
                    View v;
                    EditText Ingradient_name;
                    EditText Ingradient_price;
                    recyclerView2 = (RecyclerView)layout.findViewById(R.id.rv);
                    //arrayList = dBforAnalysis.getAllCostData();
                    //Ingradient_name = (EditText) findViewById(R.id.editText);
                    //Ingradient_price = (EditText) findViewById(R.id.editText3);
                    int lengthOfRec = recyclerView2.getChildCount();
                    for (int i=0;i< lengthOfRec; i++){
                        v = recyclerView2.getChildAt(i);
                        Ingradient_name = v.findViewById(R.id.editText);
                        Ingradient_price = v.findViewById(R.id.editText3);
                        try {
                            name = Ingradient_name.getText().toString();
                            price = Ingradient_price.getText().toString();
                        }
                        catch (NullPointerException e){
                            name = "";
                            price = "";
                        }
                        firIngradient = new Cost();
                        firIngradient.setCost_id(costAllData.get(i).getCost_id());
                        firIngradient.setCost_name(name);
                        firIngradient.setCost_price(price);
                        dBforAnalysis.updateCost(firIngradient);
                    }
                }
            });

            fab4.setOnClickListener(new View.OnClickListener() {
                int flag = 0;
                View v;
                @Override
                public void onClick(View view) {
                    //DB delete

                    recyclerView2 = (RecyclerView)layout.findViewById(R.id.rv);
                    String id = Menu_id();

                    costAllData = dBforAnalysis.getMenuMatchCostData(menu_id);
                    CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
                    int lengthOfcheck = recyclerView2.getChildCount();

                    if(flag == 0) {

                        for (int i = 0; i < lengthOfcheck; i++) {
                            v = recyclerView2.getChildAt(i);
                            checkBox = v.findViewById(R.id.checkBox);
                            checkBox.setVisibility(View.VISIBLE);
                            if(i == lengthOfcheck-1){
                                flag++;
                            }
                        }
                    }
                    else {
                        for (int i = 0; i < lengthOfcheck; i++) {
                            v = recyclerView2.getChildAt(i);
                            checkBox = v.findViewById(R.id.checkBox);
                            checkBox.setVisibility(View.VISIBLE);
                            boolean checked = checkBox.isChecked();
                            if (checked == true) {
                                id = costAllData.get(i).getCost_id().toString();
                                dBforAnalysis.deleteCost(Integer.parseInt(id));
                            }
                        }
                        costAllData = dBforAnalysis.getMenuMatchCostData(menu_id);
                        costUpdateAdapter = new CostUpdateAdapter(costAllData, CostSettingActivity.this);
                        recyclerView2.setLayoutManager(new LinearLayoutManager(CostSettingActivity.this, LinearLayoutManager.VERTICAL, false));
                        recyclerView2.setAdapter(costUpdateAdapter);
                        flag = 0;

                    }
                }
            });


            colseBtn.setOnClickListener(closeclick);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener closeclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                    pwindo.dismiss();
                    onResume();
            }
    };

    public static boolean isNumber(String str){
        boolean result = false;
        try{
            Double.parseDouble(str) ;
            result = true ;
        }catch(Exception e){}
        return result ;
    }

    public String Menu_id(){
        try {
            menu_name = spinnerMenu.getSelectedItem().toString();
        }
        catch (NullPointerException e){
            menu_name="";
        }
        menu_id = dBforAnalysis.getMenuIdData(menu_name);
        return menu_id;
    }


    private void animateFab(){

        if(isOpen){
            fab1.startAnimation(rotateBackward);
            fab2.startAnimation(fabOpen);
            fab3.startAnimation(fabOpen);
            fab4.startAnimation(fabOpen);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);
            isOpen = false;
        }
        else{
            fab1.startAnimation(rotateForward);
            fab2.startAnimation(fabClose);
            fab3.startAnimation(fabClose);
            fab4.startAnimation(fabClose);
            fab2.setClickable(false);
            fab3.setClickable(false);
            fab4.setClickable(false);
            isOpen = true;
        }


    }

}