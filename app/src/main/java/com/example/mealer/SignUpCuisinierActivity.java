package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpCuisinierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void OnClickReturnEntantque(View view) {
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        startActivityForResult(intent, 0);
    }
    public void OnClickinscrire(View view) {

        EditText fNameBox = findViewById(R.id.FirstNameField);
        String firstName = fNameBox.getText().toString();

        EditText lNameBox = findViewById(R.id.LastNameField);
        String lastName = lNameBox.getText().toString();

        EditText addressBox = findViewById(R.id.editTextTextPostalAddress);
        String address = addressBox.getText().toString();

        EditText emailBox = findViewById(R.id.editTextTextEmailAddress);
        String email = emailBox.getText().toString();

        EditText passwordBox = findViewById(R.id.editTextTextPassword2);
        String password = passwordBox.getText().toString();

        EditText descriptionBox = findViewById(R.id.editTextTextPersonName);
        String description = descriptionBox.getText().toString();

        TextView incTextView = findViewById(R.id.incTextView);

        //On cherche l'instance de cuisinier
        Cuisinier c = Cuisinier.getInstance();

        //On vérifie que aucun champs n'es null, soit que l'utilisateur a bien rentrer quelque chose dans tout les champs
        if(email.isEmpty() || email == null ||
                password.isEmpty() || password == null ||
                firstName.isEmpty() || firstName == null ||
                lastName.isEmpty() || lastName == null ||
                address.isEmpty() || address == null ||
                description.isEmpty() || description == null){
            // sinon on affiche ce message dans incTextView qui est un TextView au juste
            // en dessous du grand Sign up label
            incTextView.setText("Veuillez entrez toutes les informations s'il vous plait!");
        }else{
            // Si tout est respecter on peut entamer la création du compte
            c.signUp(email, password, firstName, lastName, address, description);
            Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
            startActivityForResult(intent, 0);
            finish();
        }
    }

    }