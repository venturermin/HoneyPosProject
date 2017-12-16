package com.bumslap.bum.menuedit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumslap.bum.DB.Cost;
import com.bumslap.bum.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
/**
 * Created by jaein on 12/11/17.
 */

public class IngradientAdapter extends BaseAdapter{
    ArrayList<Cost> list;
    Activity activity;
    ViewHolder holder;
    CostSettingActivity costSettingActivity;
    Button button;
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
        //EditText HIngradientName, HIngradientPrice;
        TextView IngradientName;
        TextView IngradientPrice;
        EditText HIngradientPrice;
        EditText HIngradientName;
        Button button;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();
        holder = new ViewHolder();

        if(view == null){

            view = inflater.inflate(R.layout.listview_cost, null);
            holder.HIngradientName = (EditText) view.findViewById(R.id.EditText_Ingradientname);
            holder.button = (Button)view.findViewById(R.id.editBtn);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.HIngradientName.setFocusable(false);
                    holder.HIngradientName.setClickable(false);

                }
            });
            view.setTag(holder);
        }



        else{
            holder = (ViewHolder) view.getTag();
            holder.HIngradientName.setFocusable(true);
            holder.HIngradientName.setClickable(true);
        }

        return view;

    }


}