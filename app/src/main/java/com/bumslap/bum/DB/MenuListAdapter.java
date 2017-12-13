package com.bumslap.bum.DB;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumslap.bum.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oyoun on 17. 12. 8.
 *
 *
 */

public class MenuListAdapter extends BaseAdapter {

    private ArrayList<Menu> menulist;
    private Context context;
    private int layout;

    public MenuListAdapter( Context context, int layout, ArrayList<Menu> menulist){
        this.layout = layout;
        this.menulist = menulist;
        this.context = context;
    }

    @Override
    public int getCount(){
        return this.menulist.size();
    }

    @Override
    public Object getItem(int position){
        return menulist.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    private class ViewHolder{
        ImageView MenuImage;
        TextView MenuName, MenuPrice, MenuCost;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.MenuName = (TextView)row.findViewById(R.id.menushowname);
            holder.MenuPrice = (TextView)row.findViewById(R.id.menushowprice);
            holder.MenuImage = (ImageView)row.findViewById(R.id.menushowimage);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        Menu menu = menulist.get(position);

        holder.MenuName.setText(menu.getMenu_name());
        holder.MenuPrice.setText(menu.getMenu_price());

        byte[] Menubyteimage = menu.getMenu_image();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Menubyteimage, 0, Menubyteimage.length);
        holder.MenuImage.setImageBitmap(bitmap);

        return row;
    }
}

