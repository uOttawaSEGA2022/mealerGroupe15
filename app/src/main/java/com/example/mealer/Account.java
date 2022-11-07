package com.example.mealer;

import com.google.firebase.database.DataSnapshot;

public interface Account {

    public void connect(String email, String pswd, DataSnapshot snapshot);
    public void disconnect();
    public Boolean isConnected();


}
