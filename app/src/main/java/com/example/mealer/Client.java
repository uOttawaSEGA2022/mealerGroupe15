package com.example.mealer;

import java.util.ArrayList;

public class Client extends User{

    static ArrayList<String> emails;
    static ArrayList<String> passwords;
    String firstName;
    String lastName;
    String email;
    String password;
    String creditCardInfo;
    Boolean connected;
    int id;
    int orderID;

    public Client(){
        connected =  false;
    }

    public Client(String firstName, String lastName, String email, String password, String creditCardInfo){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCardInfo = creditCardInfo;
        connected = false;
        id = 0;
        emails.add(id, email);
        passwords.add(id, password);

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
