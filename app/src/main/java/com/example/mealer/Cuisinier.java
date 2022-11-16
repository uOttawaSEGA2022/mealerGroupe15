package com.example.mealer;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Iterator;

public class Cuisinier extends User{

    String firstName;
    String lastName;
    String addresse;
    String email;
    String password;
    String description;
    //String cheque;
    Boolean connected;
    Boolean found;
    int id;
    int orderID;
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();


    public Cuisinier(){
        found = false;
        connected =  false;
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
        this.password = password;
        this.firstName = usr;
        this.lastName = lastName;
        this.addresse = addresse;
        this.description = description;
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
        setInfo(email, password, usr, lastName, adress, description);
    }
    
    @Override
    public void connect(String email, String pswd, DataSnapshot snapshot) {
        connected = false;
        found = false;
        for(DataSnapshot children : snapshot.getChildren()){
            Log.println(Log.INFO, "TEST", " KEYSTONE : " + children.getKey());
            for (Iterator<DataSnapshot> it = children.getChildren().iterator(); it.hasNext();) {
                DataSnapshot snap =  it.next();
                if(snap.getKey().toString().equalsIgnoreCase("email")
                        && snap.getValue().toString().equalsIgnoreCase(email)){
                    found = true;
                }

                if(found){
                    if(snap.getKey().toString().equalsIgnoreCase("password")){
                        if(snap.getValue().toString().equalsIgnoreCase(pswd)) {
                            connected = true;
                            for (Iterator<DataSnapshot> it2 = snapshot.getChildren().iterator().next().getChildren().iterator(); it2.hasNext();) {
                                DataSnapshot snap2 =  it2.next();
                                if(snap2.getKey().toString().equalsIgnoreCase("email")){
                                    email = snap2.getValue().toString();
                                }
                                if(snap2.getKey().toString().equalsIgnoreCase("password")){
                                    password = snap2.getValue().toString();
                                }
                                if(snap2.getKey().toString().equalsIgnoreCase("firstName")){
                                    firstName = snap2.getValue().toString();
                                }
                                if(snap2.getKey().toString().equalsIgnoreCase("lastName")){
                                    lastName = snap2.getValue().toString();
                                }
                                if(snap2.getKey().toString().equalsIgnoreCase("addresse")){
                                    addresse = snap2.getValue().toString();
                                }
                                if(snap2.getKey().toString().equalsIgnoreCase("description")){
                                    description = snap2.getValue().toString();
                                }

                            }
                            // Connect to the user
                        }else{
                            connected = false;
                        }
                    }
                }
                Log.println(Log.DEBUG, "TEST", "key : " + snap.getKey().toString() + " Value : " + snap.getValue().toString()
                        + " Valeur de connected :" + connected.toString());
            }
        }

        Log.println(Log.INFO, "TEST", "addresse is : " + addresse + " descrition : "+ description);
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
