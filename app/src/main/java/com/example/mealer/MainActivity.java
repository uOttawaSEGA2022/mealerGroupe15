package com.example.mealer;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static Admin admin;
    public enum connectionStates{WAITING, CONNECTED, DISCONNECTED, FAILED};
    connectionStates state = connectionStates.WAITING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView connectionState = findViewById(R.id.stateAfterConnection);
        connectionState.setText("");
        admin = new Admin();

    }
    public void OnLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        startActivityForResult(intent, 0);
    }
    public void OnSeconnecter(View view) {
        EditText editEmail = findViewById(R.id.loginEmail);
        EditText editPassword = findViewById(R.id.loginPassword);

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        TextView connectionState = findViewById(R.id.stateAfterConnection);

        Log.println(Log.DEBUG, "INFO", "email : " + email+ " passeword : " + password);
        admin.connect(email, password);

        if(admin.isConnected()){
            state = connectionStates.CONNECTED;
            Log.println(Log.DEBUG, "INFO", "SUCCESS: VOUS ETES CONNECTE");
            connectionState.setText("Connected");
            connectionState.setTextColor(Color.green(255));
            admin.setInfo(email, password);
            Intent intent = new Intent(getApplicationContext(), ADMINACCEUIL.class);
            startActivityForResult(intent, 0);
        }else{
            connectionState.setText("Veuillez verifier votre mot de passe ou votre adresse email");
            connectionState.setTextColor(Color.parseColor("#FF0000"));
            Log.println(Log.DEBUG, "INFO", "SUCCESS: VOUS AVEZ RENTREZ UN MAUVAIS MOT DE PASSE");
            state = connectionStates.FAILED;
        }

    }

}