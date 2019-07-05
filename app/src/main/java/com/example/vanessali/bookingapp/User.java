package com.example.vanessali.bookingapp;

import com.google.firebase.database.Exclude;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String uId;

    public  User(){
        //public no-arg constructor
    }

    //constructor
    public User(String firstName, String lastName, String email,String phone, String password, String uId){
        this.firstName =firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.uId = uId;


    }


    @Exclude
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
