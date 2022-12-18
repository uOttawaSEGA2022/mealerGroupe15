package com.example.mealer.clientclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.models.commandeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ClientVotreCommandeActivity extends AppCompatActivity {

    MenuModel commandes;
    String AddresseCuisinier="aa";
    commandeModel c;
  String b;
    static int pos;
    TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_votre_commande);
        commandes = MenuModel.getInstance();
        c = commandes.commandeArray.get(pos);
        b=String.valueOf(c.getStatutDeLaCommande());
        //Toast.makeText(this, b, Toast.LENGTH_SHORT).show();

         status = findViewById(R.id.StatutCommande);
        String a="";
        int aa= c.getStatutDeLaCommande();
        if (aa==-1){
            status.setText("Désolé votre commande a été rejetée");
        }
        else if(aa==0){
            status.setText("En attente d'une réponse du cuisinier");
        }
        else if(aa==1){
            status.setText("Le cuisinier a accepté votre commande.Vous pouvez aller le recupérer");
        }
       /* switch (aa) {
            case (-1):
                a= "Désolé votre commande a été rejeté.";
                status.setText(a);
            case(0):
                a="En attente d'une réponse du cuisinier";
                status.setText(a);
            case(1):
                a="Le cuisinier a accepté votre commande.Vous pouvez aller le recupérer";
                status.setText(a);
            case(2):
                a="Votre commande est prête";
                status.setText(a);
            default:
                a="le cuisinier est entrain de préparer votre commande";
                status.setText(a);
        }*/

        /*DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("Client/"+Client.getInstance().id+"/Commande/"+c.idDeLaCommande+"/statutDeLaCommande");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                b= (String) snapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/





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

    public static void setPos(int position){
        pos = position;
    }


    public void onGotoDialogu(View view) {
        if(status.getText().equals("Désolé votre commande a été rejetée")){
            Toast.makeText(this, "Vous ne pouvez pas evaluer car votre commande est rejétée", Toast.LENGTH_SHORT).show();
        }
        else if (status.getText().equals("En attente d'une réponse du cuisinier")){
            Toast.makeText(this, "Votre commande doit être acceptée pour pouvoir l'evaluer", Toast.LENGTH_SHORT).show();

        }
        else{
        RateDialogu rateDialogu=new RateDialogu(ClientVotreCommandeActivity.this, c);
        rateDialogu.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        rateDialogu.setCancelable(true);
        rateDialogu.show();}

        //Intent intent =new Intent(getApplicationContext(),RateDialogu.class);
        //startActivityForResult(intent, 0);

    }
    public void OnOpenInGoogleMaps(View view) {
     //DatabaseReference myref= FirebaseDatabase.getInstance().getReference("Cuisinier/"+c.getIdDuCuisinier());

        //AddresseCuisinier=myref.child("adresse").
       /* if(snapshot.hasChild("Rate")) {
            DataSnapshot rateSnapshot = snapshot.child("Rate");
            for (DataSnapshot rates : rateSnapshot.getChildren()) {
                if (rates.child("IdRepas").exists() && rates.child("IdRepas").getValue().toString() == c.getIdDuRepas() &&
                        rates.child("idDuCuisinier").exists() && rates.child("idDuCuisinier").getValue().toString() == c.getIdDuCuisinier()) {
                    realAverage = realAverage + Double.parseDouble(rates.child("RateValue").getValue().toString());
                    i += 1;
                }
            }*/

        /*myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("adresse")){

                    AddresseCuisinier=(String) snapshot.child("adresse").getValue();


                }


                        //AddresseCuisinier= snapshot.getValue().toString();
                        //Toast.makeText(this, ""+snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        //Toast.makeText(this, ""+c.getIdDuCuisinier(), Toast.LENGTH_SHORT).show();
        //Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q="+commandes.commandeArray.get(pos).getCuisinierAdresse());
        //Create an Intent from emmIntentUri. Set the action to ACTION_VIEW
        Intent MainActivityMap = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Make the Intent explicit by setting the Google Maps package
        MainActivityMap.setPackage("com.google.android.apps.maps");
        // Altempt to start an activity that can handle the Intent
        startActivity(MainActivityMap);
    }
}