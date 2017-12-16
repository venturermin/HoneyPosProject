package com.bumslap.bum.menuedit;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.POSproject.SignFuntion.FontFuntion;
import com.bumslap.bum.R;

import java.util.ArrayList;
/**
 * Created by jaein on 12/14/17.
 */

public class CostUpdateAdapter extends RecyclerView.Adapter<ViewHolderCostUpdate>{
    private ArrayList<Cost> arrayList;
    private Context context;
    View view;
    Typeface mTypeface;
    ViewHolderCostUpdate viewHolderCostUpdate;
    public CostUpdateAdapter(ArrayList<Cost> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context =context;
    }

    @Override
    public ViewHolderCostUpdate onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerviewupdate_cost, parent, false);
        viewHolderCostUpdate = new ViewHolderCostUpdate(view);
        return viewHolderCostUpdate;
    }

    @Override
    public void onBindViewHolder(ViewHolderCostUpdate holder, int position) {
        String IngradientName = arrayList.get(position).getCost_name();
        String IngradientPrice = arrayList.get(position).getCost_price();
        holder.Ingradient_name.setText(IngradientName);
        holder.Ingradient_price.setText(IngradientPrice);

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }
}