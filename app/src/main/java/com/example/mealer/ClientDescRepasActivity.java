package com.example.mealer;

//import static com.example.mealer.ClientRechercheActivity.repas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ClientDescRepasActivity extends AppCompatActivity {
    ImageView plus,minus;
    TextView Quantites,nooom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_desc_repas);
        Quantites=findViewById(R.id.Quantite);

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
        Intent intent =new Intent(getApplicationContext(),ClientEnvoieCommandeActivity.class);
        startActivityForResult(intent,0);
    }


}
