package com.bumslap.bum.DB;


/**
 * Created by jaein on 12/7/17.
 */

public class Order {
    public String Order_amount;
    public String Order_date;
    public String Order_time;
    public Integer Order_FK_menuId;

    public Order() {
    }

    public Order(String order_amount, String order_date, String order_time, Integer order_FK_menuId) {
        Order_amount = order_amount;
        Order_date = order_date;
        Order_time = order_time;
        Order_FK_menuId = order_FK_menuId;
    }

    public String getOrder_amount(){
        return Order_amount;
    }

    public void setOrder_amount(String Order_amount){
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
