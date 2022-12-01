package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientRechercheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_recherche);
    }

    public void OnBack (View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityClient.class);
        startActivityForResult(intent, 0);
        finish();
    }
    
    public void OnSearch (View view){
        Intent intent = new Intent(getApplicationContext(), ClientRechercheFiltreActivity.class);
        startActivityForResult(intent, 0);
        finish();
    }

}