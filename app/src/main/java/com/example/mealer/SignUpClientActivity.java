package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        Intent intent = new Intent(getApplicationContext(), MainAccueil3Activity.class);
        //quand on clique sur s'inscrire, on store les données dans des variables pour permettre la reconnection

        EditText fNameBox = findViewById(R.id.FirstNameField);
        String firstName = fNameBox.getText().toString();

        EditText lNameBox = findViewById(R.id.LastNameField);
        String lastName = lNameBox.getText().toString();

        EditText emailBox = findViewById(R.id.editTextTextEmailAddress);
        String email = emailBox.getText().toString();

        EditText passwordBox = findViewById(R.id.editTextTextPassword2);
        String password = passwordBox.getText().toString();

        EditText creditInfo = findViewById(R.id.editTextNumber);
        String creditCard = creditInfo.getText().toString();

        //appeler la classe client (constructor) et entrer les infos pour 'creer' le compte

        Client c = new Client(firstName,lastName,email,password,creditCard);
        c.connect(email,password);
        if (c.isConnected()&& ) {
            startActivityForResult(intent, 0);
        }

    }
}