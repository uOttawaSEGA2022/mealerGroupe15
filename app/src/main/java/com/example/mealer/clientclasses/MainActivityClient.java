package com.example.mealer.clientclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mealer.MainActivity;
import com.example.mealer.R;
import com.example.mealer.recyclerviewclasses.RecyclerViewInterface;

//import com.example.mealer.databinding.RecyclerviewRowBinding;

public class MainActivityClient extends AppCompatActivity implements RecyclerViewInterface {

    Client client;
    RecyclerView r;
    TextView welcomeText2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);
        client = Client.getInstance();
        welcomeText2=findViewById(R.id.welcomText2);
        welcomeText2.setText("Bienvenue "+client.firstName.toUpperCase()+" , vous êtes connectés en tant que client");


    }

    public void OnClickRecherche (View view){
        Intent intent = new Intent(getApplicationContext(), ClientRechercheActivity.class);
        startActivityForResult(intent, 0);
        finish();
    }

    public void OnMesCommandes (View view){
       Intent intent=new Intent(getApplicationContext(), ClientMesRepasActivity.class);
       // Intent intent=new Intent(getApplicationContext(),ClientVotreCommandeActivity.class);

        startActivityForResult(intent,0);
        finish();

    }

    public void OnDeconnecter(View view) {
        client.disconnect();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
        finish();
    }


    @Override
    public void OnItemClick(int position) {

    }
}