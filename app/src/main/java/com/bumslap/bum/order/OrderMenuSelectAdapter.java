package com.bumslap.bum.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.DB.Order;
import com.bumslap.bum.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oyoun on 17. 12. 18.
 */

public class OrderMenuSelectAdapter extends RecyclerView.Adapter<OrderMenuViewHoler> {
    private ArrayList<Order> Menuitems = new ArrayList<>();
    private Context context;
    DBforAnalysis dBforAnalysis;
    String MenunameDB;
    int i, j, k = 0;

    public OrderMenuSelectAdapter(ArrayList<Order> orderArrayList, Context context) {
        try {
            k = orderArrayList.size();
            for (i = 0; i < orderArrayList.size(); i++) {
                for (j = 0; j <= i; j++) {
                    if (Integer.parseInt(orderArrayList.get(i).getOrder_FK_menuId()) == Integer.parseInt(orderArrayList.get(j).getOrder_FK_menuId())) {
                        orderArrayList.set(j, orderArrayList.get(i));

                        k++;
                        if (k == orderArrayList.size() * 2 + 1) {
                            orderArrayList.remove(orderArrayList.size() - 1);
                        }
                    }
                }
            }
        } catch (Exception ec) {

        }


        this.Menuitems = orderArrayList;
        this.context = context;
    }

    @Override
    public OrderMenuViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.order_bill_item, parent, false);


        return new OrderMenuViewHoler(v);
    }

    @Override
    public void onBindViewHolder(OrderMenuViewHoler holder, int position) {
        dBforAnalysis = new DBforAnalysis(context, "menu2.db", null, 6);
        Order menuitem = Menuitems.get(position);

        MenunameDB = dBforAnalysis.getMenuName(Integer.parseInt(menuitem.getOrder_FK_menuId()));

        holder.Menuname.setText(MenunameDB);

        holder.MenuAmount.setText(menuitem.getOrder_amount());

    }

    @Override
    public int getItemCount() {
        return (null != Menuitems ? Menuitems.size() : 0);
    }

}

class OrderMenuViewHoler extends RecyclerView.ViewHolder{

    public TextView Menuname, MenuAmount;

    public OrderMenuViewHoler(View OrderitemView){
        super(OrderitemView);
        Menuname = (TextView)OrderitemView.findViewById(R.id.ordermenuname);
        MenuAmount = (TextView)OrderitemView.findViewById(R.id.ordermenuamount);

    }

}