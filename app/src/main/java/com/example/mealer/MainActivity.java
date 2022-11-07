package com.example.mealer;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    static Admin admin;
    public enum connectionStates{WAITING, CONNECTED, DISCONNECTED, FAILED};
    connectionStates state = connectionStates.WAITING;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

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
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);

                //for(int i=0; i< dataSnapshot.getChildrenCount(); i++){

                while(dataSnapshot.getChildren().iterator().hasNext()){
                    String key = dataSnapshot.getChildren().iterator().next().getKey();
                    if( key == "Admin"){
                        Log.d(TAG, "FOUND ADMIN");
                    }else{
                        Log.d(TAG, key);
                    }

                    dataSnapshot = dataSnapshot.getChildren().iterator().next();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

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