package com.bumslap.bum.DB;


/**
 * Created by jaein on 12/7/17.
 */

public class Order {
    public Integer Order_amount;
    public String Order_date;
    public String Order_time;
    public Integer Order_FK_menuId;

    public Integer getOrder_amount(){
        return Order_amount;
    }

    public void setOrder_amount(Integer Order_amount){
        this.Order_amount = Order_amount;
    }

    public String getOrder_date(){
        return Order_date;
    }

    public void setOrder_date(String Order_date){
        this.Order_date = Order_date;
    }

    public String getOrder_time(){
        return Order_time;
    }

    public void setOrder_Amount(String Order_amount){
        this.Order_time = Order_time;
    }

    public Integer getOrder_FK_menuId(){
        return Order_FK_menuId;
    }

    public void setOrder_FK_menuId(Integer Order_FK_menuId){
        this.Order_FK_menuId = Order_FK_menuId;
    }

}
