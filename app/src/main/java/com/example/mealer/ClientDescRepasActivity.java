package com.example.mealer;

//import static com.example.mealer.ClientRechercheActivity.repas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClientDescRepasActivity extends AppCompatActivity {
    ImageView plus,minus;
    TextView Quantites,nooom;
    String ParentRepas="";
    Client c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_desc_repas);
        Quantites=findViewById(R.id.Quantite);
        c = Client.getInstance();

        TextView typeeRepas = findViewById(R.id.textView57);
        TextView typeCuisinee = findViewById(R.id.textView59);
        TextView ingredientListe = findViewById(R.id.textView61);
        TextView allergenee = findViewById(R.id.textView63);
        TextView foodPricee = findViewById(R.id.textView65);
        TextView foo0dDescription = findViewById(R.id.textView67);

        nooom=findViewById(R.id.textView55);
        Bundle extra=getIntent().getExtras();
        String n= extra.getString("nomRepas");
        nooom.setText(n);
        typeeRepas.setText(extra.getString("TypeRepas"));
        typeCuisinee.setText(extra.getString("TypeCuisi"));
        ingredientListe.setText(extra.getString("Ingredients"));
        allergenee.setText(extra.getString("Allergene"));
        foodPricee.setText(""+(Double) extra.getDouble("Prix"));
        String idCUI= extra.getString("IDduCuisinier");


        foo0dDescription.setText(extra.getString("Description"));

    }

    public void OnBackToRepasDuJourClient(View view){
        Intent intent =new Intent(getApplicationContext(),ClientRechercheActivity.class);
        startActivityForResult(intent,0);
    }
    public void onclickPlus(View view){
        int nbr=Integer.parseInt((String) Quantites.getText());
        int a=nbr+1;
        Quantites.setText(""+a);
    }

    public void onclickMinus(View view){
        int nbr=Integer.parseInt((String) Quantites.getText());
        if((nbr-1)>=1){
            int a=nbr-1;
            Quantites.setText(""+a);
        }
    }
    public void onCommander(View view){
        Bundle extra=getIntent().getExtras();
        String idCUISi= extra.getString("IDduCuisinier");
        DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("Client/" + c.id + "/Commande/");
        //String key=myRef.ge
        String key  = myRef.push().getKey();

        // DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("Client/"+Client.getInstance().id+"/Commande");
        //myRef.child("Commande").child("commande1");
        //String key=myRef.push().getKey();




         String IdREPAS=extra.getString("RepasID");
         String nomCui=extra.getString("nomCuisinier");
        String nomREPAS= extra.getString("nomRepas");
        double lePrix=(Double) extra.getDouble("Prix");




        commandeModel Mycommand=new commandeModel( IdREPAS,  idCUISi,  nomCui,  nomREPAS,  lePrix,  0,  0,
         0);
        myRef.child(key).setValue(Mycommand);
        Intent intent =new Intent(getApplicationContext(),ClientEnvoieCommandeActivity.class);
        startActivityForResult(intent,0);
    }


}
