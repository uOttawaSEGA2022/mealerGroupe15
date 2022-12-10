package com.example.mealer.clientclasses;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.mealer.Account;
import com.example.mealer.cuisinierclasses.Cuisinier;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Client implements Account {

    String firstName;
    String lastName;
    String email;
    String password;
    String address;
    String creditCardInfo;
    Boolean connected;
    String id;
    int orderID;
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef = database.getReference("Client");
    static Client c = new Client();


    private Client(){
        connected =  false;
    }

    public static Client getInstance() {
        return c;
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
                       String lastName, String address, String card){
        //Generating a random id and saving it
        id = myRef.push().getKey();
        myRef = database.getReference("Client/"+id+"/id");
        myRef.setValue(id);

        //Stocking all the information temporally internally and forever in the database
        setInfo(email, password, usr, lastName, address, card);
        connected = true;
    }
    public void setInfo(String email, String password, String usr,
                        String lastName, String adress, String card ){
        this.email = email;
        myRef = database.getReference("Client/"+id+"/email");
        myRef.setValue(email);

        this.password = password;
        myRef = database.getReference("Client/"+id+"/password");
        myRef.setValue(password);

        this.firstName = usr;
        myRef = database.getReference("Client/"+id+"/firstName");
        myRef.setValue(usr);

        this.lastName = lastName;
        myRef = database.getReference("Client/"+id+"/lastName");
        myRef.setValue(lastName);

        this.address = adress;
        myRef = database.getReference("Client/"+id+"/address");
        myRef.setValue(address);

        this.creditCardInfo = card;
        myRef = database.getReference("Client/"+id+"/cardNumber");
        myRef.setValue(card);

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
        for(DataSnapshot children : snapshot.getChildren()){
            if(!connected){
                Log.println(Log.INFO, "TEST", " KEYSTONE : " + children.getKey());
                if(children != null && children.child("email").getValue().toString().equals(email)){
                    if(children.child("password").getValue().toString().equals(pswd)) {
                        connected = true;
                        this.email = children.child("email").getValue().toString();
                        this.password = children.child("password").getValue().toString();
                        firstName = children.child("firstName").getValue().toString();
                        lastName = children.child("lastName").getValue().toString();
                        address = children.child("address").getValue().toString();
                        creditCardInfo = children.child("cardNumber").getValue().toString();
                        id = children.getKey().toString();
                    }
                }
                Log.println(Log.DEBUG, "TEST", " Valeur de connected :" + connected.toString());
            }
        }
        Toast.makeText(applicationContext, "BRAVO ! L'utilisateur : " + firstName + " s'est connecté", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void disconnect() {
        connected = false;
        refresh();
    }

    private void refresh() {
        c = new Client();
    }

    @Override
    public Boolean isConnected() {
        return connected;
    }

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getAdress(){return address;}
    public String getCreditCardInfo(){return creditCardInfo;}
    public String getId(){return id;}

}
