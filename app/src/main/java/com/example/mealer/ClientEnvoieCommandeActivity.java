package com.example.mealer;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientEnvoieCommandeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_envoie_commande);
    }

    public void onBackToMainClient(View view) {
        Intent intent=new Intent(getApplicationContext(),MainActivityClient.class);
        startActivityForResult(intent,0);
    }
}