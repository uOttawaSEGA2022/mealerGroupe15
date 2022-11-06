package com.example.mealer;


import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin implements Account{

    protected static String[] emails = new String[]{"test1","MORFALLSYLLA","KHADYAMATH","BABA"};
    protected static String[] passwords = new String[]{"test1","PMS11","KAK25","DONBABA"};
    private String[] firstNames;
    private String[] lastNames;
    private String[] usernames;
    private Boolean connected;
    private static int id;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public Admin(){
        id = 0;
        firstNames = new String[1];
        lastNames = new String[1];
        usernames = new String[1];
        connected = false;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Admin");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
    public void setInfo(String email, String password){
        emails[id] = email;
        passwords[id] = password;
    }

    @Override
    public void connect(String email, String pswd) {
        for (int i =0; i<emails.length; i++) {
            if (email.equalsIgnoreCase(emails[i])) {
                id = i;
            }
        }

        if(pswd.equals(passwords[id])) {
            connected = true;
            // Connect to the user
        }else{
            connected = false;
        }

    }

    @Override
    public void disconnect() {
        connected = false;
        // Disconnect the user
    }

    @Override
    public Boolean isConnected() {
        return  connected;
    }

    private void suspend(Cuisinier c){

    }

    private void fetchComplaints(){

    }

    private void validateComplaint(int id){
        // accept or refuse complaints;
    }
}
