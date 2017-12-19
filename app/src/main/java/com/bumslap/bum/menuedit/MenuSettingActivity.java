package com.bumslap.bum.menuedit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumslap.bum.BuildConfig;

import com.bumslap.bum.DB.DBProvider;
import com.bumslap.bum.R;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.HashMap;


public class MenuSettingActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    Button MenuSetBtn, CostSetBtn;
    RecyclerView.LayoutManager mLayoutManager;
    Intent mvSetIntent;
    private GestureDetector gestureDetector;
    android.app.AlertDialog.Builder alertDialogBuilder;
    RecyclerView.Adapter mMyadapter;
    RecyclerView mRecyclerView;
    private DBProvider db;
    private com.bumslap.bum.DB.DBHelper mDBHelper;
    ArrayList<com.bumslap.bum.DB.Menu> menulist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_setting);

        if(BuildConfig.DEBUG){
            Context context = getApplicationContext();
            Stetho.initializeWithDefaults(this);
        }

        this.gestureDetector = new GestureDetector(this,this);
        FloatingActionButton floatingActionButton =

                (FloatingActionButton)findViewById(R.id.addItemAction);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), MenuUpdateActivity.class);
                startActivity(intent);

            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        db = new DBProvider(this);
        db.open();
        menulist = new ArrayList<>();
        mMyadapter = new MyAdapter(db,R.layout.activity_menu_setting, menulist);
        mRecyclerView.setAdapter(mMyadapter);

        //already Opened database in MenuUpdateActivity
        //call the retrieve method
        retrieve();
        // DeleteData method move on from here to MyAdapter class.


    }

    //RETRIEVE = call
    private void retrieve()
    {

        Cursor cursor =  db.getData("SELECT * FROM MENU_TABLE");
        menulist.clear();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String cost = cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            menulist.add(new com.bumslap.bum.DB.Menu(id, name, image, price, cost));

        }

            mMyadapter.notifyDataSetChanged();

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
            View view = findViewById(R.id.menu_setting_activity);
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


    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
       gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child;
                child = recycleView.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && clicklistener!=null){
                    clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                }
            }
        });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
