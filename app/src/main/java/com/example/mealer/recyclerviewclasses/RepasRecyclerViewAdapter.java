package com.example.mealer.recyclerviewclasses;


import static android.view.View.VISIBLE;

import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mealer.R;
import com.example.mealer.clientclasses.Client;
import com.example.mealer.models.RepasModel;
import com.example.mealer.cuisinierclasses.Cuisinier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepasRecyclerViewAdapter extends RecyclerView.Adapter<RepasRecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    AppCompatActivity context;
    Cuisinier cui;
    Client clientt;

    static ArrayList<RepasModel> menuArray;
    public RepasRecyclerViewAdapter(AppCompatActivity context, ArrayList<RepasModel> menuArray, RecyclerViewInterface recyclerViewInterface) {
        this.context=context;
        this.menuArray=menuArray;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<RepasModel> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        menuArray = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RepasRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        cui=Cuisinier.getInstance();
        clientt=Client.getInstance();
        View view=inflater.inflate(R.layout.daily_meal_model,parent,false);
        return new RepasRecyclerViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }


    @Override
    public void onBindViewHolder(@NonNull RepasRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.textViewRepasName.setText(menuArray.get(position).getNom());
        holder.textViewRepasPrix.setText(String.valueOf(menuArray.get(position).getPrix()));
        holder.textViewRepasRate.setText(String.valueOf(menuArray.get(position).getRate()));
        holder.textViewRepasNamneCuisinier.setText(menuArray.get(position).getNameCuisinier());


       holder.laDistance.setText(menuArray.get(position).getAdresseDuCuisinier());
       holder.laDistance.setVisibility(VISIBLE);

        if (Objects.equals(menuArray.get(position).getPhotoRepas(), "cafe")){

        holder.TextVPhoto.setBackgroundResource(R.drawable.cafe);}
        else if (Objects.equals(menuArray.get(position).getPhotoRepas(), "burgerhori")){

            holder.TextVPhoto.setBackgroundResource(R.drawable.burgerhori);}
        else if (Objects.equals(menuArray.get(position).getPhotoRepas(), "pizzahori")){

            holder.TextVPhoto.setBackgroundResource(R.drawable.pizzahori);}
        else if (Objects.equals(menuArray.get(position).getPhotoRepas(), "cocahori")){

            holder.TextVPhoto.setBackgroundResource(R.drawable.cocahori);}
        else if (Objects.equals(menuArray.get(position).getPhotoRepas(), "rizfrit")){

            holder.TextVPhoto.setBackgroundResource(R.drawable.rizfrit);}
        else if (Objects.equals(menuArray.get(position).getPhotoRepas(), "dejeuner")){

            holder.TextVPhoto.setBackgroundResource(R.drawable.dejeuner);}
        else{
            holder.TextVPhoto.setBackgroundResource(R.drawable.cafe);
        }
    }

    @Override
    public int getItemCount() {
        return menuArray.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewRepasName, textViewRepasPrix,textViewRepasNamneCuisinier, textViewRepasRate,laDistance;
        ConstraintLayout TextVPhoto;
        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textViewRepasName=itemView.findViewById(R.id.NomPlat);
            textViewRepasRate=itemView.findViewById(R.id.RateMenu);
           textViewRepasNamneCuisinier=itemView.findViewById(R.id.NomCuisinierMenu);
            textViewRepasPrix = itemView.findViewById(R.id.PrixPlat);
            TextVPhoto=itemView.findViewById(R.id.BackGroundModel);
            laDistance=itemView.findViewById(R.id.Distance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface!=null){

                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.OnItemClick(pos);
                        }
                    }
                }
            });

        }
    }


}

