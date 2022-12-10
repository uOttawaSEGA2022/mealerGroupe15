package com.example.mealer.cuisinierclasses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer.MainActivity;
import com.example.mealer.R;

public class MainActivityCuisinier extends AppCompatActivity {
    
    Cuisinier c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = Cuisinier.getInstance();
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
        finish();
    }

    ////code pour creer un popup
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
    public void OnClickMenuComplet(View view){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnClickMenuDuJour(View view){
        Intent intent = new Intent(getApplicationContext(), MenuDuJourActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnProfile(View view){
        Intent intent = new Intent(getApplicationContext(), CuisinierProfileActivity.class);
        startActivityForResult(intent, 0);
    }

    public void OnDemande(View view){
        Intent intent = new Intent(getApplicationContext(),CuisinierVosDemandesActivity.class);
        startActivityForResult(intent,0);
    }

}