package com.bumslap.bum.menuedit;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.DB.Menu;
import com.bumslap.bum.R;

public class MenuUpdateActivity extends AppCompatActivity {
    Button UpdateBTN;
    EditText UpdateMenuName, UpdateMenuPrice, UpdateMenuCost;
    ImageView UpdateMenuImage;
    private DBforAnalysis dbforAnalysis;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_update);
        setTitle("메뉴 등록");

        UpdateBTN = (Button)findViewById(R.id.UpdateBtn);
        UpdateBTN.setOnClickListener(UpdateMenu);

        UpdateMenuImage = (ImageView)findViewById(R.id.UpdateMenuImage);
        UpdateMenuName = (EditText)findViewById(R.id.UpdateMenuName);
        UpdateMenuPrice = (EditText)findViewById(R.id.UpdateMenuPrice);
        UpdateMenuCost = (EditText)findViewById(R.id.UpdateMenuCost);
    }

    Button.OnClickListener UpdateMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dbforAnalysis = new DBforAnalysis(getApplicationContext(), "menu.db", null,1);
            String MenuName = UpdateMenuName.getText().toString();
            String MenuPrice = UpdateMenuPrice.getText().toString();
            String MenuCost = UpdateMenuCost.getText().toString();

            if(dbforAnalysis == null){
                dbforAnalysis = new DBforAnalysis(getApplicationContext(), "test.db", null, 1);
            }

            menu = new Menu();
            menu.setMenu_name(MenuName);
            menu.setMenu_price(MenuPrice);
            menu.setMenu_cost(MenuCost);

            dbforAnalysis.addMenu(menu);
        }
    };
}
