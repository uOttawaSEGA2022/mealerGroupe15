package com.example.mealer.clientclasses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealer.R;
import com.example.mealer.models.commandeModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RateDialogu extends Dialog {

    RatingBar myRatingStar;
    String mess=null;
    commandeModel c;
    Client client;


    int MyRate;

    public RateDialogu(@NonNull Context context, commandeModel c) {
        super(context);
        this.c = c;
        client = Client.getInstance();
    }

    public RateDialogu(@NonNull Context context) {
        super(context);
    }

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef = database.getReference("Rate");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_dialogu);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView AfficheRate=findViewById(R.id.Note);

        @SuppressLint("WrongViewCast") final AppCompatButton ConfirmBtn=findViewById(R.id.ConfirmerRate);
        myRatingStar=findViewById(R.id.ratingBar);

        myRatingStar.setRating((float) c.getMyRate());
//        DatabaseReference ratingRef = FirebaseDatabase.getInstance().getReference("Client/" + );


        ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(),"Merci d'avoir noter votre repas" , Toast.LENGTH_SHORT).show();

                ArrayList<Double> average = new ArrayList<Double>();

                String key = myRef.push().getKey();


                myRef = database.getReference("Rate/"+key+"/id");
                myRef.setValue(key);
                myRef = database.getReference("Rate/"+key+"/IdRepas");
                myRef.setValue(c.getIdDuRepas());
                myRef = database.getReference("Rate/"+key+"/RateValue");
                myRef.setValue((int) myRatingStar.getRating());
                c.setMyRate((int) myRatingStar.getRating());
                myRef = database.getReference("Client/" + client.getId()+"/Commande/" + c.getIdDeLaCommande());
                myRef.child("myRate").setValue((int) myRatingStar.getRating());
                myRef = database.getReference("Rate/"+key+"/idDuCuisinier");
                myRef.setValue(c.getIdDuCuisinier());
                dismiss();


            }
        });


        myRatingStar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                MyRate=(int) rating;
                switch (MyRate){

                    case 1:
                        mess="Sorry to hear that! :(";
                        break;
                    case 2:
                        mess="We always accept suggetions";
                        break;
                    case 3:
                        mess="Good enough!";
                        break;
                    case 4:
                        mess="Great! Thank you!";
                        break;
                    case 5:
                        mess="Awesome!";
                        break;
                    default:
                        mess="Sorry to hear that! :(";
                        break;


                }
                AfficheRate.setText(Integer.toString(MyRate));




                Toast.makeText(getContext().getApplicationContext(),mess , Toast.LENGTH_SHORT).show();




            }
        });

        myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double realAverage = 0.0;
                int i=0;
                if(snapshot.hasChild("Rate")) {
                    DataSnapshot rateSnapshot = snapshot.child("Rate");
                    for (DataSnapshot rates : rateSnapshot.getChildren()) {
                        if (rates.child("IdRepas").exists() && rates.child("IdRepas").getValue().toString() == c.getIdDuRepas() &&
                                rates.child("idDuCuisinier").exists() && rates.child("idDuCuisinier").getValue().toString() == c.getIdDuCuisinier()) {
                            realAverage = realAverage + Double.parseDouble(rates.child("RateValue").getValue().toString());
                            i += 1;
                        }
                    }

                    realAverage = Math.round(realAverage / i);
                    DataSnapshot clientIterator = snapshot.child("Client");
                    for (DataSnapshot clients : clientIterator.getChildren()) {
                        if (clients.child("Commande").exists() && clients.child("Commande/" + c.getIdDeLaCommande()).exists()) {
                            DatabaseReference ref = clients.child("Commande/" + c.getIdDeLaCommande() + "/rate").getRef();
//                            if(!Double.isNaN(realAverage) && Double.isFinite())
                            ref.setValue(realAverage);
                        }
                    }
                    DataSnapshot cuisinierIterator = snapshot.child("Cuisinier");
                    for (DataSnapshot cooks : cuisinierIterator.getChildren()) {
                        if (cooks.child("Demandes").exists() && cooks.child("Demandes/" + c.getIdDeLaCommande()).exists() &&
                                cooks.child("menu").exists() && cooks.child("menu/" + c.getIdDuRepas()).exists()) {
                            DatabaseReference ref = cooks.child("Demandes/" + c.getIdDeLaCommande() + "/rate").getRef();
                            DatabaseReference menu = cooks.child("menu/"+c.getIdDuRepas()+ "/rate").getRef();
                            menu.setValue(realAverage);
                            ref.setValue(realAverage);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}