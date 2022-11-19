package com.example.mealer;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class repasMenuDuJourActivity extends AppCompatActivity {

    RepasModel repas;
    MenuModel menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas_menu_du_jour);
        menu = MenuModel.getInstance();
        repas = MenuDuJourActivity.getRepas();

    }

    public void onBack(View view){
        Intent intent = new Intent(getApplicationContext(), MenuDuJourActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onDelelte(View view){
        if(repas != null){
            menu.deleteFromMenuDuJour(repas);
            Intent intent = new Intent(getApplicationContext(), MenuDuJourActivity.class);
            startActivityForResult(intent, 0);

        }else{
            Toast.makeText(this, "Vous n'avez pas selectionnez de repas", Toast.LENGTH_SHORT).show();
        }

    }
}