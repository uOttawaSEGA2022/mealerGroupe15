package com.example.mealer.recyclerviewclasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mealer.R;
import com.example.mealer.models.RepasModel;
import com.example.mealer.cuisinierclasses.Cuisinier;

import java.util.ArrayList;

public class RepasRecyclerViewAdapter extends RecyclerView.Adapter<RepasRecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    AppCompatActivity context;
    Cuisinier cui;

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
        View view=inflater.inflate(R.layout.daily_meal_model,parent,false);
        return new RepasRecyclerViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RepasRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.textViewRepasName.setText(menuArray.get(position).getNom());
        holder.textViewRepasPrix.setText(String.valueOf(menuArray.get(position).getPrix()));
        holder.textViewRepasRate.setText(String.valueOf(menuArray.get(position).getRate()));
        holder.textViewRepasNamneCuisinier.setText(menuArray.get(position).getNameCuisinier());
    }

    @Override
    public int getItemCount() {
        return menuArray.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewRepasName, textViewRepasPrix,textViewRepasNamneCuisinier, textViewRepasRate;
        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textViewRepasName=itemView.findViewById(R.id.NomPlat);
            textViewRepasRate=itemView.findViewById(R.id.RateMenu);
           textViewRepasNamneCuisinier=itemView.findViewById(R.id.NomCuisinierMenu);
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

