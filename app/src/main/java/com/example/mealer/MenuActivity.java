package com.example.mealer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MenuActivity extends AppCompatActivity {

    MenuModel menu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menu = MenuModel.getInstance();
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RecyclerViewInterface repasRecycleView= findViewById(R.id.repasRecycleView);
        menu.ShowMenu(this.getApplicationContext(), repasRecycleView, this);
    }

    public void onClickAdd(View view){
        Intent intent = new Intent(getApplicationContext(), addRepas.class);
        startActivityForResult(intent, 0);
    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
        startActivityForResult(intent, 0);
    }
}
