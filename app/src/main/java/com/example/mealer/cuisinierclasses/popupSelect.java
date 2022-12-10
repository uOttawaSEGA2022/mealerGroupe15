package com.example.mealer.cuisinierclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.recyclerviewclasses.RecyclerViewInterface;
import com.example.mealer.models.RepasModel;

public class popupSelect extends AppCompatActivity implements RecyclerViewInterface {

    MenuModel menu;
    RepasModel repas;
    String idCuisinier = Cuisinier.getInstance().id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_select);
        menu = MenuModel.getInstance();
        RecyclerView recyclerView= findViewById(R.id.RecyclerView);
        menu.ShowMenu(idCuisinier, recyclerView, this, this);

    }

    public void onBack(View view){
        Intent intent = new Intent(getApplicationContext(), MenuDuJourActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onAdd(View view){
        if(repas != null){
            menu.addToRepasDujour(idCuisinier, repas);
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