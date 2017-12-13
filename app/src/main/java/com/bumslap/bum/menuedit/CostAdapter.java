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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cardview_cost, null);

        ViewHolderCost viewHolderCost = new ViewHolderCost(view);

        return viewHolderCost;
    }

    @Override
    public void onBindViewHolder(ViewHolderCost holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
