package com.bumslap.bum.menuedit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.DB.Menu;
import com.bumslap.bum.R;


/**
 * DB 안의 내용을 출력해보기 위한 activity 페이지.
 */
public class TestDBselectActivity extends AppCompatActivity {
    private TextView menunameview, menupriceview, menucostview;
    private ImageView menuimgaeview;

    private DBforAnalysis dBforAnalysis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dbselect);

        menunameview = (TextView) findViewById(R.id.menunameview);
        menupriceview = (TextView) findViewById(R.id.menupriceview);
        menucostview = (TextView) findViewById(R.id.menucostview);

        menuimgaeview = (ImageView)findViewById(R.id.menuimageview);

        dBforAnalysis = new DBforAnalysis(getApplicationContext(), "menu.db", null, 1);

        int menu_id = getIntent().getIntExtra("Menu_id", 1);
        if(dBforAnalysis == null){
            dBforAnalysis = new DBforAnalysis(getApplicationContext(), "test.db", null, 1);
        }

        Menu menu = dBforAnalysis.getMenuById(menu_id);

        menunameview.setText(menu.getMenu_name());
        menupriceview.setText(menu.getMenu_price());
        menucostview.setText(menu.getMenu_cost());

        //menuimgaeview.setImageURI(menu.getMenu_image());
    }
}
