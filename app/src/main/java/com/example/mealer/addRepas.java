package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class addRepas extends AppCompatActivity {

    String idCuisinier = Cuisinier.getInstance().id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repas);
    }

    public void onClickAdd(View view){

        MenuModel menu = MenuModel.getInstance();

        EditText mealname = findViewById(R.id.mealname);
        String name = mealname.getText().toString();

        EditText mealtype = findViewById(R.id.mealtype);
        String typeRepas = mealtype.getText().toString();

        EditText cuisinetype = findViewById(R.id.cuisinetype);
        String typeCuisine = cuisinetype.getText().toString();

        EditText ingredients = findViewById(R.id.ingredients);
        String ingredient = ingredients.getText().toString();

        EditText allergenes = findViewById(R.id.allergenes);
        String allergene = allergenes.getText().toString();

        EditText prix = findViewById(R.id.prix);
        Double price = Double.parseDouble(prix.getText().toString());

        EditText description = findViewById(R.id.description);
        String foodDescription = description.getText().toString();

        RepasModel repas = new RepasModel(name,typeRepas,typeCuisine,ingredient,allergene,price,foodDescription,idCuisinier,foodDescription);
        menu.addToMenu(idCuisinier, repas);

        Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
        startActivityForResult(intent, 0);
    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(intent, 0);
    }


}