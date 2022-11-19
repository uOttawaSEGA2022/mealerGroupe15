package com.example.mealer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
