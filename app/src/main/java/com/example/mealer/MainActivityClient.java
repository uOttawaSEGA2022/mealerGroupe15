package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

//import com.example.mealer.databinding.RecyclerviewRowBinding;

public class MainActivityClient extends AppCompatActivity implements RecyclerViewInterface{

    Client client;
    MenuModel menu;
    RecyclerView r;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);
        client = Client.getInstance();
        menu = MenuModel.getInstance();
        r = (RecyclerView) findViewById(R.id.allRepasDujour) ;
        //menu.showAllMenuDuJour(r,this, this );

    }

    public void OnClickRecherche (View view){
        Intent intent = new Intent(getApplicationContext(), ClientRechercheActivity.class);
        startActivityForResult(intent, 0);
        finish();
    }

    public void OnMesCommandes (View view){
        Intent intent=new Intent(getApplicationContext(),ClientMesRepasActivity.class);
        //Intent intent=new Intent(getApplicationContext(),ClientVotreCommandeActivity.class);

        startActivityForResult(intent,0);

    }

    public void OnDeconnecter(View view) {
        client.disconnect();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }


    @Override
    public void OnItemClick(int position) {

    }
}