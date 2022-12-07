package com.example.mealer;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientVotreCommandeActivity extends AppCompatActivity {

    MenuModel commandes;
    commandeModel c;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_votre_commande);
        Bundle intentExtras = getIntent().getExtras();
        pos = intentExtras.getInt("position");
        commandes = MenuModel.getInstance();
        c = commandes.commandeArray.get(pos);

        TextView status = findViewById(R.id.StatutCommande);
        status.setText(c.getStatutDeLaCommande());


    }

    public void OnBackToMesRepas(View view) {
        Intent intent =new Intent(getApplicationContext(),ClientMesRepasActivity.class);
        startActivityForResult(intent, 0);

    }

    public void onSoumettrePlainte(View view) {
        Intent intent =new Intent(getApplicationContext(),ClientPlainteActivity.class);
        intent.putExtra("position", pos);
        startActivityForResult(intent, 0);


    }


    public void onGotoDialogu(View view) {
        RateDialogu rateDialogu=new RateDialogu(ClientVotreCommandeActivity.this, c);
        rateDialogu.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        rateDialogu.setCancelable(true);
        rateDialogu.show();

        //Intent intent =new Intent(getApplicationContext(),RateDialogu.class);
        //startActivityForResult(intent, 0);

    }
}