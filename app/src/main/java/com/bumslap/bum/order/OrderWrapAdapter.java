package com.bumslap.bum.order;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumslap.bum.R;

import java.util.ArrayList;

/**
 * Created by oyoun on 17. 12. 19.
 */

public class OrderWrapAdapter extends RecyclerView.Adapter<OrderWrapAdapterViewHolder>{

    private ArrayList<OrderWrapDataSet> orderarrayList;
    private Context orderwrapcontext;
    private int position = 0;

    public OrderWrapAdapter(ArrayList<OrderWrapDataSet> orderarrayList, Context context){
        this.orderarrayList = orderarrayList;
        this.orderwrapcontext = context;
    }

    @Override
    public OrderWrapAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_bills_layout, parent, false);
        final OrderWrapAdapterViewHolder viewHolder = new OrderWrapAdapterViewHolder(view);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = viewHolder.getAdapterPosition();
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderWrapAdapterViewHolder holder, final int position) {

        final String billtitlenumber = orderarrayList.get(position).getBillTitleNumber();
        ArrayList billAllData = orderarrayList.get(position).getBillAllData();

        holder.orderbilltitlenumber.setText(billtitlenumber);
        OrderMenuSelectAdapter orderMenuSelectAdapter = new OrderMenuSelectAdapter(billAllData, orderwrapcontext);

        holder.orderbilllistrecyclerView.setLayoutManager(new LinearLayoutManager(orderwrapcontext, LinearLayoutManager.VERTICAL, false));
        holder.orderbilllistrecyclerView.setAdapter(orderMenuSelectAdapter);


    }

    @Override
    public int getItemCount() {

        //다시 봐야 할 필요가 있는 부분.

        return (null != orderarrayList ? orderarrayList.size() : 0);
    }
}

class OrderWrapAdapterViewHolder extends RecyclerView.ViewHolder{

    public RecyclerView orderbilllistrecyclerView;
    public TextView orderbilltitlenumber;
    public CardView orderbillcardview;
    public OrderWrapAdapterViewHolder(View view){

        super(view);
        orderbillcardview = (CardView)view.findViewById(R.id.order_bill_cardview);
        orderbilltitlenumber = (TextView)view.findViewById(R.id.BillNumber);
        orderbilllistrecyclerView = (RecyclerView)view.findViewById(R.id.Bill_order_list);

    }
}
