package com.example.mealer;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Cuisinier extends User{

    static ArrayList<String> emails;
    static ArrayList<String> passwords;
    String firstName;
    String lastName;
    String addresse;
    String email;
    String password;
    String description;
    //String cheque;
    Boolean connected;
    int id;
    int orderID;

    public Cuisinier(){
        connected =  false;
    }

    public Cuisinier(String firstName, String lastName, String addresse, String email, String password, String description){
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresse = addresse;
        this.email = email;
        this.password = password;
        this.description = description;
        //this.cheque = cheque;
        connected = false;
        id = 0;
        emails.add(id, email);
        passwords.add(id, password);

    }

    public void acceptOrder(){

    }

    public void suspend(){

    }

    @Override
    public void connect(String email, String pswd, DataSnapshot snapshot) {
        for (int i =0; i>emails.size(); i++) {
            if (email.equalsIgnoreCase(emails.get(i))) {
                id = i;
            }
        }

        if(pswd.equals(passwords.get(id))) {
            connected = true;
            // Connect to the user
        }else{
            connected = false;
        }
    }

    @Override
    public void disconnect() {
        connected = false;
    }

    @Override
    public Boolean isConnected() {
        return connected;
    }
}
