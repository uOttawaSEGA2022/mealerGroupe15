package com.example.mealer.clientclasses;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//import com.example.mealer.databinding.ActivityClientDescRepasBinding;

import com.example.mealer.models.MenuModel;
import com.example.mealer.R;
import com.example.mealer.recyclerviewclasses.RecyclerViewInterface;
import com.example.mealer.models.RepasModel;
import com.example.mealer.recyclerviewclasses.RepasRecyclerViewAdapter;

import java.util.ArrayList;

public class ClientRechercheActivity extends AppCompatActivity implements RecyclerViewInterface {

    MenuModel menu;
    static RepasModel repas;
    RepasRecyclerViewAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_recherche);
        menu = MenuModel.getInstance();

        adapter=new RepasRecyclerViewAdapter(this,menu.allMenuDuJourArray,this);
        menu.showAllMenuDuJour(adapter, (RecyclerView) findViewById(R.id.rechercheRecylclerView), this, this);
        // getting search view of our item.
        SearchView searchView = findViewById(R.id.searchView);

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
    }

    public void OnBack (View view){
        Intent intent = new Intent(getApplicationContext(), MainActivityClient.class);
        startActivityForResult(intent, 0);
        finish();
    }
    
    public void OnSearch (View view){

//        Intent intent = new Intent(getApplicationContext(), ClientRechercheFiltreActivity.class);
//        startActivityForResult(intent, 0);
//        finish();
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<RepasModel> filteredlist = new ArrayList<RepasModel>();

        // running a for loop to compare elements.
        for (RepasModel item : menu.allMenuDuJourArray) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getNom().toLowerCase().contains(text.toLowerCase()) ||
                    item.getTypeDeCuisine().toLowerCase().contains(text.toLowerCase()) ||
                    item.getTypeDeRepas().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
            adapter.filterList(filteredlist);
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void OnItemClick(int position) {
        //repas = menu.menuDuJourArray.get(position);
        //RepasMenuActivity.position = position;
       // menu.allMenuDuJourArray.get(position).getNom();
       //Intent intent=new Intent(getApplicationContext(),ClientDescRepasActivity.class);

        Intent intent=new Intent(ClientRechercheActivity.this,ClientDescRepasActivity.class);
        //for (RepasModel item : menu.allMenuDuJourArray)

       intent.putExtra("nomRepas",menu.allMenuDuJourArray.get(position).getNom());
        intent.putExtra("TypeRepas",menu.allMenuDuJourArray.get(position).getTypeDeRepas());
        intent.putExtra("TypeCuisi",menu.allMenuDuJourArray.get(position).getTypeDeCuisine());
        intent.putExtra("Ingredients",menu.allMenuDuJourArray.get(position).getIngredients());
        intent.putExtra("Allergene",menu.allMenuDuJourArray.get(position).getAllergenes());
        //intent.putExtra("Prix",(int)menu.allMenuDuJourArray.get(position).getPrix());
        intent.putExtra("Prix",menu.allMenuDuJourArray.get(position).getPrix());
        intent.putExtra("RepasID",menu.allMenuDuJourArray.get(position).getRepasId());
        intent.putExtra("Description",menu.allMenuDuJourArray.get(position).getDescription());
        intent.putExtra("IDduCuisinier",menu.allMenuDuJourArray.get(position).getIdDuCuisinier());
        intent.putExtra("nomCuisinier",menu.allMenuDuJourArray.get(position).getNameCuisinier());








        startActivityForResult(intent,0);

    }
}