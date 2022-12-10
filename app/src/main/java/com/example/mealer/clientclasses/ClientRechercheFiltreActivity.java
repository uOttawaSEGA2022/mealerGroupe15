package com.example.mealer.clientclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mealer.R;

public class ClientRechercheFiltreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_recherche_filtre);
    }

    public void OnClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), ClientRechercheActivity.class);
        startActivityForResult(intent, 0);
        finish();
    }

}