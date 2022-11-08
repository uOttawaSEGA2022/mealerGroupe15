package com.example.mealer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<PlainteModel> modeeldeplainte;
    public RecyclerViewAdapter(Context context, ArrayList<PlainteModel> modeeldeplainte, RecyclerViewInterface recyclerViewInterface) {
        this.context=context;
        this.modeeldeplainte=modeeldeplainte;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recyclerview_row,parent,false);
        return new RecyclerViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textVClientName.setText(modeeldeplainte.get(position).getNameOfClient());
        holder.textVCuisiName.setText(modeeldeplainte.get(position).getNameOfCuisinier());
        holder.textVDescrip.setText(modeeldeplainte.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return modeeldeplainte.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textVClientName, textVCuisiName,textVDescrip;
        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textVClientName=itemView.findViewById(R.id.NomClient);
            textVCuisiName=itemView.findViewById(R.id.NomCuisinier);
            textVDescrip=itemView.findViewById(R.id.Descrip);
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

