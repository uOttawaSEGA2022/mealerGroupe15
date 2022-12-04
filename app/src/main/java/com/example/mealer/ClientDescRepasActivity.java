package com.example.mealer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ClientDescRepasActivity extends AppCompatActivity {
    ImageView plus,minus;
    TextView Quantites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_desc_repas);
        Quantites=findViewById(R.id.Quantite);
        /*plus=findViewById(R.id.btn_plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nbr=Integer.parseInt((String) Quantites.getText());
                Quantites.setText(nbr+1);

            }
        });
        minus=findViewById(R.id.btn_minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nbr=Integer.parseInt((String) Quantites.getText());
                Quantites.setText(nbr-1);

            }
        });*/


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
