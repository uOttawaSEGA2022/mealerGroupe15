package com.example.mealer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RepasMenuActivity extends AppCompatActivity{

    RepasModel repas = new RepasModel();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas_menu);

        EditText foodName = findViewById(R.id.foodName);
        foodName.setText(repas.getNom());

        EditText typeRepas = findViewById(R.id.foodType);
        typeRepas.setText(repas.getTypeDeRepas());

        EditText typeCuisine = findViewById(R.id.cookingType);
        typeCuisine.setText(repas.getTypeDeCuisine());

        EditText ingredientList = findViewById(R.id.ingredientsList);
        ingredientList.setText(repas.getIngredients());

        EditText allergene = findViewById(R.id.allergenes);
        allergene.setText(repas.getAllergenes());

        EditText foodPrice = findViewById(R.id.foodPrice);
        foodPrice.setText(String.valueOf(repas.getPrix()));

        EditText foodDescription = findViewById(R.id.foodDescr);
        foodDescription.setText(repas.getDescription());

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
