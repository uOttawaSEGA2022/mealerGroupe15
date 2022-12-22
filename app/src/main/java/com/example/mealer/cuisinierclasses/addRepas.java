package com.example.mealer.cuisinierclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.models.RepasModel;
import com.google.firebase.database.FirebaseDatabase;

public class addRepas extends AppCompatActivity {

    String idCuisinier = Cuisinier.getInstance().id;
    Cuisinier cuii;
    String drawableName;
    //ImageView tooof ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repas);
        cuii=Cuisinier.getInstance();

    }
    public void OnOpenPhoto(View view) {

    Intent intent = new Intent(getApplicationContext(), SelectPhotoActivity.class);
    startActivityForResult(intent, 0);}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //tooof = (ImageView) findViewById(R.id.BackGroundModel);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        //Getting the Avatar Image we Show to our users
        ImageView avatarImage = (ImageView) findViewById(R.id.imageView8);
        //Figuring out the correct image
        drawableName = "cafe";
        switch (data.getIntExtra("imageID", R.id.cafe)) {
            case R.id.cafe:
                drawableName = "cafe";
                break;
            case R.id.burgerhori:
                drawableName = "burgerhori";

                break;
            case R.id.cocahori:
                drawableName = "cocahori";
                break;
            case R.id.rizfrit:
                drawableName = "rizfrit";
                break;
            case R.id.dejeuner:
                drawableName = "dejeuner";
                break;
            case R.id.pizzahori:
                drawableName = "pizzahori";
                break;
            default:
                drawableName = "cafe";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
        avatarImage.setImageResource(resID);
        //tooof.setImageResource(resID);
    }
    //android:background="#C9FB8C00"
    public void onClickAdd(View view) {

        MenuModel menu = MenuModel.getInstance();


        EditText mealname = findViewById(R.id.mealname);
        String name = mealname.getText().toString().toUpperCase();

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
        /*
        TextView nooooom=findViewById(R.id.NomCuisinierMenu);
        nooooom.setText(""+cuii.firstName);
        */


        TextView incTextView2 = findViewById(R.id.incTextView2);

        if (name == null || name.isEmpty() ||
                typeRepas == null || typeRepas.isEmpty() ||
                typeCuisine == null || typeCuisine.isEmpty() ||
                ingredient == null  || ingredient.isEmpty() ||
                allergene == null || allergene.isEmpty() ||
                price == null || price.isNaN() ||
                foodDescription == null || foodDescription.isEmpty()){

            incTextView2.setText("Veuillez entrer toutes les informations correctement s'il vous plait! ");
        }
        else {
        RepasModel repas = new RepasModel(name,typeRepas,typeCuisine,ingredient,allergene,idCuisinier,foodDescription,cuii.getFirstName(), false, price, 0,cuii.adresse,drawableName);
        menu.addToMenu(idCuisinier, repas);

        Intent intent = new Intent(addRepas.this, MainActivityCuisinier.class);
        intent.putExtra("DrawableNAME",drawableName);
        startActivityForResult(intent, 0);}
    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(intent, 0);
    }



}