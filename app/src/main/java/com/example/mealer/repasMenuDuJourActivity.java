package com.example.mealer;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class repasMenuDuJourActivity extends AppCompatActivity {

    RepasModel repas = MenuDuJourActivity.getRepas();
    MenuModel menu = MenuModel.getInstance();
    String idCuisinier = MainActivity.cuisinier.id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas_menu_du_jour);

        TextView foodName = findViewById(R.id.textView38);
        TextView typeRepas = findViewById(R.id.textView39);
        TextView typeCuisine = findViewById(R.id.textView40);
        TextView ingredientList = findViewById(R.id.textView41);
        TextView allergene = findViewById(R.id.textView42);
        TextView foodPrice = findViewById(R.id.textView43);
        TextView foodDescription = findViewById(R.id.textView44);

        foodName.setText(repas.getNom());
        typeRepas.setText(repas.getTypeDeRepas());
        typeCuisine.setText(repas.getTypeDeCuisine());
        ingredientList.setText(repas.getIngredients());
        allergene.setText(repas.getAllergenes());
        foodPrice.setText(String.valueOf(repas.getPrix()));
        foodDescription.setText(repas.getDescription());

    }

    public void onBack(View view){
        Intent intent = new Intent(getApplicationContext(), MenuDuJourActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onDelete(View view){
        if(repas != null){
            menu.deleteFromMenuDuJour(idCuisinier, repas);
            Intent intent = new Intent(getApplicationContext(), MenuDuJourActivity.class);
            startActivityForResult(intent, 0);

        }else{
            Toast.makeText(this, "Vous n'avez pas selectionnez de repas", Toast.LENGTH_SHORT).show();
        }

    }
}