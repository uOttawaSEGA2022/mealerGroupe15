package com.example.mealer.adminclasses;


import android.content.Context;
import android.util.Log;

import com.example.mealer.Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Iterator;

public class Admin implements Account {

    protected static String[] emails = new String[]{"test1","MORFALLSYLLA","KHADYAMATH","BABA"};
    protected static String[] passwords = new String[]{"test1","PMS11","KAK25","DONBABA"};
    private String[] firstNames;
    private String[] lastNames;
    private String[] usernames;
    private Boolean connected;
    private Boolean found;
    private static int id;
    FirebaseDatabase database;
    DatabaseReference myRef;
    static Admin a = new Admin();

    private Admin(){
        id = 0;
        firstNames = new String[1];
        lastNames = new String[1];
        usernames = new String[1];
        connected = false;
        found = false;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Admin");

    }

    public static Admin getInstance() {
        return a;
    }

    public void setInfo(String email, String password){
        emails[id] = email;
        passwords[id] = password;
    }

    @Override
    public void connect(String email, String pswd, DataSnapshot snapshot, Context applicationContext) {
        for (Iterator<DataSnapshot> it = snapshot.getChildren().iterator().next().getChildren().iterator(); it.hasNext();) {
            DataSnapshot snap =  it.next();
            if(snap.getKey().toString().equalsIgnoreCase("mail")
                    && snap.getValue().toString().equalsIgnoreCase(email)){
                found = true;
            }

            if(found){
                if(snap.getKey().toString().equalsIgnoreCase("motdepass")){
                    if(snap.getValue().toString().equalsIgnoreCase(pswd)) {
                        connected = true;
                        // Connect to the user
                    }else{
                        connected = false;
                    }
                }

            }
            Log.println(Log.DEBUG, "TEST", "key : " + snap.getKey().toString() + " Value : " + snap.getValue().toString()
                    + "Valeur de connected :" + connected.toString());
        }
    }

    @Override
    public void disconnect() {
        id = 0;
        firstNames = new String[1];
        lastNames = new String[1];
        usernames = new String[1];
        connected = false;
        found = false;
        connected = false;
        // Disconnect the user
    }

    @Override
    public Boolean isConnected() {
        return  connected;
    }

    private void suspend(String cuisinnierId, String suspensionTime){
        DatabaseReference suspensionRef = FirebaseDatabase.getInstance().getReference("Cuisinier/" + cuisinnierId);
        suspensionRef.child("suspended").setValue(true);
        suspensionRef.child("suspensionTime").setValue(suspensionTime);

    }

    private void validateComplaint(int id){
        // accept or refuse complaints;
    }
}
