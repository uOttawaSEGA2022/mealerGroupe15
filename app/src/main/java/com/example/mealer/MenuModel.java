package com.example.mealer;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.provider.ContactsContract;
import android.util.Log;
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
    ArrayList<commandeModel> commandeArray;
    ArrayList<commandeModel> demandesArray;

    private static final MenuModel menu = new MenuModel();

    String cuisinierId = Cuisinier.getInstance().id;
    String clientId = Client.getInstance().id;

    // database reference and others
    FirebaseDatabase database;
    DatabaseReference myRef;

    private MenuModel(){
        menuArray = new ArrayList<RepasModel>();
        menuDuJourArray = new ArrayList<RepasModel>();
        allMenuDuJourArray = new ArrayList<RepasModel>();
        commandeArray = new ArrayList<commandeModel>();
        demandesArray = new ArrayList<commandeModel>();
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

    public void showDemandes(@NonNull String idCuisinier,RecyclerView recyclerView,
                             RecyclerViewInterface recyclerViewInterface, AppCompatActivity activity){

        assert !idCuisinier.isEmpty();
        Toast.makeText(activity, idCuisinier.toString(), Toast.LENGTH_SHORT).show();

        //show the Orders
        recyclerView.setHasFixedSize(true);
        DatabaseReference demandesRef= FirebaseDatabase.getInstance().getReference("Cuisinier/" + idCuisinier + "/Demandes");
        CommandeRecyclerViewAdapter adapter=new CommandeRecyclerViewAdapter(activity,demandesArray,recyclerViewInterface);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        ValueEventListener demandeListener = new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                demandesArray.clear();

                for(DataSnapshot dataSnapshot:snapshot.getChildren( )){
                    commandeModel demande = dataSnapshot.getValue(commandeModel.class);
                    if(!demandesArray.contains(demande)){
                        demandesArray.add(demande);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        };
        demandesRef.addValueEventListener(demandeListener);
    }

    public void addCommande(String idClient, commandeModel m){
        assert !idClient.isEmpty();

        // Adding to the array
        commandeArray.add(m);

        // Adding to the database
        myRef = database.getReference("Client/" + idClient + "/Commande");

        //Getting an id
        String key = myRef.push().getKey();
        m.setIdDeLaCommande(key);

        // Adding the repas to the database
        assert key != null;
        myRef.child(key).setValue(m);
    }

    public void showCommande(@NonNull String idClient,RecyclerView recyclerView, RecyclerViewInterface RecycleView, AppCompatActivity activity){
        assert !idClient.isEmpty();

        Toast.makeText(activity, idClient.toString(), Toast.LENGTH_SHORT).show();
        //show the Orders
        recyclerView.setHasFixedSize(true);
        DatabaseReference comRef= FirebaseDatabase.getInstance().getReference("Client/" + idClient + "/Commande");
        CommandeRecyclerViewAdapter adapter=new CommandeRecyclerViewAdapter(activity,commandeArray,RecycleView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        ValueEventListener commandeListener = new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commandeArray.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren( )){
                    commandeModel commande=dataSnapshot.getValue(commandeModel.class);
//                    DatabaseReference repasRef = FirebaseDatabase.getInstance().getReference("Cuisinier/" + commande.idDuCuisinier + "/menu");
//                    repasRef.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snap) {
//                            Log.d(TAG, "commande.idducuisinier : " + commande.idDuCuisinier + " onDataChange: " + snap.child("firstName"));
//
//                                for(DataSnapshot place : snap.getChildren()) {
//                                    if(place.getKey() == commande.idDuRepas){
//                                        commande.setRepas(place.getValue(RepasModel.class));
//                                    }
//                                    Log.d(TAG, "onDataChange: " + place.getValue(RepasModel.class).getNom());
//
//                                }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });

                    if(!commandeArray.contains(commande)){
                        commandeArray.add(commande);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        comRef.addValueEventListener(commandeListener);
    }

    public void refresh(){
        menuArray.clear();
        menuDuJourArray.clear();
        allMenuDuJourArray.clear();
        commandeArray.clear();
    }

    public static MenuModel getInstance(){
        return menu;
    }


}
