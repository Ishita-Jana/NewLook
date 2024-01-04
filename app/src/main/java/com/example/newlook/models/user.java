package com.example.newlook.models;

public class user {

    public String name;
    public String email;
    public String password;
    public String studentID;
    public String role;
    public String phoneNumber;


    public user(){
    }


    public user(String name, String email, String password, String studentID, String role, String phoneNumber){
        this.name = name;
        this.email = email;
        this.password = password;
        this.studentID = studentID;
        this.role = role;
        this.phoneNumber = phoneNumber;

    }

    public String getRole() {
        return role;
    }
}
