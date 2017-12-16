package com.bumslap.bum.DB;


/**
 * Created by jaein on 12/7/17.
 */

public class Order {

    private String Order_amount;
    private String Order_date;
    private String Order_time;
    private Integer Order_FK_menuId;
    private String Order_number;

    public Order() {
    }

    public Order(String order_amount, String order_date, String order_time, Integer order_FK_menuId, String order_number) {
        Order_amount = order_amount;
        Order_date = order_date;
        Order_time = order_time;
        Order_FK_menuId = order_FK_menuId;
        Order_number = order_number;
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

    public void setOrder_time(String Order_time){
        this.Order_time = Order_time;
    }
    public Integer getOrder_FK_menuId(){
        return Order_FK_menuId;
    }

    public void setOrder_FK_menuId(Integer Order_FK_menuId){
        this.Order_FK_menuId = Order_FK_menuId;
    }
    public String getOrder_number(){ return Order_number;}

    public void setOrder_number(String Order_number){
        this.Order_number = Order_number;
    }


}
