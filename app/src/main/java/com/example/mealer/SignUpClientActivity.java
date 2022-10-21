package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        //quand on clique s'inscrire, on store les données dans des variables pour permettre la reconnection
        //creer des variables pour garder les infos entrées
        //appeler la classe client (constructor) et entrer les infos pour 'creer' le compte
        startActivityForResult(intent, 0);

    }
}