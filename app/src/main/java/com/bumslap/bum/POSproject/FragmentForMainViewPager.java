package com.bumslap.bum.POSproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumslap.bum.R;

public class FragmentForMainViewPager extends Fragment{

    public static FragmentForMainViewPager newInstance(int position){

        Bundle args = new Bundle();
        args.putInt("position",position);
        FragmentForMainViewPager fragmentForMainViewPager = new FragmentForMainViewPager();
        fragmentForMainViewPager.setArguments(args);
        return fragmentForMainViewPager;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_main,container,false);

        ImageView imageView = view.findViewById(R.id.imageView3);
        imageView.setImageResource(getArguments().getInt("position"));
        return view;
    }
}



