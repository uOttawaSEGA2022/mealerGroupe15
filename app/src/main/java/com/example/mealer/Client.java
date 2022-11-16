package com.example.mealer;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

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
    int id;
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
        for(DataSnapshot children : snapshot.getChildren()) {
            for (Iterator<DataSnapshot> it = children.getChildren().iterator(); it.hasNext(); ) {
                DataSnapshot snap = it.next();
                if (snap.getKey().toString().equalsIgnoreCase("email")
                        && snap.getValue().toString().equalsIgnoreCase(email)) {
                    found = true;
                }

                if (found) {
                    if (snap.getKey().toString().equalsIgnoreCase("password")) {
                        if (snap.getValue().toString().equalsIgnoreCase(pswd)) {
                            connected = true;
                            for (Iterator<DataSnapshot> it2 = snapshot.getChildren().iterator().next().getChildren().iterator(); it2.hasNext(); ) {
                                DataSnapshot snap2 = it2.next();
                                if (snap2.getKey().toString().equalsIgnoreCase("email")) {
                                    email = snap2.getValue().toString();
                                }
                                if (snap2.getKey().toString().equalsIgnoreCase("password")) {
                                    password = snap2.getValue().toString();
                                }
                                if (snap2.getKey().toString().equalsIgnoreCase("firstName")) {
                                    firstName = snap2.getValue().toString();
                                }
                                if (snap2.getKey().toString().equalsIgnoreCase("lastName")) {
                                    lastName = snap2.getValue().toString();
                                }
                                if (snap2.getKey().toString().equalsIgnoreCase("adress")) {
                                    adress = snap2.getValue().toString();
                                }
                                if (snap2.getKey().toString().equalsIgnoreCase("cardNumber")) {
                                    creditCardInfo = snap2.getValue().toString();
                                }

                            }
                            // Connect to the user
                        } else {
                            connected = false;
                        }
                    }
                }
                Log.println(Log.DEBUG, "TEST", "key : " + snap.getKey().toString() + " Value : " + snap.getValue().toString()
                        + " Valeur de connected :" + connected.toString());
            }
            Log.println(Log.INFO, "TEST", "adress is : " + adress);
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
