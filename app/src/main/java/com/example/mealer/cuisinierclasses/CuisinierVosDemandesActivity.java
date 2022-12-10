package com.example.mealer.cuisinierclasses;

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

import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.recyclerviewclasses.RecyclerViewInterface;

public class CuisinierVosDemandesActivity extends AppCompatActivity implements RecyclerViewInterface {

    MenuModel demandes;
    Cuisinier c;
    //Client cli;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisinier_vos_demandes);
        demandes = MenuModel.getInstance();


        //cli=Client.getInstance();
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

    public void traiterDemande(View view){
        //inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_popup_traiter_demande, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height);

        //show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0,0);


        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                popupWindow.dismiss();
                return true;}
        });
    }

    @Override
    public void OnItemClick(int position) {

        Intent intent=new Intent(CuisinierVosDemandesActivity.this,PopupTraiterDemandeActivity.class);
        //Bundle extra=intent.getExtras();
        intent.putExtra("nomduRepas", demandes.demandesArray.get(position).getNomDuRepas());
        intent.putExtra("Quantite",demandes.demandesArray.get(position).getQuantite());
        intent.putExtra("IdClient",demandes.demandesArray.get(position).getIDduClient());
        intent.putExtra("IdDeLaCommande", demandes.demandesArray.get(position).getIdDeLaCommande());
        //Toast.makeText(this, demandes.demandesArray.get(position).getIdDeLaCommande(), Toast.LENGTH_SHORT).show();

        intent.putExtra("nomduClient",demandes.demandesArray.get(position).getNomDUClient());


        //String a="19H";

        intent.putExtra("HeureDeCueillete", demandes.demandesArray.get(position).getHeuredeCeuillette());
        //traiterDemande(new View(this));
        startActivityForResult(intent,0);


    }
}