package com.bumslap.bum.menuedit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.R;

/**
 * Created by jaein on 12/15/17.
 */

public class ViewHolderCostUpdateGet extends RecyclerView.ViewHolder {
    EditText Ingradient_name, Ingradient_price;
    String IngradientName, IngradientPrice;

    public ViewHolderCostUpdateGet(View itemView) {
        super(itemView);
        Ingradient_name = (EditText) itemView.findViewById(R.id.editText);
        Ingradient_price = (EditText) itemView.findViewById(R.id.editText3);

    }
}
