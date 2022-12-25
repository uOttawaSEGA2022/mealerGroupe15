package com.example.mealer.recyclerviewclasses;

import static android.view.View.VISIBLE;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealer.R;
import com.example.mealer.models.commandeModel;

import java.util.ArrayList;
import java.util.Objects;

public class CommandeRecyclerViewAdapter extends RecyclerView.Adapter<CommandeRecyclerViewAdapter.MyViewHolder>{
    private final RecyclerViewInterface recyclerViewInterface;
    AppCompatActivity context;

    static ArrayList<commandeModel> Array;
    public CommandeRecyclerViewAdapter(AppCompatActivity context, ArrayList<commandeModel> Array, RecyclerViewInterface recyclerViewInterface) {
        this.context=context;
        this.Array=Array;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public CommandeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.daily_meal_model,parent,false);
        return new CommandeRecyclerViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CommandeRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textViewRepasName.setText(Array.get(position).getNomDuRepas());
        holder.textViewRepasPrix.setText(String.valueOf(Array.get(position).getPrix()));
        holder.textViewRepasRate.setText(String.valueOf(Array.get(position).getRate()));
        holder.textViewRepasNamneCuisinier.setText(String.valueOf(Array.get(position).getNomDuCuisinier()));
        holder.laDistance.setText(Array.get(position).getCuisinierAdresse());
        holder.laDistance.setVisibility(VISIBLE);
    if (Objects.equals(Array.get(position).getPhotoRepas(), "cafe")){

            holder.itemBackground.setBackgroundResource(R.drawable.cafe);}
        else if (Objects.equals(Array.get(position).getPhotoRepas(), "burgerhori")){

            holder.itemBackground.setBackgroundResource(R.drawable.burgerhori);}
        else if (Objects.equals(Array.get(position).getPhotoRepas(), "pizzahori")){

            holder.itemBackground.setBackgroundResource(R.drawable.pizzahori);}
        else if (Objects.equals(Array.get(position).getPhotoRepas(), "cocahori")){

            holder.itemBackground.setBackgroundResource(R.drawable.cocahori);}
        else if (Objects.equals(Array.get(position).getPhotoRepas(), "rizfrit")){

            holder.itemBackground.setBackgroundResource(R.drawable.rizfrit);}
        else if (Objects.equals(Array.get(position).getPhotoRepas(), "dejeuner")){

            holder.itemBackground.setBackgroundResource(R.drawable.dejeuner);}
        else{
            holder.itemBackground.setBackgroundResource(R.drawable.cafe);
        }
        if (Array.get(position).getStatutDeLaCommande() == -1){
            holder.Statuuuut.setVisibility(VISIBLE);
            holder.Statuuuut.setImageResource(R.drawable.rejete);
        }else if (Array.get(position).getStatutDeLaCommande() == 1){
            holder.Statuuuut.setVisibility(VISIBLE);

            holder.Statuuuut.setImageResource(R.drawable.verifier);        }
        else{
            holder.Statuuuut.setVisibility(VISIBLE);

            holder.Statuuuut.setImageResource(R.drawable.attente);
        }
    }


    @Override
    public int getItemCount() {
        return Array.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewRepasName, textViewRepasPrix, textViewRepasRate, textViewRepasNamneCuisinier,laDistance;
        ConstraintLayout itemBackground;
        ImageView Statuuuut;
        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textViewRepasName=itemView.findViewById(R.id.NomPlat);
            textViewRepasRate=itemView.findViewById(R.id.RateMenu);
            textViewRepasNamneCuisinier=itemView.findViewById(R.id.NomCuisinierMenu);
            textViewRepasPrix = itemView.findViewById(R.id.PrixPlat);
            itemBackground = itemView.findViewById(R.id.BackGroundModel);
            Statuuuut=itemView.findViewById(R.id.StatutImage);
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