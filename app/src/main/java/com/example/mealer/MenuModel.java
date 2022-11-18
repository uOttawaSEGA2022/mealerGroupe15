package com.example.mealer;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MenuModel {

    ArrayList<RepasModel> menuArray;
    ArrayList<RepasModel> menuDuJourArray;
    private static final MenuModel menu = new MenuModel();

    String cuisinierId;

    // databade reference and others
    FirebaseDatabase database;
    DatabaseReference myRef;

    private MenuModel(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Cuisinier");
    }

    public void addToMenu(RepasModel r){
        assert !cuisinierId.isEmpty();

        // Adding to the array
        menuArray.add(r);

        // Adding to the database
        myRef = database.getReference("Cuisinier/" + cuisinierId + "/menu");

        //Getting an id
        String key = myRef.push().getKey();
        r.setRepasId(key);
        // Adding the repas to the database
        assert key != null;
        myRef.child(key).setValue(r);



    }

    public void deleteFromMenu(RepasModel r){
        assert !r.isInRepasDuJour();
        assert !cuisinierId.isEmpty();

        // Deleting from the Array
        menuArray.remove(r);

        // Deleting from the database
        myRef = database.getReference("Cuisinier/" + cuisinierId + "/menu");

        myRef.child(r.getRepasId()).removeValue();

    }

    public void addToRepasDujour(RepasModel r){
        //TODO Implement this method
        myRef = database.getReference("Cuisinier/" + cuisinierId + "/menu");
        myRef.child(r.repasId).child("inRepasDuJour").setValue(true);
        r.setInRepasDuJour("true");
        menuDuJourArray.add(r);

    }

    public void deleteFromMenuDuJour(RepasModel r){
        //TODO Implement this method


    }

    public void ShowMenu(){
        //TODO Implement this method
    }

    public void ShowMenuDuJour(){
        //TODO Implement this method

    }

    public String getCuisinierId(){
        return cuisinierId;
    }

    public void setCuisinierId(String id){
        cuisinierId = id;
    }

    private static MenuModel getInstance(){
        return menu;
    }

}
