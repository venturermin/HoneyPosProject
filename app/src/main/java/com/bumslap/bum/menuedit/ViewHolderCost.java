package com.bumslap.bum.menuedit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.POSproject.SignFuntion.FontFuntion;
import com.bumslap.bum.R;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

/**
 * Created by jaein on 12/13/17.
 */

public class ViewHolderCost extends RecyclerView.ViewHolder {
    TextView Ingradient_name, Ingradient_price;
    CheckBox checkbox;
    public ViewHolderCost(final View itemView) {
        super(itemView);
        Ingradient_name = (TextView)itemView.findViewById(R.id.Ingradient_name);
        Ingradient_price = (TextView)itemView.findViewById(R.id.Ingradient_price);
        checkbox = (CheckBox)itemView.findViewById(R.id.checkBox);
        /*
        Ingradient_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ad = new AlertDialog.Builder(CostSettingActivity.context);

                Context context = CostSettingActivity.context;
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                ad.setTitle("원가 설정");

                final EditText titleBox = new EditText(context);
                titleBox.setHint("재료 명");
                layout.addView(titleBox);

                final EditText descriptionBox = new EditText(context);
                descriptionBox.setHint("재료 가격");
                layout.addView(descriptionBox);

                final Button updateBtn = new Button(context);
                updateBtn.setText("UPDATE");
                layout.addView(updateBtn);

                ad.setView(layout);
                ad.show();
            }
        });*/
    }


}
