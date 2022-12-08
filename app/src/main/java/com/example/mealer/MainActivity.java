package com.example.mealer;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

// ...
    static Admin admin;
    static Client client;
    static Cuisinier cuisinier;
    public enum ConnectionStates{WAITING, CONNECTED, DISCONNECTED, FAILED};
    public enum AccountType{ADMIN, CUISINIER, CLIENT, DISCONNECTED};
    ConnectionStates state = ConnectionStates.WAITING;
    AccountType accountType = AccountType.DISCONNECTED;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView connectionState = findViewById(R.id.stateAfterConnection);
        connectionState.setText("");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

    }
    public void OnLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        startActivityForResult(intent, 0);
        finish();
    }
    public void OnSeconnecter(View view) {
        admin = Admin.getInstance();
        client = Client.getInstance();
        cuisinier = Cuisinier.getInstance();

        EditText editEmail = findViewById(R.id.loginEmail);
        EditText editPassword = findViewById(R.id.loginPassword);

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        TextView connectionState = findViewById(R.id.stateAfterConnection);

        if (email.isEmpty() || email == null ||
                password.isEmpty() || password == null){

            connectionState.setText("Veuillez entrer toutes les informations correctement s'il vous plait!");
        }

        Log.println(Log.DEBUG, "TYPED", "email : " + email+ " passeword : " + password);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // while (snap.getChildren().iterator().hasNext()) {
                    String key = snapshot.getRef().getKey().toString();
                    if(!admin.isConnected() && !cuisinier.isConnected() && !client.isConnected()){
                        if (key.equals("Admin")) {
                            admin.connect(email, password, snapshot, getApplicationContext());
                        } else if (key.equals("Client")) {
                            client.connect(email, password, snapshot, getApplicationContext());
                        } else if (key.equals("Cuisinier")) {
                            cuisinier.connect(email, password, snapshot, getApplicationContext());
                        } else {
                            Log.d(TAG, "ON NE PEUT PAS TROUVER L'INFORMATION DANS " + key);
                        }
                    }else{
                        // ON VERIFIE QUI EST CONNECTÉ PUIS ON LOAD LA NOUVELLE PAGE EN FONCTION DE SI C'EST UN CLIENT, UN ADMIN OU UN CUISINIER
                        if(admin.isConnected()
                                && !client.isConnected()
                                && !cuisinier.isConnected()){
                            state = ConnectionStates.CONNECTED;
                            Log.println(Log.DEBUG, "INFO", "SUCCESS: VOUS ETES CONNECTE");
                            connectionState.setText("Connected");
                            connectionState.setTextColor(Color.green(255));
                            Intent intent = new Intent(getApplicationContext(), MainActivityAdmin.class);
                            startActivityForResult(intent, 0);
                            finish();
                        }else if(client.isConnected()
                                && !admin.isConnected()
                                && !cuisinier.isConnected()){
                            state = ConnectionStates.CONNECTED;
                            Log.println(Log.DEBUG, "INFO", "SUCCESS: VOUS ETES CONNECTE");
                            connectionState.setText("Connected");
                            connectionState.setTextColor(Color.green(255));
                            Intent intent = new Intent(getApplicationContext(), MainActivityClient.class);
                            startActivityForResult(intent, 0);
                            finish();
                        }else if(cuisinier.isConnected()
                                && !admin.isConnected()
                                && !client.isConnected()){
                            state = ConnectionStates.CONNECTED;
                            Log.println(Log.DEBUG, "INFO", "SUCCESS: VOUS ETES CONNECTE");
                            connectionState.setText("Connected");
                            connectionState.setTextColor(Color.green(255));

                            Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
                            startActivityForResult(intent, 0);
                            finish();
                        }else{
                            connectionState.setText("Veuillez verifier votre mot de passe ou votre adresse email");
                            connectionState.setTextColor(Color.parseColor("#FF0000"));
                            Log.println(Log.DEBUG, "INFO", "PROBLEM: VOUS AVEZ ENTRÉ UN MAUVAIS MOT DE PASSE");
                            state = ConnectionStates.FAILED;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        Log.println(Log.DEBUG, "TEST", "Valeur de connected :" + admin.isConnected().toString());

//        if(admin.isConnected()){
//                state = ConnectionStates.CONNECTED;
//                Log.println(Log.DEBUG, "INFO", "SUCCESS: VOUS ETES CONNECTE");
//                connectionState.setText("Connected");
//                connectionState.setTextColor(Color.green(255));
//                admin.setInfo(email, password);
//                Intent intent = new Intent(getApplicationContext(), ADMINACCEUIL.class);
//                startActivityForResult(intent, 0);
//        }else if(accountType == AccountType.CLIENT){
//
//
//        }else if(accountType == AccountType.CUISINIER){
//
//        }else{
//                connectionState.setText("Veuillez verifier votre mot de passe ou votre adresse email");
//                connectionState.setTextColor(Color.parseColor("#FF0000"));
//                Log.println(Log.DEBUG, "INFO", "PROBLEM: VOUS AVEZ RENTREZ UN MAUVAIS MOT DE PASSE");
//                state = ConnectionStates.FAILED;
//        }


    }

}