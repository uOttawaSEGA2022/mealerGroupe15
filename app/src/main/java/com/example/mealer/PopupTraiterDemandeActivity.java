package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class PopupTraiterDemandeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_traiter_demande);

        //Faire que quand on ouvre la page, ca affiche le nom du client, la quantite, et l'heure de cueillete
        //Ceci veut dire qu'il faut que le client entre ces infos quand il commande
        //Heure de cueillete doit etre en time format et le client peut selectionner l'heure

    }

    public void OnRejeter(View view){
        Intent intent = new Intent(getApplicationContext(), CuisinierVosDemandesActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnApprouver(View view){
        Intent intent = new Intent(getApplicationContext(), CuisinierVosDemandesActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnBack(View view){
        Intent intent = new Intent(getApplicationContext(), CuisinierVosDemandesActivity.class);
        startActivityForResult(intent, 0);
    }
}