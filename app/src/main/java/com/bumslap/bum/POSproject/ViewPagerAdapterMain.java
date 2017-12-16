package com.bumslap.bum.POSproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bumslap.bum.R;

/**
 * Created by bum on 12/16/17.
 */

public class ViewPagerAdapterMain extends FragmentPagerAdapter {


    int images[] = {R.drawable.night,R.drawable.mainimg,R.drawable.loginback};


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
    }
}
