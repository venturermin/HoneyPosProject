package com.bumslap.bum.menuedit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import com.bumslap.bum.DB.DBProvider;
import com.bumslap.bum.DB.Menu;
import com.bumslap.bum.R;
/**
 * Created by min on 12/8/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<com.bumslap.bum.DB.Menu> menulist;
    public Menu menu;
    public DBProvider db;
    int layout;
    Context context;
    RecyclerView.Adapter mMyadapter;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    public MyAdapter(DBProvider db,int layout, ArrayList<Menu> menulist) {
        //ASSIGN THEM LOCALLY
        this.db = db;
        this.layout = layout;
        this.menulist = menulist;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        //VIEW OBJ FROM XML
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        menu = menulist.get(position);
        holder.mTextUniqueId.setText(menu.getMenu_id());
        holder.mTextViewTitle.setText(menu.getMenu_name());
        holder.mTextViewPrice.setText(menu.getMenu_price());

        //menulist = new ArrayList<com.bumslap.bum.DB.Menu>();
        //menulist.get(0).getMenu_id();


        byte[] Menubyteimage = menu.getMenu_image();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Menubyteimage, 0, Menubyteimage.length);

        holder.mImageView.setImageBitmap(bitmap);

        holder.mTextUniqueId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.deleteData(menulist.get(position).getMenu_id());
                Toast.makeText(context, "Data deleted", Toast.LENGTH_LONG).show();
                notifyItemRemoved(position);

            }
        });

    }


    @Override
    public int getItemCount() {
        return menulist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextUniqueId;
        public TextView mTextViewTitle;
        public TextView mTextViewPrice;

        public com.bumslap.bum.DB.DBHelper mDBHelper;
        public RecyclerView.Adapter mAdapter;

        public ViewHolder(View view) {
            super(view);
            mTextUniqueId = (TextView) view.findViewById(R.id.item_uniqueID);
            mImageView = (ImageView) view.findViewById(R.id.item_image);
            mTextViewTitle = (TextView) view.findViewById(R.id.item_title);
            mTextViewPrice = (TextView) view.findViewById(R.id.item_price);

            //occur image click
        }
    }

}

