package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
    public void OnClickinscrire(View view) {
        //quand on clique sur s'inscrire, on store les données dans des variables pour permettre la reconnection

        TextInputEditText fNameBox = findViewById(R.id.FirstNameField);
        String firstName = fNameBox.getText().toString();

        TextInputEditText lNameBox = findViewById(R.id.LastNameField);
        String lastName = lNameBox.getText().toString();

        EditText emailBox = findViewById(R.id.editTextTextEmailAddress);
        String email = emailBox.getText().toString();

        EditText passwordBox = findViewById(R.id.editTextTextPassword2);
        String password = passwordBox.getText().toString();

        EditText creditInfo = findViewById(R.id.editTextNumber);
        String creditCard = creditInfo.getText().toString();

        //appeler la classe client (constructor) et entrer les infos pour 'creer' le compte

        Client c = new Client(firstName,lastName,email,password,creditCard);
        c.connected = true;

        Intent intent = new Intent(getApplicationContext(), MainAccueil3Activity.class);
        startActivityForResult(intent, 0);

    }
}