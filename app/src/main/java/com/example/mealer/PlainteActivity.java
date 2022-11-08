package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;

public class PlainteActivity extends AppCompatActivity {
    ArrayList<PlainteModel> modeeldeplainte=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plainte);
        RecyclerView recyclerView=findViewById(R.id.myRecycleView);
        setupModeeldeplainte();
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,modeeldeplainte);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setupModeeldeplainte(){
        String [] ClientNamess= getResources().getStringArray(R.array.Full_listplainte_nomClient);
        String [] CuisiniersNames= getResources().getStringArray(R.array.Full_listplainte_nomCuisinier);
        String [] Descrip= getResources().getStringArray(R.array.Full_listplainte_Desc);
        for (int i=0; i<ClientNamess.length;i++){
            modeeldeplainte.add(new PlainteModel(ClientNamess[i],CuisiniersNames[i],Descrip[i]));

        }
    }

}