package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class popupSelect extends AppCompatActivity implements RecyclerViewInterface{

    MenuModel menu;
    RepasModel repas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_select);
        menu = MenuModel.getInstance();
        menu.ShowMenu(this, this);

    }

    public void onBack(View view){
        Intent intent = new Intent(getApplicationContext(), MenuDuJourActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onAdd(View view){
        if(repas != null){
            menu.addToRepasDujour(repas);
            Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
            startActivityForResult(intent, 0);
        }else{
            Toast.makeText(this, "Vous n'avez pas selectionnez de repas", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void OnItemClick(int position) {
       repas = menu.menuArray.get(position);
        Toast.makeText(this, "Vous avez choisis " + repas.getNom(), Toast.LENGTH_SHORT).show();

    }
}