package com.example.mealer;

import android.annotation.SuppressLint;
import android.content.Context;
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

    // databade reference and others
    FirebaseDatabase database;
    DatabaseReference myRef;

    private MenuModel(){
        menuArray = new ArrayList<RepasModel>();
        menuDuJourArray = new ArrayList<RepasModel>();
        database = FirebaseDatabase.getInstance();
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
        //Putting it in repas du jour in the database
        myRef = database.getReference("Cuisinier/" + cuisinierId + "/menu");
        myRef.child(r.repasId).child("inRepasDuJour").setValue(true);

        //Putting it to the repas du jour in the array and in the object
        r.setInRepasDuJour("true");
        menuDuJourArray.add(r);
    }



    public void deleteFromMenuDuJour(@NonNull RepasModel r){
        //setting inRepasDuJour false in the database
        myRef = database.getReference("Cuisinier/" + cuisinierId + "/menu/" + r.repasId);
        myRef.child("inRepasDuJour").setValue(false);

        //Setting inRepasDuJour false in the array and in the object
        r.setInRepasDuJour("false");
        menuDuJourArray.remove(r);

    }

    public void ShowMenu(RecyclerViewInterface repasRecycleView, AppCompatActivity activity){

        Toast.makeText(activity, "added", Toast.LENGTH_SHORT).show();
        //show the repas
        RecyclerView recyclerView= activity.findViewById(R.id.repasRecycleView);
        myRef= FirebaseDatabase.getInstance().getReference("Cuisinier/" + getCuisinierId() + "menu");
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

    public void ShowMenuDuJour(Context context, RecyclerViewInterface repasDuJourRecycleView, AppCompatActivity activity){

        //shows the repas du jour
        RecyclerView recyclerView= activity.findViewById(R.id.RecyclerMenuJour);
        myRef= FirebaseDatabase.getInstance().getReference("Cuisinier/" + getCuisinierId() + "menu");
        RepasDuJourRecyclerViewAdapter adapter=new RepasDuJourRecyclerViewAdapter(context,menuDuJourArray,repasDuJourRecycleView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
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
