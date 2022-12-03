package com.example.mealer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RepasDuJourRecyclerViewAdapter extends RecyclerView.Adapter<RepasDuJourRecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;

    static ArrayList<RepasModel> menuArray;
    public RepasDuJourRecyclerViewAdapter(Context context, ArrayList<RepasModel> menuArray, RecyclerViewInterface recyclerViewInterface) {
        this.context=context;
        this.menuArray=menuArray;
        this.recyclerViewInterface=recyclerViewInterface;
    }


    @NonNull
    @Override
    public RepasDuJourRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.daily_meal_model,parent,false);
        return new RepasDuJourRecyclerViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RepasDuJourRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.textViewRepasName.setText(menuArray.get(position).getNom());
        holder.textViewRepasPrix.setText(String.valueOf(menuArray.get(position).getPrix()));
    }

    @Override
    public int getItemCount() {
        return menuArray.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewRepasName, textViewRepasPrix;
        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textViewRepasName=itemView.findViewById(R.id.NomPlat);
            textViewRepasPrix = itemView.findViewById(R.id.PrixPlat);
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

