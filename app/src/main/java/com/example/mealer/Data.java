package com.example.mealer;

public class Data {
    String email;
    String password;
    AccountType type;
    public Data(String email, String password, AccountType type){
        this.email = email;
        this.password = password;
        this.type = type;

    }

    public AccountType getType(){
        return type;
    }
}
