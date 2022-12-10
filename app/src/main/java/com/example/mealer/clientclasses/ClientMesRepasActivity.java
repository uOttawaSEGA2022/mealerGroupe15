package com.example.mealer.clientclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.recyclerviewclasses.RecyclerViewInterface;

public class ClientMesRepasActivity extends AppCompatActivity implements RecyclerViewInterface {

    MenuModel commande = MenuModel.getInstance();
    String idClient = Client.getInstance().id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_mes_repas);
        commande.showCommande(idClient, findViewById(R.id.commandeRecyclerView), this, this);
    }

    public void OnBackToMainActivityClient(View view) {
        Intent intent =new Intent(getApplicationContext(), MainActivityClient.class);
        startActivityForResult(intent,0);
    }

    @Override
    public void OnItemClick(int position) {
        Intent i = new Intent(ClientMesRepasActivity.this, ClientVotreCommandeActivity.class);
        i.putExtra("position", position);
        startActivityForResult(i, 0);

    }
}