package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        Intent intent = new Intent(getApplicationContext(), MainAccueil2Activity.class);

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

        //si tous les champs sont remplis, on change de page.
        if (c.isConnected()&& ) {
            startActivityForResult(intent, 0);
    }
}