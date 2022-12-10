package com.example.mealer.cuisinierclasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.recyclerviewclasses.RecyclerViewInterface;
import com.example.mealer.models.RepasModel;

public class MenuActivity extends AppCompatActivity implements RecyclerViewInterface {

    RepasModel repas;
    MenuModel menu = MenuModel.getInstance();
    String idCuisinier = Cuisinier.getInstance().id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        RecyclerView recyclerView= findViewById(R.id.RecyclerView);
        menu.ShowMenu(idCuisinier, recyclerView,this, this);
    }

    public void onClickAdd(View view){
        Intent intent = new Intent(getApplicationContext(), addRepas.class);
        startActivityForResult(intent, 0);
        finish();
    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
        startActivityForResult(intent, 0);
        finish();
    }

    @Override
    public void OnItemClick(int position) {
        RepasMenuActivity.position = position;
        Intent intent = new Intent(getApplicationContext(), RepasMenuActivity.class);
        startActivityForResult(intent, 0);
        finish();

    }
}
