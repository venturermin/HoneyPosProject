package com.bumslap.bum.order;

import android.widget.ImageButton;

/**
 * Created by oyoun on 17. 12. 13.
 */

public class RealtimeOrder {

    String OrderMenuname;
    String OrderMenuAmount;

    public String getOrderMenuname() { return OrderMenuname; }

    public String getOrderMenuAmount(){return OrderMenuAmount;}



    public RealtimeOrder(String OrderMenuname) {
        this.OrderMenuname=OrderMenuname;
        //this.OrderMenuAmount = OrderMenuAmount;

    }


}
