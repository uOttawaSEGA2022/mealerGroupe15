package com.example.mealer;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;

public class Client extends User{

    String firstName;
    String lastName;
    String email;
    String password;
    String adress;
    String creditCardInfo;
    Boolean connected;
    Boolean found;
    String id;
    int orderID;
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef = database.getReference("Client");


    public Client(){
        connected =  false;
        found = false;
    }

//    public Client(String firstName, String lastName, String email, String password, String creditCardInfo){
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.creditCardInfo = creditCardInfo;
//        connected = false;
//
//    }

    public void signUp(String email, String password, String usr,
                       String lastName, String adress, String card){
        //Enregistrer le email dans la database
        myRef = database.getReference("Client/"+usr+"/email");
        myRef.setValue(email);
        //Enregistrer le mot de passe dans la database
        myRef = database.getReference("Client/"+usr+"/password");
        myRef.setValue(password);
        //Enregistrer le first name dans la database
        myRef = database.getReference("Client/"+usr+"/firstName");
        myRef.setValue(usr);
        //Enregistrer le last name dans la database
        myRef = database.getReference("Client/"+usr+"/lastName");
        myRef.setValue(lastName);
        //Enregistrer l'addresse dans la database
        myRef = database.getReference("Client/"+usr+"/adress");
        myRef.setValue(adress);
        //Enregistrer le card number dans la database
        myRef = database.getReference("Client/"+usr+"/cardNumber");
        myRef.setValue(card);
        setInfo(email, password, usr, lastName, adress, card);
    }
    public void setInfo(String email, String password, String usr,
                        String lastName, String adress, String card ){
        this.email = email;
        this.password = password;
        this.firstName = usr;
        this.lastName = lastName;
        this.adress = adress;
        this.creditCardInfo = card;
    }

    public void sendOrderRequest(Cuisinier c){

    }
    public void getOrderStatus(int orderId){

    }
//    public ArrayList<order> getHistoric(){
//
//    }
    public void rateOrder(int orderId){

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
                        adress = children.child("address").getValue().toString();
                        creditCardInfo = children.child("creditCard").getValue().toString();
                        id = children.getKey().toString();
                    }
                }
                Log.println(Log.DEBUG, "TEST", " Valeur de connected :" + connected.toString());
            }
        }
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

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getAdress(){return adress;}
    public String getCreditCardInfo(){return creditCardInfo;}

}
