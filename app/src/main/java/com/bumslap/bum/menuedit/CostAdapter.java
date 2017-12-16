package com.bumslap.bum.menuedit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.R;

import java.util.ArrayList;

/**
 * Created by jaein on 12/13/17.
 */

public class CostAdapter extends RecyclerView.Adapter<ViewHolderCost> {
    private ArrayList<Cost> arrayList;
    private Context context;

    public CostAdapter(ArrayList<Cost> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context =context;
    }

    @Override
    public ViewHolderCost onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_cost, parent, false);

        ViewHolderCost viewHolderCost = new ViewHolderCost(view);
        return viewHolderCost;
    }

    @Override
    public void onBindViewHolder(ViewHolderCost holder, int position) {
        String IngradientName = arrayList.get(position).getCost_name();
        String IngradientPrice = arrayList.get(position).getCost_price();
        holder.Ingradient_name.setText(IngradientName);
        holder.Ingradient_price.setText(IngradientPrice);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    /*
    public void removeItem(int position){
        this.arrayList.remove(position);
        notifyItemRemoved(position);
        //notifyDataSetChanged();
    }*/
}
