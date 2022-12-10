package com.example.mealer.cuisinierclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mealer.MainActivity;
import com.example.mealer.R;

public class CuisinierProfileActivity extends AppCompatActivity {

    Cuisinier c= Cuisinier.getInstance();

    EditText lastnameBox;
    EditText nameBox;
    EditText emailBox;
    EditText passwordBox;
    EditText addressBox;
    EditText descBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisinier_profile);

        lastnameBox = findViewById(R.id.editTextTextPersonName2);
        nameBox = findViewById(R.id.editTextTextPersonName3);
        emailBox = findViewById(R.id.editTextTextPersonName4);
        passwordBox = findViewById(R.id.editTextTextPersonName5);
        addressBox = findViewById(R.id.editTextTextPersonName6);
        descBox = findViewById(R.id.editTextTextPersonName7);

        lastnameBox.setText(c.getLastName());
        nameBox.setText(c.getFirstName());
        emailBox.setText(c.getEmail());
        passwordBox.setText(c.getPassword());
        addressBox.setText(c.getAdresse());
        descBox.setText(c.getDescription());
    }

    public void OnSave(View view){

        String lastname = lastnameBox.getText().toString();
        String name = nameBox.getText().toString();
        String email = emailBox.getText().toString();
        String password = passwordBox.getText().toString();
        String address = addressBox.getText().toString();
        String description = descBox.getText().toString();

        c.setInfo(email,password,name,lastname,address,description);

        Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
        startActivityForResult(intent, 0);
        finish();

    }

    public void OnBack(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
        startActivityForResult(intent, 0);
        finish();
    }

    public void OnDeconnecter(View view) {
        c.disconnect();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
        finish();
    }
}