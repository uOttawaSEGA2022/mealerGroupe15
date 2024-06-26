package com.example.mealer.cuisinierclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mealer.R;
import com.example.mealer.models.MenuModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PopupTraiterDemandeActivity extends AppCompatActivity {
    String IdClient,IdDelaCommande, IdCuisinier;
    //commandeModel macommande;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_traiter_demande);
        TextView nomDuClient=findViewById(R.id.textView18);
        TextView nomREPAS=findViewById(R.id.textView16);
        TextView Quantite=findViewById(R.id.textView37);
        TextView HeureCeuillete=findViewById(R.id.textView35);
        Bundle extra=getIntent().getExtras();
        String n= extra.getString("nomduRepas");
        nomREPAS.setText(""+n);
        Quantite.setText(""+extra.getInt("Quantite"));
       HeureCeuillete.setText(""+extra.getString("HeureDeCueillete"));
       IdClient= extra.getString("IdClient");
        IdDelaCommande= String.valueOf(extra.getString("IdDeLaCommande"));

        nomDuClient.setText(""+extra.getString("nomduClient").toUpperCase());

        //Faire que quand on ouvre la page, ca affiche le nom du client, la quantite, et l'heure de cueillete
        //Ceci veut dire qu'il faut que le client entre ces infos quand il commande
        //Heure de cueillete doit etre en time format et le client peut selectionner l'heure

    }

    public void OnRejeter(View view){
        DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("Client/"+IdClient+"/Commande/"+IdDelaCommande);
        myRef.child("statutDeLaCommande").setValue(-1);

        DatabaseReference demandeRef = FirebaseDatabase.getInstance().getReference("Cuisinier/"+IdCuisinier+"/Demandes/"+IdDelaCommande);
        demandeRef.removeValue();
        Intent intent = new Intent(getApplicationContext(), CuisinierVosDemandesActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnApprouver(View view){
       // Toast.makeText(this, , Toast.LENGTH_SHORT).show();
        DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("Client/"+IdClient+"/Commande/"+IdDelaCommande);
        myRef.child("statutDeLaCommande").setValue(1);

        DatabaseReference demandeRef = FirebaseDatabase.getInstance().getReference("Cuisinier/"+IdCuisinier+"/Demandes/"+IdDelaCommande);
        demandeRef.removeValue();
        Intent intent = new Intent(getApplicationContext(), CuisinierVosDemandesActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnBack(View view){
        Intent intent = new Intent(getApplicationContext(), CuisinierVosDemandesActivity.class);
        startActivityForResult(intent, 0);
    }
}