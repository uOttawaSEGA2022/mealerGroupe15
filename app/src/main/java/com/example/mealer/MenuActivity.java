package com.example.mealer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MenuActivity extends AppCompatActivity implements RecyclerViewInterface{

    MenuModel menu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menu = MenuModel.getInstance();
        menu.ShowMenu(this, this);
    }

    public void onClickAdd(View view){
        Intent intent = new Intent(getApplicationContext(), addRepas.class);
        startActivityForResult(intent, 0);
    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), RepasMenuActivity.class);
        startActivityForResult(intent, 0);

    }
}
