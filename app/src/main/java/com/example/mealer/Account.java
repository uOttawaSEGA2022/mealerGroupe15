package com.example.mealer;

public interface Account {

    public void connect(String email, String pswd);
    public void disconnect();
    public Boolean isConnected();


}
