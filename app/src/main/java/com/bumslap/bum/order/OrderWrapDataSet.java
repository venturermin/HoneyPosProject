package com.bumslap.bum.order;

import java.util.ArrayList;

/**
 * Created by oyoun on 17. 12. 19.
 */

public class OrderWrapDataSet {
    private String BillTitleNumber="ll";
    private ArrayList BillAllData;

    public OrderWrapDataSet(){

    }

    public ArrayList getBillAllData(){
        return BillAllData;
    }

    public void setBillAllData(ArrayList billAllData){
        this.BillAllData = billAllData;
    }

    public String getBillTitleNumber(){
        return BillTitleNumber;
    }

    public void setBillTitleNumber(String billTitleNumber){
        this.BillTitleNumber = BillTitleNumber;
    }
}
