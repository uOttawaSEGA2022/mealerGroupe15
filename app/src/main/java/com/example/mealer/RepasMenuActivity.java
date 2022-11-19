package com.example.mealer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RepasMenuActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas_menu);
    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickSave(View view){

    }

    public void onClickDelete(View view){

    }

}
