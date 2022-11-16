package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class PlainteActivity<plainte> extends AppCompatActivity implements RecyclerViewInterface{
    ArrayList<PlainteModel> modeeldeplainte=new ArrayList<>();
    DatabaseReference databaseReference;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PlainteActivity.this,MainActivityAdmin.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plainte);
        RecyclerView recyclerView=findViewById(R.id.myRecycleView);
        databaseReference= FirebaseDatabase.getInstance().getReference("Plainte");
        //setupModeeldeplainte();
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,modeeldeplainte,this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren( )){
                    PlainteModel plainte=dataSnapshot.getValue(PlainteModel.class);
                    if(plainte!=null){
                        modeeldeplainte.add(plainte);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
   /*private void setupModeeldeplainte(){
        String [] ClientNamess= getResources().getStringArray(R.array.Full_listplainte_nomClient);
        String [] CuisiniersNames= getResources().getStringArray(R.array.Full_listplainte_nomCuisinier);
        String [] Descrip= getResources().getStringArray(R.array.Full_listplainte_Desc);
        for (int i=0; i<ClientNamess.length;i++){
            modeeldeplainte.add(new PlainteModel(ClientNamess[i],CuisiniersNames[i],Descrip[i]));


        }
    }*/
   //String id;
    public void SuprimerPlainte(PlainteModel plainte) {


        databaseReference.child(plainte.id).removeValue();
        //id = databaseReference.getKey();
        //databaseReference.child(dataSnapshot.get.id).setValue()
        /*DatabaseReference Dr=FirebaseDatabase.getInstance().getReference("Plainte").child(id);
        Dr.removeValue();*/
        Toast.makeText(getApplicationContext(), "Plainte supprimée", Toast.LENGTH_SHORT).show();
        //return true;
    }


    @Override
    public void OnItemClick(int position) {
       //String _id = databaseReference.push().getKey();


       Intent intent=new Intent(PlainteActivity.this,dialogue.class);
       startActivity(intent);
        SuprimerPlainte(modeeldeplainte.get(position));



    }
    public void OnReturnToAdmin(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivityAdmin.class);
        startActivityForResult(intent, 0);
    }
}