package com.example.mealer;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ClientVotreCommandeActivity extends AppCompatActivity {
    RatingBar myRatingStar;
    TextView AfficheRate;
    int MyRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_votre_commande);
        myRatingStar=findViewById(R.id.ratingBar);
        AfficheRate=findViewById(R.id.Note);
        myRatingStar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String mess=null;
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


                }
                AfficheRate.setText(Integer.toString(MyRate));
                Toast.makeText(ClientVotreCommandeActivity.this,mess , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void OnBackToMesRepas(View view) {
        Intent intent =new Intent(getApplicationContext(),ClientMesRepasActivity.class);
        startActivityForResult(intent, 0);

    }

    public void onSoumettrePlainte(View view) {
        Intent intent =new Intent(getApplicationContext(),ClientPlainteActivity.class);
        startActivityForResult(intent, 0);


    }


}