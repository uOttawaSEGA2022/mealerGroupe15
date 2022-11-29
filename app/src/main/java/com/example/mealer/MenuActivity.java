package com.example.mealer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MenuActivity extends AppCompatActivity implements RecyclerViewInterface{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenuModel.getInstance().ShowMenu(this, this);
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
        RepasMenuActivity.position = position;
        Intent intent = new Intent(getApplicationContext(), RepasMenuActivity.class);
        startActivityForResult(intent, 0);

    }
}
