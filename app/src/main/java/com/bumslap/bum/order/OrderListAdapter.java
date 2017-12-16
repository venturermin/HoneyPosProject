package com.bumslap.bum.order;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumslap.bum.DB.Menu;
import com.bumslap.bum.DB.MenuListAdapter;
import com.bumslap.bum.DB.Order;
import com.bumslap.bum.R;

import java.util.ArrayList;

/**
 * Created by oyoun on 17. 12. 16.
 */

public class OrderListAdapter extends BaseAdapter {

    private ArrayList<Order> OrderMenulist;
    private Context context;
    private int layout;

    public OrderListAdapter( ArrayList<Order> OrderMenulist, Context context){
        this.layout = layout;
        this.OrderMenulist = OrderMenulist;
        this.context = context;
    }

    @Override
    public int getCount(){
        return this.OrderMenulist.size();
    }

    @Override
    public Object getItem(int position){
        return OrderMenulist.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    private class ViewHolder{

        TextView OrderMenuName, OrderMenuamount;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        OrderListAdapter.ViewHolder holder = new OrderListAdapter.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.OrderMenuName = (TextView)row.findViewById(R.id.billsList);
            holder.OrderMenuamount = (TextView)row.findViewById(R.id.billamout);

            row.setTag(holder);
        }
        else{
            holder = (OrderListAdapter.ViewHolder) row.getTag();
        }

        Order order = OrderMenulist.get(position);

        holder.OrderMenuName.setText(order.getOrder_FK_menuId());
        holder.OrderMenuamount.setText(order.getOrder_amount());


        return row;
    }
}
