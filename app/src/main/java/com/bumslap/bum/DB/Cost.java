package com.bumslap.bum.DB;


/**
 * Created by jaein on 12/7/17.
 */

public class Cost {
    public String Cost_name;
    public String Cost_price;
    public Integer Cost_FK_menuId;

    public Cost() {
    }

    public Cost(String cost_name, String cost_price, Integer cost_FK_menuId) {

        Cost_name = cost_name;
        Cost_price = cost_price;
        Cost_FK_menuId = cost_FK_menuId;
    }

    public String getCost_name(){
        return Cost_name;
    }

    public void setCost_name(String Cost_name){
        this.Cost_name = Cost_name;
    }

    public String getCost_price(){
        return Cost_price;
    }

    public void setCost_price(String Cost_price){
        this.Cost_price = Cost_price;
    }

    public Integer getCost_FK_menuId(){
        return Cost_FK_menuId;
    }

    public void setCost_FK_menuId(Integer Cost_FK_menuId){
        this.Cost_FK_menuId = Cost_FK_menuId;
    }

}
