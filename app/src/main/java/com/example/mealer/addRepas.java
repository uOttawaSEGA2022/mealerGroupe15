package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class addRepas extends AppCompatActivity {

    String idCuisinier = Cuisinier.getInstance().id;
    //Cuisinier cuii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repas);
        //cuii=Cuisinier.getInstance();
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
        //TextView nooooom=findViewById(R.id.NomCuisinierMenu);
        //nooooom.setText(""+cuii.firstName);


        TextView incTextView2 = findViewById(R.id.incTextView2);

        if (name.isEmpty() || name == null ||
                typeRepas.isEmpty() || typeRepas == null ||
                typeCuisine.isEmpty() || typeCuisine == null ||
                ingredient.isEmpty() || ingredient == null ||
                allergene.isEmpty() || allergene == null ||
                price.isNaN() || price == null ||
                foodDescription.isEmpty() || foodDescription == null){

            incTextView2.setText("Veuillez entrer toutes les informations correctement s'il vous plait!");
        }

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