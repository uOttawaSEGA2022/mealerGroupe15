package com.example.mealer;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;

public interface Account {

    public void connect(String email, String pswd, DataSnapshot snapshot, Context getApplicationContext);
    public void disconnect();
    public Boolean isConnected();


}
