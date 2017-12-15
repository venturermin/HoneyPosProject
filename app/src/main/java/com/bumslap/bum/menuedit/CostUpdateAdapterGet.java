package com.bumslap.bum.menuedit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.R;

import java.util.ArrayList;

/**
 * Created by jaein on 12/15/17.
 */

public class CostUpdateAdapterGet extends RecyclerView.Adapter<ViewHolderCostUpdateGet> {
    private ArrayList<Cost> arrayList;
    private Context context;
    ViewHolderCostUpdateGet viewHolderCostUpdateget;
    View view;
    ArrayList<String> strings = new ArrayList<>();

    public void addItem(String s){
        strings.add(s);
    }

    public String getItem(int postion){
        return strings.get(postion);
    }

    public String getAllText(){
        StringBuilder sb = new StringBuilder();
        for(String s : strings){
            sb.append(s);
        }
        return sb.toString();
    }

    public CostUpdateAdapterGet(ArrayList<Cost> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context =context;
    }

    @Override
    public ViewHolderCostUpdateGet onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerviewupdate_cost, parent, false);
        viewHolderCostUpdateget = new ViewHolderCostUpdateGet(view);

        return viewHolderCostUpdateget;
    }

    @Override
    public void onBindViewHolder(ViewHolderCostUpdateGet holder, int position) {


        String s = holder.Ingradient_name.getText().toString();
        String v = holder.Ingradient_price.getText().toString();
        holder.Ingradient_name.setText(s);
        holder.Ingradient_price.setText(v);

        //arrayList.get(position).setCost_name(s);
        //arrayList.get(position).setCost_price(v);

        //arrayList.get(position).getCost_name();
        //arrayList.get(position).getCost_price();


    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }
}
