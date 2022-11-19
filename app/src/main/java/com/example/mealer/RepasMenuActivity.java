package com.example.mealer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RepasMenuActivity extends AppCompatActivity{

    RepasModel repas = new RepasModel();
    MenuModel menu = MenuModel.getInstance();

    EditText foodName = findViewById(R.id.foodName);
    EditText typeRepas = findViewById(R.id.foodType);
    EditText typeCuisine = findViewById(R.id.cookingType);
    EditText ingredientList = findViewById(R.id.ingredientsList);
    EditText allergene = findViewById(R.id.allergenes);
    EditText foodPrice = findViewById(R.id.foodPrice);
    EditText foodDescription = findViewById(R.id.foodDescr);


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas_menu);

        foodName.setText(repas.getNom());

        typeRepas.setText(repas.getTypeDeRepas());

        typeCuisine.setText(repas.getTypeDeCuisine());

        ingredientList.setText(repas.getIngredients());

        allergene.setText(repas.getAllergenes());

        foodPrice.setText(String.valueOf(repas.getPrix()));

        foodDescription.setText(repas.getDescription());

    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickSave(View view){
        String nomRepas = foodName.getText().toString();
        String foodType = typeRepas.getText().toString();
        String cookingType = typeCuisine.getText().toString();
        String listeIngredients = ingredientList.getText().toString();
        String allergenes = allergene.getText().toString();
        Double prixRepas = Double.parseDouble(foodPrice.getText().toString());
        String descrRepas = foodDescription.getText().toString();

        RepasModel repas = new RepasModel(nomRepas,foodType,cookingType,listeIngredients,allergenes,prixRepas,descrRepas);

        menu.addToMenu(repas);
    }

    public void onClickDelete(View view){
        menu.deleteFromMenu(repas);
    }

}
