package com.bumslap.bum.POSproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumslap.bum.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bum on 12/16/17.
 */

public class ViewPagerAdapterMain extends FragmentStatePagerAdapter implements CardAdapter {

    private List<FragmentForMainViewPager> mFragments;
    private float mBaseElevation;

    public ViewPagerAdapterMain(FragmentManager fm, float baseElevation) {
        super(fm);
        mFragments = new ArrayList<>();
        mBaseElevation = baseElevation;

        for(int i = 0; i< 5; i++){
            addCardFragment(new FragmentForMainViewPager());
        }
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mFragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_fragment_main,container,false);
        Object fragment = super.instantiateItem(container, position);
        mFragments.set(position, (FragmentForMainViewPager) fragment);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    Toast.makeText(MainActivity.context,position,Toast.LENGTH_SHORT).show();
                }
            }
        });


        return fragment;
    }

    public void addCardFragment(FragmentForMainViewPager fragment) {
        mFragments.add(fragment);
    }

}/*{


    int images[] = {R.drawable.first_time_viewpager,R.drawable.mainimg,R.drawable.loginback};


    public ViewPagerAdapterMain(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentForMainViewPager.newInstance(images[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }*/

