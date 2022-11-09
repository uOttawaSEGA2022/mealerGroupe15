package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class MainActivityAdmin extends AppCompatActivity {
    Button Gotocom;
    DatabaseReference databasePlainte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        Gotocom=findViewById(R.id.ToComplaints);
        databasePlainte= FirebaseDatabase.getInstance().getReference();
        Gotocom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityAdmin.this,PlainteActivity.class));//???????
                finish();
            }
        });
    }
    public void OnAdDeconnecter(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }
     /*public void OnGotoComplains(View view) {
        Intent intent = new Intent(getApplicationContext(), PlainteActivity.class);
        startActivityForResult(intent, 0);
    }*/
}