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
    ArrayList<RepasModel> allMenuDuJourArray;
    private static final MenuModel menu = new MenuModel();

    String cuisinierId = MainActivity.cuisinier.id;

    // database reference and others
    FirebaseDatabase database;
    DatabaseReference myRef;

    private MenuModel(){
        menuArray = new ArrayList<RepasModel>();
        menuDuJourArray = new ArrayList<RepasModel>();
        allMenuDuJourArray = new ArrayList<RepasModel>();
        database = FirebaseDatabase.getInstance();
    }

    public void setCuisinierId(String s){
        cuisinierId = s;
    }

    public void save(String idCuisinier, RepasModel ancien, RepasModel nouveau, AppCompatActivity activity){
        assert !cuisinierId.isEmpty();

        nouveau.setInRepasDuJour(ancien.isInRepasDuJour());

        //Deleting the old one
        deleteFromMenuDuJour(idCuisinier, ancien);
        deleteFromMenu(idCuisinier, ancien, activity);

        // Adding the new one
        addToMenu(idCuisinier, nouveau);
    }

    public void addToMenu(String idCuisinier, RepasModel r){
        assert !idCuisinier.isEmpty();

        // Adding to the array
        menuArray.add(r);

        // Adding to the database
        myRef = database.getReference("Cuisinier/" + idCuisinier + "/menu");

        //Getting an id
        String key = myRef.push().getKey();
        r.setRepasId(key);

        // Adding the repas to the database
        assert key != null;
        myRef.child(key).setValue(r);

    }

    public void deleteFromMenu(String idCuisinier, RepasModel r, AppCompatActivity activity){
        if(r.isInRepasDuJour()){
            Toast.makeText(activity, "Vous ne pouvez pas supprimer cet éléments, il est dans vos repas du jour!", Toast.LENGTH_SHORT).show();
        }else {
            assert !idCuisinier.isEmpty();

            // Deleting from the Array
            menuArray.remove(r);

            // Deleting from the database
            myRef = database.getReference("Cuisinier/" + cuisinierId + "/menu");


            myRef.child(r.getRepasId()).removeValue();
        }

    }

    public void addToRepasDujour(String idCuisinier, RepasModel r){
        //Putting it in repas du jour in the database
        myRef = database.getReference("Cuisinier/" + idCuisinier + "/menu");
        myRef.child(r.repasId).child("inRepasDuJour").setValue(true);

        //Putting it to the repas du jour in the array and in the object
        r.setInRepasDuJour(true);
        menuDuJourArray.add(r);
    }

    public void deleteFromMenuDuJour(String idCuisinier, @NonNull RepasModel r){
        //setting inRepasDuJour false in the database
        myRef = database.getReference("Cuisinier/" + idCuisinier + "/menu/" + r.repasId);
        myRef.child("inRepasDuJour").setValue(false);

        //Setting inRepasDuJour false in the array and in the object
        r.setInRepasDuJour(false);
        menuDuJourArray.remove(r);

    }

    public void ShowMenu(@NonNull String idCuisinier,RecyclerView recyclerView, RecyclerViewInterface repasRecycleView, AppCompatActivity activity){
        assert !idCuisinier.isEmpty();

        Toast.makeText(activity, idCuisinier.toString(), Toast.LENGTH_SHORT).show();
        //show the repas
        recyclerView.setHasFixedSize(true);
        myRef= FirebaseDatabase.getInstance().getReference("Cuisinier/" + idCuisinier + "/menu");
        RepasRecyclerViewAdapter adapter=new RepasRecyclerViewAdapter(activity,menuArray,repasRecycleView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        myRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                menuArray.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren( )){
                    RepasModel repas=dataSnapshot.getValue(RepasModel.class);
                    if(!menuArray.contains(repas)){
                        menuArray.add(repas);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void ShowMenuDuJour(@NonNull String idCuisinier,RecyclerView recyclerView,RecyclerViewInterface repasDuJourRecycleView, AppCompatActivity activity){

        //shows the repas du jour
        myRef= FirebaseDatabase.getInstance().getReference("Cuisinier/" + idCuisinier + "/menu");
        RepasRecyclerViewAdapter adapter=new RepasRecyclerViewAdapter(activity,menuDuJourArray,repasDuJourRecycleView);
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

    public void showAllMenuDuJour(RepasRecyclerViewAdapter adapter, RecyclerView recyclerView, RecyclerViewInterface repasRecycleView, AppCompatActivity activity){
        //show the repas
        recyclerView.setHasFixedSize(true);
        myRef= FirebaseDatabase.getInstance().getReference("Cuisinier/");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        myRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allMenuDuJourArray.clear();
                for (DataSnapshot s : snapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot : s.child("menu").getChildren()) {
                        RepasModel repas = dataSnapshot.getValue(RepasModel.class);
                        if (!allMenuDuJourArray.contains(repas) && repas.isInRepasDuJour()) {
                            allMenuDuJourArray.add(repas);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void refresh(){
        menuArray.clear();
        menuDuJourArray.clear();
        allMenuDuJourArray.clear();
    }

    public String getCuisinierId(){
        return cuisinierId;
    }

    public static MenuModel getInstance(){
        return menu;
    }

}
