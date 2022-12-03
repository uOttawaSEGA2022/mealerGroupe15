package com.example.mealer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ClientDescRepasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_desc_repas);
    }
    public void OnBackToRepasDuJourClient(View view){
        Intent intent =new Intent(getApplicationContext(),ClientRepasDuJourActivity.class);
        startActivityForResult(intent,0);
    }
}
