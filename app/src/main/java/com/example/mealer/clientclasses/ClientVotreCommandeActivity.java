package com.example.mealer.clientclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.models.commandeModel;

public class ClientVotreCommandeActivity extends AppCompatActivity {

    MenuModel commandes;
    commandeModel c;
  String b;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_votre_commande);
        Bundle intentExtras = getIntent().getExtras();
        pos = intentExtras.getInt("position");
        commandes = MenuModel.getInstance();
        c = commandes.commandeArray.get(pos);
        b=String.valueOf(c.getStatutDeLaCommande());
        //Toast.makeText(this, b, Toast.LENGTH_SHORT).show();

        TextView status = findViewById(R.id.StatutCommande);
        String a="";
        int aa= c.getStatutDeLaCommande();
        if (aa==-1){
            status.setText("Désolé votre commande a été rejeté");
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


    public void onGotoDialogu(View view) {
        RateDialogu rateDialogu=new RateDialogu(ClientVotreCommandeActivity.this, c);
        rateDialogu.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        rateDialogu.setCancelable(true);
        rateDialogu.show();

        //Intent intent =new Intent(getApplicationContext(),RateDialogu.class);
        //startActivityForResult(intent, 0);

    }
}