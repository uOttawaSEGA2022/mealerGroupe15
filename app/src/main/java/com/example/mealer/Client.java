package com.example.mealer;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Client extends User{

    String firstName;
    String lastName;
    String email;
    String password;
    String adress;
    String creditCardInfo;
    Boolean connected;
    int id;
    int orderID;
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef = database.getReference("Client");


    public Client(){
        connected =  false;
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
    public void connect(String email, String pswd) {
//        for (int i =0; i>emails.size(); i++) {
//            if (email.equalsIgnoreCase(emails.get(i))) {
//                id = i;
//            }
//        }
//
//        if(pswd.equals(passwords.get(id))) {
//            connected = true;
//            // Connect to the user
//        }else{
//            connected = false;
//        }
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
