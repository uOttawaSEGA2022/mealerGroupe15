package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class addRepas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repas);
    }

    public void onClickAdd(View view){

        MenuModel menu = MenuModel.getInstance();
        RepasModel repas = new RepasModel();
        menu.addToMenu(repas);

        EditText mealname = findViewById(R.id.mealname);
        String Name = mealname.getText().toString();

        EditText mealtype = findViewById(R.id.mealtype);
        String TypeRepas = mealtype.getText().toString();

        EditText cuisinetype = findViewById(R.id.cuisinetype);
        String TypeCuisine = cuisinetype.getText().toString();

        EditText ingredients = findViewById(R.id.ingredients);
        String Ingredients = ingredients.getText().toString();

        EditText allergenes = findViewById(R.id.allergenes);
        String Allergenes = allergenes.getText().toString();

        EditText prix = findViewById(R.id.prix);
        String Prix = prix.getText().toString();

        EditText description = findViewById(R.id.description);
        String Description = description.getText().toString();

        //creer des variables pour stocker l'entree des utilisateurs dans addrepas
        //ajouter ces references dans la database
        //retourner dans la page de menuactivity

        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(intent, 0);
    }


}