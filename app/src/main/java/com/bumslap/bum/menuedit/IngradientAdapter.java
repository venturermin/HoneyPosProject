package com.bumslap.bum.menuedit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.DB.DBforAnalysis;
import com.bumslap.bum.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jaein on 12/11/17.
 */

public class IngradientAdapter extends BaseAdapter{

    ArrayList<Cost> list;
    Activity activity;
    EditText IngradientName;
    EditText IngradientPrice;
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

    private class ViewHolder{

        EditText IngradientName, IngradientPrice;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();
        ViewHolder holder = new ViewHolder();
        if(view == null){
            view = inflater.inflate(R.layout.listview_cost, null);
            holder.IngradientName = (EditText) view.findViewById(R.id.EditText_Ingradientname);
            holder.IngradientPrice = (EditText) view.findViewById(R.id.EditText_Ingradientprice);
            view.setTag(holder);

            } else{
            holder = (ViewHolder) view.getTag();
        }
        Cost cost = list.get(i);
        holder.IngradientName.setText(list.get(i).getCost_name());
        holder.IngradientPrice.setText(list.get(i).getCost_price());
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
