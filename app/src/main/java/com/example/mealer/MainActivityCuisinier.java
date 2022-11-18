package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivityCuisinier extends AppCompatActivity {
    
    Cuisinier c;
    
    FirebaseDatabase database;
    DatabaseReference myREf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = MainActivity.cuisinier;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cuisinier);
        if(c.connected) {
            @SuppressLint({"MissingInflatedId", "LocalSuppress"})
            TextView welcome = findViewById(R.id.welcomText);
            welcome.setText("Bienvenue," + c.firstName +" vous êtes connecté en tant que cuisinier.");

            if(c.isSuspended()){
                if(c.suspensionTime.equalsIgnoreCase("-1")){
                    welcome.setText("Bienvenue," + c.firstName +" vous êtes suspendu indéfiniment. Veuillez vous deconnecter!");
                }else{
                    welcome.setText("Bienvenue," + c.firstName +" vous êtes suspendu jusqu'au " + c.suspensionTime + " reconnecté vous ultèrieurement.");
                }
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

    ////code pour creer un popup
    //public void onClickMenuComplet(View view){
        //// inflate the layout of the popup window
        //LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        //View popupView = inflater.inflate(R.layout.popup_select, null);

        //// create the popup window
        //int width = LinearLayout.LayoutParams.MATCH_PARENT;
        //int height = LinearLayout.LayoutParams.MATCH_PARENT;
        //final PopupWindow popupWindow = new PopupWindow(popupView, width, height);

        ////show the popup window
        //popupWindow.showAtLocation(view, Gravity.CENTER, 0,0);

        //// dismiss the popup window when touched
        //popupView.setOnTouchListener(new View.OnTouchListener() {
            //@Override
            //public boolean onTouch(View view, MotionEvent motionEvent) {
                //popupWindow.dismiss();
                //return true;
            //}
       // });
    //}

}