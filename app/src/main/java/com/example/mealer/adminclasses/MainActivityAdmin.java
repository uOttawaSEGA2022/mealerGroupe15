package com.example.mealer.adminclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mealer.MainActivity;
import com.example.mealer.R;

public class MainActivityAdmin extends AppCompatActivity {
    Button Gotocom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        Gotocom=findViewById(R.id.ToComplaints);
        Gotocom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityAdmin.this,PlainteActivity.class));
                finish();
            }
        });
    }
    public void OnAdDeconnecter(View view) {
        Admin.getInstance().disconnect();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
        finish();
    }
     /*public void OnGotoComplains(View view) {
        Intent intent = new Intent(getApplicationContext(), PlainteActivity.class);
        startActivityForResult(intent, 0);
    }*/
}