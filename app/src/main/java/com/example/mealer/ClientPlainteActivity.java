package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




public class ClientPlainteActivity extends AppCompatActivity {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef = database.getReference("Plainte");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_plainte);
    }

    public void OnClickEnvoyerPlainte(View view) {

        EditText description = findViewById(R.id.editTextTextPersonName3);
        String description1=description.getText().toString();
        EditText titre = findViewById(R.id.editTextTextPersonName2);
        String titre1=titre.getText().toString();
        myRef = database.getReference("Plainte/"+ titre1+"/description");
        myRef.setValue(description1);

        myRef = database.getReference("Plainte/"+ titre1+"/id");
        myRef.setValue("xuw");
        myRef = database.getReference("Plainte/"+ titre1+"/nameOfClient");
        myRef.setValue("test");
        myRef = database.getReference("Plainte/"+ titre1+"/nameOfCuisinier");
        myRef.setValue("test");
        Intent intent = new Intent(getApplicationContext(), MainActivityClient.class);
    }


}