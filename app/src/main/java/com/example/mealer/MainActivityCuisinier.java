package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivityCuisinier extends AppCompatActivity {
    
    static Cuisinier c;
    
    FirebaseDatabase database;
    DatabaseReference myREf;

    public static void setCuisinier(Cuisinier cuisinier) {
        c = cuisinier;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cuisinier);
        if(c.connected) {
            @SuppressLint({"MissingInflatedId", "LocalSuppress"})
            TextView welcome = findViewById(R.id.welcomText);
            welcome.setText("Bienvenue," + c.firstName + " vous êtes connecté en tant que cuisinier.");

            if(c.isSuspended()){
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.optLayout);
                linearLayout.setVisibility(View.INVISIBLE);
            }

        }

    }
    public void OnDeconnecter(View view) {
        c.disconnect();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }
}