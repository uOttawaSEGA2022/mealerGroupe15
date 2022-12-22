package com.example.mealer.clientclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.models.commandeModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ClientPlainteActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Plainte");

    int pos;
    MenuModel commandes;
    commandeModel c;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_plainte);
        Bundle intentExtras = getIntent().getExtras();
        pos = intentExtras.getInt("position");
        commandes = MenuModel.getInstance();
        c = commandes.commandeArray.get(pos);
        client = Client.getInstance();
    }

    public void OnClickEnvoyerPlainte(View view) {

        EditText descriptionTextView = findViewById(R.id.editTextTextPersonName3);
        String description=descriptionTextView.getText().toString();
        EditText titreTextView = findViewById(R.id.editTextTextPersonName2);
        String titre = titreTextView.getText().toString();
        String key=c.getIdDeLaCommande();
        TextView incTextView3 = findViewById(R.id.incTextView3);


        if (titre.isEmpty() || titre == null || description.isEmpty() || description == null){

            incTextView3.setText("Veuillez entrer toutes les informations correctement s'il vous plait!");
        }

        myRef = database.getReference("Plainte/"+ key);
        myRef.child("titre").setValue(titre);
        myRef.child("description").setValue(description);
        myRef.child("id").setValue(c.getIdDeLaCommande());

        myRef.child("nameOfClient").setValue(client.getFirstName());
        myRef.child("nameOfCuisinier").setValue(c.getNomDuCuisinier());
        myRef.child("idDuCuisinier").setValue(c.getIdDuCuisinier());
        Toast.makeText(this, "Plainte envoyée! ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ClientMesRepasActivity.class);
        startActivityForResult(intent, 0);
    }
    public void OnBackToVotreCommande(View view) {


        Intent intent = new Intent(getApplicationContext(), ClientVotreCommandeActivity.class);
        startActivityForResult(intent, 0);

    }



}