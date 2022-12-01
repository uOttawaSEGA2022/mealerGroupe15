package com.example.mealer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Iterator;

public class Cuisinier extends User{

    String firstName;
    String lastName;
    String adresse;
    String email;
    String password;
    String description;
    //String cheque;
    Boolean connected;
    Boolean found;
    boolean suspended;
    String suspensionTime;
    String id;
    int orderID;
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();


    public Cuisinier(){
        found = false;
        connected =  false;
        suspensionTime = "0";
        suspended = false;
    }

//    public Cuisinier(String firstName, String lastName, String addresse, String email, String password, String description){
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.addresse = addresse;
//        this.email = email;
//        this.password = password;
//        this.description = description;
//        //this.cheque = cheque;
//        connected = false;
//        id = 0;
//        emails.add(id, email);
//        passwords.add(id, password);
//
//    }

    public void acceptOrder(){

    }

    public void suspend(){

    }
    public void setTextPlainte(){
       // if/ (SuspenduDefi){


        //}
    }

    public void setInfo(String email, String password, String usr,
                        String lastName, String addresse, String description ){
        this.email = email;
        myRef = database.getReference("Cuisinier/"+usr+"/email");
        myRef.setValue(email);

        this.password = password;
        myRef = database.getReference("Cuisinier/"+usr+"/password");
        myRef.setValue(password);

        this.firstName = usr;
        myRef = database.getReference("Cuisinier/"+usr+"/firstName");
        myRef.setValue(usr);

        this.lastName = lastName;
        myRef = database.getReference("Cuisinier/"+usr+"/lastName");
        myRef.setValue(lastName);

        this.adresse = addresse;
        myRef = database.getReference("Cuisinier/"+usr+"/adresse");
        myRef.setValue(adresse);

        this.description = description;
        myRef = database.getReference("Cuisinier/"+usr+"/description");
        myRef.setValue(description);
    }

    public void signUp(String email, String password, String usr,
                       String lastName, String adress, String description){
        //Enregistrer le email dans la database
        myRef = database.getReference("Cuisinier/"+usr+"/email");
        myRef.setValue(email);
        //Enregistrer le mot de passe dans la database
        myRef = database.getReference("Cuisinier/"+usr+"/password");
        myRef.setValue(password);
        //Enregistrer le first name dans la database
        myRef = database.getReference("Cuisinier/"+usr+"/firstName");
        myRef.setValue(usr);
        //Enregistrer le last name dans la database
        myRef = database.getReference("Cuisinier/"+usr+"/lastName");
        myRef.setValue(lastName);
        //Enregistrer l'addresse dans la database
        myRef = database.getReference("Cuisinier/"+usr+"/adresse");
        myRef.setValue(adress);
        //Enregistrer le card number dans la database
        myRef = database.getReference("Cuisinier/"+usr+"/description");
        myRef.setValue(description);

        //Information par rapport a la suspension
        myRef = database.getReference("Cuisinier/"+usr+"/suspended");
        myRef.setValue(suspended);
        myRef = database.getReference("Cuisinier/"+usr+"/suspensionTime");
        myRef.setValue(suspensionTime);
        setInfo(email, password, usr, lastName, adress, description);
    }
    
    @Override
    public void connect(String email, String pswd, DataSnapshot snapshot, Context applicationContext) {
        connected = false;
        found = false;
        for(DataSnapshot children : snapshot.getChildren()){
            if(!connected){
                Log.println(Log.INFO, "TEST", " KEYSTONE : " + children.getKey());
                    if(children.child("email").getValue().toString().equals(email)){
                        found = true;
                        if(children.child("password").getValue().toString().equals(pswd)) {
                            connected = true;
                            this.email = children.child("email").getValue().toString();
                            this.password = children.child("password").getValue().toString();
                            firstName = children.child("firstName").getValue().toString();
                            lastName = children.child("lastName").getValue().toString();
                            adresse = children.child("adresse").getValue().toString();
                            description = children.child("description").getValue().toString();
                            suspended = (boolean) children.child("suspended").getValue();
                            suspensionTime = children.child("suspensionTime").getValue().toString();
                            id = children.getKey().toString();
                        }
                    }
                    Log.println(Log.DEBUG, "TEST", " Valeur de connected :" + connected.toString());
            }
        }

        Log.println(Log.INFO, "TEST", "addresse is : " + adresse + " descrition : "+ description);

        Toast.makeText(applicationContext, "BRAVO ! L'utilisateur : " + firstName + "s'est connecté", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void disconnect() {

        connected = false;
    }

    @Override
    public Boolean isConnected() {
        return connected;
    }

    public boolean isSuspended(){return suspended;}

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getAdresse(){return adresse;}
    public String getDescription(){return description;}


}
