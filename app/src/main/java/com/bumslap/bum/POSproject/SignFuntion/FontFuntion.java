package com.bumslap.bum.POSproject.SignFuntion;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bum on 12/12/17.
 */

public class FontFuntion {


    public void setGlobalFont(ViewGroup root, Typeface mTypeface) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child instanceof TextView)
                ((TextView)child).setTypeface(mTypeface);
            else if (child instanceof ViewGroup)
                setGlobalFont((ViewGroup)child,mTypeface);
        }
    }

}
