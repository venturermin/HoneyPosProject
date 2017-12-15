package com.bumslap.bum.order;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumslap.bum.R;

import java.util.ArrayList;


public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "pageNumber";
    private RecyclerView PageRecyclerView;
    private RecyclerView.Adapter PageAdapter;
    private RecyclerView.LayoutManager PageLayoutManager;
    ArrayList<RealtimeOrder> Billordermenu;


    public static PageFragment create(int pageNumber) {
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE_NUMBER, pageNumber);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        PageRecyclerView = (RecyclerView) view.findViewById(R.id.billsrecyclerview);
        if(view instanceof RecyclerView){

            Context context = view.getContext();
            PageRecyclerView = (RecyclerView) view;
            PageRecyclerView.setHasFixedSize(true);

            PageLayoutManager = new LinearLayoutManager(context);
            PageRecyclerView.setLayoutManager(PageLayoutManager);

            PageAdapter = new BillAdapter(Billordermenu);
            PageRecyclerView.setAdapter(PageAdapter);
        }

        return view;
    }

}
