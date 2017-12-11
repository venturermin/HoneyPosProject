package com.bumslap.bum.menuedit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import com.bumslap.bum.R;
/**
 * Created by min on 12/8/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<HashMap<String, Object>> arrayList;


    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView mImageView;
        public TextView mTextViewTitle;
        public TextView mTextViewDetail;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.item_image);
            mTextViewTitle = (TextView) view.findViewById(R.id.item_title);
            mTextViewDetail = (TextView) view.findViewById(R.id.item_detail);
        }
    }

    public MyAdapter(ArrayList<HashMap<String, Object>> arrayList) {
        this.arrayList = new ArrayList<HashMap<String, Object>>();
        this.arrayList = arrayList;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap<String, Object> hashMap = arrayList.get(position);
        holder.mImageView.setImageResource((Integer) hashMap.get("image"));
        holder.mTextViewTitle.setText((String) hashMap.get("title"));
        holder.mTextViewDetail.setText((String) hashMap.get("detail"));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



}

