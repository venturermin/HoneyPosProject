package com.bumslap.bum.menuedit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.Toast;

import com.bumslap.bum.R;

/**
 * Created by jaein on 12/14/17.
 */

public class ViewHolderCostUpdate extends RecyclerView.ViewHolder {
    EditText Ingradient_name, Ingradient_price;
    CheckBox checkBox;

    public ViewHolderCostUpdate(final View itemView) {
        super(itemView);
        Ingradient_name = (EditText) itemView.findViewById(R.id.editText);
        Ingradient_price = (EditText) itemView.findViewById(R.id.editText3);
        checkBox = (CheckBox)itemView.findViewById(R.id.checkBox);
        checkBox.setVisibility(View.INVISIBLE);
    }
}
