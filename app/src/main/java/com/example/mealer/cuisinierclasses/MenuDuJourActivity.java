
package com.example.mealer.cuisinierclasses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.recyclerviewclasses.RecyclerViewInterface;
import com.example.mealer.models.RepasModel;

public class MenuDuJourActivity extends AppCompatActivity implements RecyclerViewInterface {
    MenuModel menu;
    static RepasModel repas;
    String idCuisinier = Cuisinier.getInstance().id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_du_jour);
        menu = MenuModel.getInstance();
        menu.refresh();
        RecyclerView recyclerView= findViewById(R.id.RecyclerView);
        menu.ShowMenuDuJour(idCuisinier, recyclerView, this, this);
    }
    public  void goBack(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityCuisinier.class);
        startActivityForResult(intent, 0);
    }
    @SuppressLint("MissingInflatedId")
    public void addFromListe(View view){
//        // inflate the layout of the popup window
//        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.popup_select, null);
//
//        // create the popup window
//        int width = LinearLayout.LayoutParams.MATCH_PARENT;
//        int height = LinearLayout.LayoutParams.MATCH_PARENT;
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height);
//
//        //show the popup window
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0,0);
//
//
//        // dismiss the popup window when touched
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                popupWindow.dismiss();
//                return true;
//            }
//        });
//
//
//
//        if(popupView.isShown()){
//            menu.ShowMenu((RecyclerViewInterface) popupView, this);
//        }
        Intent intent = new Intent(getApplicationContext(), popupSelect.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void OnItemClick(int position) {
        repas = menu.menuDuJourArray.get(position);
        Intent intent = new Intent(getApplicationContext(), repasMenuDuJourActivity.class);
        startActivityForResult(intent, 0);

    }

    public static RepasModel getRepas(){
        return repas;
    }
}

