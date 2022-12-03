package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class SignUpClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_client);
    }
    public void OnClickReturnEntantque(View view) {
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        startActivityForResult(intent, 0);
    }
    public void OnClickinscrireClient(View view) {
        //quand on clique sur s'inscrire, on store les données dans des variables pour permettre la reconnection

        EditText emailBox = findViewById(R.id.editTextTextEmailAddress);
        String email = emailBox.getText().toString();

        EditText passwordBox = findViewById(R.id.editTextTextPassword2);
        String password = passwordBox.getText().toString();

        EditText nameBox = findViewById(R.id.FirstNameField);
        String usr = nameBox.getText().toString().toLowerCase();

       EditText lastNameBox =  findViewById(R.id.LastNameField);
        String lastName = lastNameBox.getText().toString().toLowerCase();

        TextView adressEditText = findViewById(R.id.editTextTextPostalAddress);
        String adress = adressEditText.getText().toString();

        TextView cardTextView = findViewById(R.id.editTextNumber);
        String cardNumber = cardTextView.getText().toString();

        TextView incTextView = findViewById(R.id.incompleteInfoText);


        //On crée une nouvelle instance client
        Client c = Client.getInstance();

        //On vérifie que aucun champs n'es null, soit que l'utilisateur a bien rentrer quelque chose dans tout les champs
        if(email.isEmpty() || email == null ||
        password.isEmpty() || password == null ||
        usr.isEmpty() || usr == null ||
        lastName.isEmpty() || lastName == null ||
                adress.isEmpty() || adress == null ||
                cardNumber.isEmpty() || cardNumber == null){
            // sinon on affiche ce message dans incTextView qui est un TextView au juste
            // en dessous du grand Sign up label
            incTextView.setText("Veuillez entrez toutes les informations s'il vous plait!");
        }else{
            // Si tout est respecter on peut entamer la création du compte
            c.signUp(email, password, usr, lastName, adress, cardNumber);
            Intent intent = new Intent(getApplicationContext(), MainActivityClient.class);
            startActivityForResult(intent, 0);
            finish();
        }

    }



}