
package com.example.mealer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MenuDuJourActivity extends AppCompatActivity {
    ArrayList<MenuModel> ListeDesPlats=new ArrayList<>();
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_du_jour);
    }
    public  void GotoAdmin(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
        startActivityForResult(intent, 0);
    }
    public void addFromListe(View view){
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_select, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height);

        //show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0,0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}

