package com.example.mealer.cuisinierclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.models.RepasModel;
import com.google.firebase.database.FirebaseDatabase;

public class addRepas extends AppCompatActivity {

    String idCuisinier = Cuisinier.getInstance().id;
    Cuisinier cuii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repas);
        cuii=Cuisinier.getInstance();
    }

    public void onClickAdd(View view) {

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
        Double price = null;
        if (prix.getText() != null && !prix.getText().toString().isEmpty()){
            price = Double.parseDouble(prix.getText().toString());
        }

        EditText description = findViewById(R.id.description);
        String foodDescription = description.getText().toString();
        //TextView nooooom=findViewById(R.id.NomCuisinierMenu);
        //nooooom.setText(""+cuii.firstName);


        TextView incTextView2 = findViewById(R.id.incTextView2);

        if (name == null || name.isEmpty() ||
                typeRepas == null || typeRepas.isEmpty() ||
                typeCuisine == null || typeCuisine.isEmpty() ||
                ingredient == null  || ingredient.isEmpty() ||
                allergene == null || allergene.isEmpty() ||
                price == null || price.isNaN() ||
                foodDescription == null || foodDescription.isEmpty()){

            incTextView2.setText("Veuillez entrer toutes les informations correctement s'il vous plait!");
        }

        RepasModel repas = new RepasModel(name,typeRepas,typeCuisine,ingredient,allergene,idCuisinier,foodDescription,cuii.getFirstName(), false, price, 0);
        menu.addToMenu(idCuisinier, repas);

        Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
        startActivityForResult(intent, 0);
    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(intent, 0);
    }


}