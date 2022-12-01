package com.example.mealer;

import android.annotation.SuppressLint;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuModel {

    ArrayList<RepasModel> menuArray;
    ArrayList<RepasModel> menuDuJourArray;
    private static final MenuModel menu = new MenuModel();

    String cuisinierId = MainActivity.cuisinier.id;

    // database reference and others
    FirebaseDatabase database;
    DatabaseReference myRef;

    private MenuModel(){
        menuArray = new ArrayList<RepasModel>();
        menuDuJourArray = new ArrayList<RepasModel>();
        database = FirebaseDatabase.getInstance();
    }

    public void save(RepasModel ancien, RepasModel nouveau, AppCompatActivity activity){
        assert !cuisinierId.isEmpty();

        nouveau.setInRepasDuJour(ancien.isInRepasDuJour());

        //Deleting the old one
        deleteFromMenuDuJour(ancien);
        deleteFromMenu(ancien, activity);

        // Adding the new one
        addToMenu(nouveau);
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

    public void deleteFromMenu(RepasModel r, AppCompatActivity activity){
        if(r.isInRepasDuJour()){
            Toast.makeText(activity, "Vous ne pouvez pas supprimer cet éléments, il est dans vos repas du jour!", Toast.LENGTH_SHORT).show();
        }else {


            assert !cuisinierId.isEmpty();

            // Deleting from the Array
            menuArray.remove(r);

            // Deleting from the database
            myRef = database.getReference("Cuisinier/" + cuisinierId + "/menu");


            myRef.child(r.getRepasId()).removeValue();
        }

    }

    public void addToRepasDujour(RepasModel r){
        //Putting it in repas du jour in the database
        myRef = database.getReference("Cuisinier/" + cuisinierId + "/menu");
        myRef.child(r.repasId).child("inRepasDuJour").setValue(true);

        //Putting it to the repas du jour in the array and in the object
        r.setInRepasDuJour(true);
        menuDuJourArray.add(r);
    }

    public void refresh(){
        menuArray = new ArrayList<RepasModel>();
        menuDuJourArray = new ArrayList<RepasModel>();
    }

    public void deleteFromMenuDuJour(@NonNull RepasModel r){
        //setting inRepasDuJour false in the database
        myRef = database.getReference("Cuisinier/" + cuisinierId + "/menu/" + r.repasId);
        myRef.child("inRepasDuJour").setValue(false);

        //Setting inRepasDuJour false in the array and in the object
        r.setInRepasDuJour(false);
        menuDuJourArray.remove(r);

    }

    public void ShowMenu(RecyclerViewInterface repasRecycleView, AppCompatActivity activity){
        assert !cuisinierId.isEmpty();

        Toast.makeText(activity, getCuisinierId().toString(), Toast.LENGTH_SHORT).show();
        //show the repas
        RecyclerView recyclerView= activity.findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        myRef= FirebaseDatabase.getInstance().getReference("Cuisinier/" + cuisinierId + "/menu");
        RepasRecyclerViewAdapter adapter=new RepasRecyclerViewAdapter(activity,menuArray,repasRecycleView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        myRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren( )){
                    RepasModel repas=dataSnapshot.getValue(RepasModel.class);
                    menuArray.add(repas);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void ShowMenuDuJour(RecyclerViewInterface repasDuJourRecycleView, AppCompatActivity activity){

        //shows the repas du jour
        RecyclerView recyclerView= activity.findViewById(R.id.RecyclerView);
        myRef= FirebaseDatabase.getInstance().getReference("Cuisinier/" + getCuisinierId() + "/menu");
        RepasDuJourRecyclerViewAdapter adapter=new RepasDuJourRecyclerViewAdapter(activity,menuDuJourArray,repasDuJourRecycleView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren( )){
                    RepasModel repas=dataSnapshot.getValue(RepasModel.class);
                    if(repas.isInRepasDuJour()){
                        menuDuJourArray.add(repas);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String getCuisinierId(){
        return cuisinierId;
    }

    public static MenuModel getInstance(){
        return menu;
    }

}
