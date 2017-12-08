package com.bumslap.bum.DB;

/**
 * Created by oyoun on 17. 12. 7.
 */

public class Menu {
    private int Menu_id ;
    private String Menu_name;
    private String Menu_image;
    private String Menu_price;
    private String Menu_cost;

    public Menu() {
    }

    public Menu(int menu_id, String menu_name, String menu_image, String menu_price, String menu_cost) {
        Menu_id = menu_id;
        Menu_name = menu_name;
        Menu_image = menu_image;
        Menu_price = menu_price;
        Menu_cost = menu_cost;
    }

    public int getMenu_id(){
        return Menu_id;
    }

    public String getMenu_name(){
        return Menu_name;
    }

    public String getMenu_image(){
        return Menu_image;
    }

    public String getMenu_price(){
        return Menu_price;
    }

    public String getMenu_cost(){
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

    public void setMenu_price(String Menu_price){
        this.Menu_price = Menu_price;
    }

    public void setMenu_cost(String Menu_cost){
        this.Menu_cost = Menu_cost;
    }
}
