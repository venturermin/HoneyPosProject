package com.bumslap.bum.menuedit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumslap.bum.BuildConfig;
import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.R;

import java.util.ArrayList;
/**
 * Created by jaein on 12/11/17.
 */

public class IngradientAdapter extends BaseAdapter{
    ArrayList<Cost> list;
    Activity activity;
    EditText IngradientName, IngradientPrice;
    ViewHolder holder;
    public IngradientAdapter(Activity activity, ArrayList<Cost> list){
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{
        EditText HIngradientName, HIngradientPrice;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();
        holder = new ViewHolder();
        if(view == null){
            view = inflater.inflate(R.layout.listview_cost, null);
            holder.HIngradientName = (EditText) view.findViewById(R.id.EditText_Ingradientname);
            holder.HIngradientPrice = (EditText) view.findViewById(R.id.EditText_Ingradientprice);
            view.setTag(holder);
        } else{
            holder = (ViewHolder) view.getTag();
        }
        Cost cost = list.get(i);
        holder.HIngradientName.setText(list.get(i).getCost_name());
        holder.HIngradientPrice.setText(list.get(i).getCost_price());


        return view;


    }

    //리스트 삭제
   /*
   View.OnClickListener click = new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Integer index = (Integer) view.getTag();
           list.remove(index.intValue());
           notifyDataSetChanged();
       }
   };*/

}