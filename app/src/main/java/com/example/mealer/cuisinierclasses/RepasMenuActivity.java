package com.example.mealer.cuisinierclasses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.models.RepasModel;

public class RepasMenuActivity extends AppCompatActivity{

    public static int position = 0;
    Cuisinier cui;

    RepasModel repas = new RepasModel();
    MenuModel menu = MenuModel.getInstance();
    String idCuisinier = Cuisinier.getInstance().id;

    EditText foodName;
    EditText typeRepas;
    EditText typeCuisine;
    EditText ingredientList;
    EditText allergene;
    EditText foodPrice;
    EditText foodDescription;
    //TextView  NOMCUISI;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas_menu);
        cui=Cuisinier.getInstance();


        foodName = findViewById(R.id.foodName);
        typeRepas = findViewById(R.id.foodType);
        typeCuisine = findViewById(R.id.cookingType);
        ingredientList = findViewById(R.id.ingredientsList);
        allergene = findViewById(R.id.allergenes);
        foodPrice = findViewById(R.id.foodPrice);
        foodDescription = findViewById(R.id.foodDescr);
        //NOMCUISI=findViewById(R.id.NomCuisinierMenu);



        repas = menu.menuArray.get(position);


        foodName.setText(repas.getNom());
        //NOMCUISI.setText(""+cui.firstName);
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

        RepasModel nouveauRepas = new RepasModel(nomRepas,foodType,cookingType,listeIngredients,allergenes,idCuisinier,descrRepas,Cuisinier.getInstance().getFirstName(), repas.isInRepasDuJour(), prixRepas, repas.getRate(),cui.adresse);
        menu.save(idCuisinier, repas, nouveauRepas, this);
    }

    public void onClickDelete(View view){
        menu.deleteFromMenu(idCuisinier, repas, this);
    }

}
