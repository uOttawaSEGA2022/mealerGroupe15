package com.example.mealer;

import java.util.ArrayList;

public class Cuisinier extends User{

    static ArrayList<String> emails;
    static ArrayList<String> passwords;
    String firstName;
    String lastName;
    String email;
    String password;
    String description;
    String cheque;
    Boolean connected;
    int id;
    int orderID;

    public Cuisinier(){
        connected =  false;
    }

    public Cuisinier(String firstName, String lastName, String email, String password, String description, String cheque){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.description = description;
        this.cheque = cheque;
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
    public void connect(String email, String pswd) {
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
