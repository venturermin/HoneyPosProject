package com.bumslap.bum.DB;

/**
 * Created by jaein on 12/7/17.
 */

public class User {
    private String User_Email;
    private String User_Name;
    private String User_StoreName;
    private String User_Password;
    private String User_Gender;
    private String User_PhoneNumber;
    private String User_Birthday;
    private String User_High_sales_per_hour;
    private String User_goal_gain;

    public User(String user_Email, String user_Name, String user_StoreName, String user_Password, String user_Gender, String user_PhoneNumber, String user_Birthday, String user_High_sales_per_hour, String user_goal_gain) {
        User_Email = user_Email;
        User_Name = user_Name;
        User_StoreName = user_StoreName;
        User_Password = user_Password;
        User_Gender = user_Gender;
        User_PhoneNumber = user_PhoneNumber;
        User_Birthday = user_Birthday;
        User_High_sales_per_hour = user_High_sales_per_hour;
        User_goal_gain = user_goal_gain;
    }

    public User() {
    }

    public String getUser_Email(){
        return User_Email;
    }

    public void setUser_Email(String User_Email){
        this.User_Email = User_Email;
    }

    public String getUser_Name(){
        return User_Name;
    }

    public void setUser_Name(String User_Name){
        this.User_Name = User_Name;
    }

    public String getUser_StoreName(){
        return User_StoreName;
    }

    public void setUser_StoreName(String User_StoreName){
        this.User_StoreName = User_StoreName;
    }

    public String getUser_Password(){
        return User_Password;
    }

    public void setUser_Password(String User_Password){
        this.User_Password = User_Password;
    }

    public String getUser_Gender(){
        return User_Gender;
    }

    public void setUser_Gender(String User_Gender){
        this.User_Gender = User_Gender;
    }

    public String getUser_PhoneNumber(){
        return User_PhoneNumber;
    }

    public void setUser_PhoneNumber(String User_PhoneNumber){
        this.User_PhoneNumber = User_PhoneNumber;
    }

    public String getUser_Birthday(){
        return User_Birthday;
    }

    public void setUser_Birthday(String User_Birthday){
        this.User_Birthday = User_Birthday;
    }

    public String getUser_High_sales_per_hour(){
        return User_High_sales_per_hour;
    }

    public void setUser_High_sales_per_hour(String User_High_sales_per_hour){
        this.User_High_sales_per_hour = User_High_sales_per_hour;
    }

    public String getUser_goal_gain(){
        return User_goal_gain;
    }

    public void setUser_goal_gain(String User_goal_gain){
        this.User_goal_gain = User_goal_gain;
    }
}
