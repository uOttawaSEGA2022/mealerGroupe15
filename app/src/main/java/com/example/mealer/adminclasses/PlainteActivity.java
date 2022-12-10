package com.example.mealer.adminclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import android.os.Bundle;

import com.example.mealer.models.PlainteModel;
import com.example.mealer.R;
import com.example.mealer.recyclerviewclasses.RecyclerViewAdapter;
import com.example.mealer.recyclerviewclasses.RecyclerViewInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class PlainteActivity extends AppCompatActivity implements RecyclerViewInterface {
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
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView=findViewById(R.id.RecyclerView);
        databaseReference= FirebaseDatabase.getInstance().getReference("Plainte");
        //setupModeeldeplainte();
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,modeeldeplainte,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren( )){
                    PlainteModel p=dataSnapshot.getValue(PlainteModel.class);
                    if(!modeeldeplainte.contains(p)){
                        modeeldeplainte.add(p);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent=new Intent(PlainteActivity.this, dialogue.class);
        intent.putExtra("idCuisinier", modeeldeplainte.get(position).getIdCuisinier());
        intent.putExtra("id", modeeldeplainte.get(position).getId());
        startActivity(intent);

    }
    public void OnReturnToAdmin(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivityAdmin.class);
        startActivityForResult(intent, 0);
    }
}