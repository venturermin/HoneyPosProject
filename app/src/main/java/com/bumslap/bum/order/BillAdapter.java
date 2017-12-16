package com.bumslap.bum.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumslap.bum.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by oyoun on 17. 12. 13.
 */

public class BillAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<RealtimeOrder> mItems;
    private Context mContext;
    private LayoutInflater inflater;

    int MenuAmount=0;

    public BillAdapter(ArrayList<RealtimeOrder> itemList) {

        this.mItems = itemList;

    }
    // 필수 오버라이드 : View 생성, ViewHolder 호출
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_bill_item, parent, false);
        mContext = parent.getContext();

        return new RecyclerViewHolder(v);
    }
    // 필수 오버라이드 : 재활용되는 View 가 호출, Adapter 가 해당 position 에 해당하는 데이터를 결합

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        // 해당 position 에 해당하는 데이터 결합
        RealtimeOrder realtimeOrder = mItems.get(position);
        holder.mIndex.setText(String.valueOf(position +1));
        //holder.mContent.setText(RealtimeOrder.toString());

    }

    // 필수 오버라이드 : 데이터 갯수 반환
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public final View mview;
    public TextView mIndex;
    public TextView mContent;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mview = itemView;

        mIndex = (TextView) itemView.findViewById(R.id.billsList);
        mContent = (TextView)itemView.findViewById(R.id.billamout);


    }
}

