package com.bumslap.bum.POSproject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumslap.bum.DB.CardItem;
import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.DB.Menu;
import com.bumslap.bum.POSproject.SignFuntion.FontFuntion;
import com.bumslap.bum.R;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    ImageButton BtnStart;
    ImageButton BtnPrepare;
    ImageButton BtnAnalysis;
    ImageButton BtnSetting;
    SQLiteDatabase mdb;
    DBforAnalysis dbHelper;
    Menu menu;
    Typeface mTypeface;
    LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView[] dots;
    static Context context;
    CardPagerAdapter mCardAdapter;

    public ViewPager viewPager;

    private List<String> numberList;

    //private ViewPagerIndicatorActivity viewPagerIndicatorActivity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderDotsPanel = (LinearLayout) findViewById(R.id.IndicatorDots);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.drawable.first_time_viewpager));
        mCardAdapter.addCardItem(new CardItem(R.drawable.mainimg));

        context = this;
        viewPager.setAdapter(mCardAdapter);




        dotsCount = mCardAdapter.getCount();
        dots = new ImageView[dotsCount];
        for(int i=0;i<dotsCount;i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.viewpager_nonselected_item));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            sliderDotsPanel.addView(dots[i],params);


        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.viewpager_selected_item));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i < dotsCount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.viewpager_nonselected_item));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.viewpager_selected_item));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        FontFuntion fontFuntion = new FontFuntion();
        mTypeface = Typeface.createFromAsset(getAssets(), "fonts/NanumSquareRoundL.ttf");
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        fontFuntion.setGlobalFont(root,mTypeface);


        BtnStart = (ImageButton)findViewById(R.id.button_Start);
        BtnStart.setOnClickListener(BtnClick);

        BtnPrepare = (ImageButton)findViewById(R.id.button_Prepare);
        BtnPrepare.setOnClickListener(BtnClick);

        BtnAnalysis = (ImageButton)findViewById(R.id.button_Analysis);
        BtnAnalysis.setOnClickListener(BtnClick);

        BtnSetting = (ImageButton)findViewById(R.id.button_Setting);
        BtnSetting.setOnClickListener(BtnClick);

        dbHelper = new DBforAnalysis(this, "test.db", null,1);
        mdb = dbHelper.getWritableDatabase();


    }



    ImageButton.OnClickListener BtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button_Start:
                    Intent intent = new Intent(getApplicationContext(), com.bumslap.bum.order.OrderActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_Prepare:
                    intent = new Intent(getApplicationContext(), com.bumslap.bum.menuedit.MenuSettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_Analysis:
                    intent = new Intent(getApplicationContext(), com.bumslap.bum.statistics.PieChartDataActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_Setting:
                    intent = new Intent(getApplicationContext(), com.bumslap.bum.settings.UserSettingActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };


    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }






}

