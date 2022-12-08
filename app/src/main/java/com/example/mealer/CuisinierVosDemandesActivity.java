package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class CuisinierVosDemandesActivity extends AppCompatActivity implements RecyclerViewInterface{

    MenuModel demandes;
    Cuisinier c;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisinier_vos_demandes);
        demandes = MenuModel.getInstance();
        c = Cuisinier.getInstance();
        demandes.showDemandes(c.id, (RecyclerView) findViewById(R.id.demandesRecyclerview), this, this);
    }

    public void OnBack(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
        startActivityForResult(intent, 0);
    }

    public void OnClickItem(View view){

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_popup_traiter_demande, null);

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
        //if(popupView.isShown()){
        //    menu.ShowMenu((RecyclerViewInterface) popupView, this);
        //}
    }

    @Override
    public void OnItemClick(int position) {

    }
}