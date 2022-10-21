package com.example.mealer;

public interface Account {
    String username = new String();
    String firstName = new String();
    String lastName = new String();
    String email = new String();
    String password = new String();
    Boolean connected = false;

    public void connect(String usrnm, String pswd);
    public void disconnect();
    public Boolean isConnected();


}
