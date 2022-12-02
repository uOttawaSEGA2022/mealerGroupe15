package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientMesRepasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_mes_repas);
    }

    public void OnBackToMainActivityClient(View view) {
        Intent intent =new Intent(getApplicationContext(),MainActivityClient.class);
        startActivityForResult(intent,0);
    }
}