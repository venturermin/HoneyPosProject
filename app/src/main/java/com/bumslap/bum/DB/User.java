package com.bumslap.bum.DB;

/**
 * Created by jaein on 12/7/17.
 */

public class User {
    public String User_Email;
    public String User_Name;
    public String User_StoreName;
    public String User_Password;
    public Integer User_Gender;
    public Integer User_PhoneNumber;
    public String User_Birthday;
    public String User_High_sales_per_hour;
    public String User_goal_gain;

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

    public Integer getUser_Gender(){
        return User_Gender;
    }

    public void setUser_Gender(Integer User_Gender){
        this.User_Gender = User_Gender;
    }

    public Integer getUser_PhoneNumber(){
        return User_PhoneNumber;
    }

    public void setUser_PhoneNumber(Integer User_PhoneNumber){
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
