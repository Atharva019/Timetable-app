package com.example.ttgmp;

public class User {
    public String name,email,pass;
    public User(){

    }
    public User(String name,String email,String pass){
        this.name=name;
        this.email=email;
        this.pass=pass;
    }
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPass(){return pass;}

    public void setName(String name){this.name=name;}
    public void setEmail(String name){this.email=email;}
    public void setPass(String name){this.pass=pass;}
}
