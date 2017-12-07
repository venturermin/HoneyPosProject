package com.bumslap.bum.DB;

/**
 * Created by oyoun on 17. 12. 7.
 */

public class Menu {
    private int Menu_id;

    private String Menu_name;
    private String Menu_image;
    private int Menu_price;
    private int Menu_cost;

    public int getMenu_id(){
        return Menu_id;
    }

    public String getMenu_name(){
        return Menu_name;
    }

    public String getMenu_image(){
        return Menu_image;
    }

    public int getMenu_price(){
        return Menu_price;
    }

    public int getMenu_cost(){
        return Menu_cost;
    }

    public void setMenu_id(int Menu_id){
        this.Menu_id = Menu_id;
    }

    public void setMenu_name(String Menu_name){
        this.Menu_name = Menu_name;
    }

    public void setMenu_image(String Menu_image){
        this.Menu_image = Menu_image;
    }

    public void setMenu_price(int Menu_price){
        this.Menu_price = Menu_price;
    }

    public void setMenu_cost(int Menu_cost){
        this.Menu_cost = Menu_cost;
    }
}
