package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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

public class PlainteActivity extends AppCompatActivity implements RecyclerViewInterface{
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
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView=findViewById(R.id.RecycleView);
        databaseReference= FirebaseDatabase.getInstance().getReference("Plainte");
        //setupModeeldeplainte();
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,modeeldeplainte,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren( )){
                    PlainteModel user=dataSnapshot.getValue(PlainteModel.class);
                    modeeldeplainte.add(user);

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

    @Override
    public void OnItemClick(int position) {
        dialogue.setPlainteid(modeeldeplainte.get(position).getId());
        Toast.makeText(getApplicationContext(), "PlaintId is "+ modeeldeplainte.get(position).getId(), Toast.LENGTH_LONG).show();
        Intent intent=new Intent(PlainteActivity.this,dialogue.class);
        startActivity(intent);

    }
    public void OnReturnToAdmin(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivityAdmin.class);
        startActivityForResult(intent, 0);
    }
}