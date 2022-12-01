package com.example.mealer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity implements RecyclerViewInterface{

    RepasModel repas;
    MenuModel menu = MenuModel.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menu.refresh();
        menu.ShowMenu(this, this);
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
