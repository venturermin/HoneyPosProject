package com.bumslap.bum.DB;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by oyoun on 17. 12. 8.
 *
 *  작동하지 않는 클래스임.
 */

public class MenuListAdapter extends BaseAdapter {

    private List menulist;
    private Context context;

    public MenuListAdapter(List menulist, Context context){
        this.menulist = menulist;
        this.context = context;
    }

    @Override
    public int getCount(){
        return this.menulist.size();
    }

    @Override
    public Object getItem(int position){
        return this.menulist.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        Holder holder = null;

        if (converView == null) {

            converView = new LinearLayout(context);
            ((LinearLayout) converView).setOrientation(LinearLayout.HORIZONTAL);

            TextView menu_id = new TextView(context);
            menu_id.setPadding(10, 0, 20, 0);
            menu_id.setTextColor(Color.rgb(0, 0, 0));

            TextView menu_name = new TextView(context);
            menu_name.setPadding(20, 0, 20, 0);
            menu_name.setTextColor(Color.rgb(0, 0, 0));

            TextView menu_price = new TextView(context);
            menu_price.setPadding(20, 0, 20, 0);
            menu_price.setTextColor(Color.rgb(0, 0, 0));

            TextView menu_cost = new TextView(context);
            menu_cost.setPadding(20, 0, 20, 0);
            menu_cost.setTextColor(Color.rgb(0, 0, 0));


            ((LinearLayout) converView).addView(menu_id);
            ((LinearLayout) converView).addView(menu_name);
            ((LinearLayout) converView).addView(menu_price);
            ((LinearLayout) converView).addView(menu_cost);


            converView.setTag(holder);
        } else {
            holder = (Holder) converView.getTag();
        }

        Menu menu = (Menu) getItem(position);
        holder.menu_id.setText(menu.getMenu_id() + "");
        holder.menu_name.setText(menu.getMenu_name() + "");
        holder.menu_price.setText(menu.getMenu_price() + "");
        holder.menu_cost.setText(menu.getMenu_cost() + "");

        return converView;
    }
}

class Holder {
    public TextView menu_id;
    public TextView menu_name;
    public TextView menu_price;
    public TextView menu_cost;
    //public ImageView menu_Image;

}
