package com.example.mealer.clientclasses;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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


                String key = myRef.push().getKey();


                myRef = database.getReference("Rate/"+key+"/IdRepas");
                myRef.setValue(key);
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
    }

}