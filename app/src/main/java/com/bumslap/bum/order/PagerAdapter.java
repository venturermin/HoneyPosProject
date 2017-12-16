package com.bumslap.bum.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by oyoun on 17. 12. 16.
 */

class PagerAdapter extends FragmentStatePagerAdapter {
    @Override
    public float getPageWidth(int position) {
        return 0.4f;
    }
    private List<Fragment> fragments;

    public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {

        super(fm);

        this.fragments = fragments;

    }

    @Override

    public Fragment getItem(int position) {

        return this.fragments.get(position);

    }

    @Override

    public int getCount() {

        return this.fragments.size();

    }

}