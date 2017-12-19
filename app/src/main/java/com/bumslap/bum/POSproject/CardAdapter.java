package com.bumslap.bum.POSproject;

import android.support.v7.widget.CardView;

/**
 * Created by bum on 12/18/17.
 */

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;
    float getBaseElevation();
    CardView getCardViewAt(int position);
    int getCount();
}
