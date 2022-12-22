package com.example.mealer.clientclasses;

//import static com.example.mealer.clientclasses.ClientRechercheActivity.repas;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer.R;
import com.example.mealer.models.commandeModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class ClientDescRepasActivity extends AppCompatActivity {
    ImageView plus,minus;
    TextView Quantites,nooom;
    String ParentRepas="";
    TextView timeButton;
    String heure;
    int hour, minute;
    Client c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_desc_repas);
        Quantites=findViewById(R.id.Quantite);
        c = Client.getInstance();
        timeButton = findViewById(R.id.timeButton);

        TextView typeeRepas = findViewById(R.id.textView57);
        TextView typeCuisinee = findViewById(R.id.textView59);
        TextView ingredientListe = findViewById(R.id.textView61);
        TextView allergenee = findViewById(R.id.textView63);
        TextView foodPricee = findViewById(R.id.textView65);
        TextView foo0dDescription = findViewById(R.id.textView67);


        nooom=findViewById(R.id.textView55);
        Bundle extra=getIntent().getExtras();
        String n= extra.getString("nomRepas");
        nooom.setText(n);
        typeeRepas.setText(extra.getString("TypeRepas"));
        typeCuisinee.setText(extra.getString("TypeCuisi"));
        ingredientListe.setText(extra.getString("Ingredients"));
        allergenee.setText(extra.getString("Allergene"));
        foodPricee.setText(""+(Double) extra.getDouble("Prix"));
        String idCUI= extra.getString("IDduCuisinier");


        foo0dDescription.setText(extra.getString("Description"));











    }
    public void popTimePicker(View view)

    {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
                heure=String.format(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
                //Toast.makeText(ClientDescRepasActivity.this, heure, Toast.LENGTH_SHORT).show();
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

    }

    public void OnBackToRepasDuJourClient(View view){
        Intent intent =new Intent(getApplicationContext(),ClientRechercheActivity.class);
        startActivityForResult(intent,0);
    }
    public void onclickPlus(View view){
        int nbr=Integer.parseInt((String) Quantites.getText());
        int a=nbr+1;
        Quantites.setText(""+a);


    }

    public void onclickMinus(View view){
        int nbr=Integer.parseInt((String) Quantites.getText());
        if((nbr-1)>=1){
            int a=nbr-1;

            Quantites.setText(""+a);
        }


    }
    public void onCommander(View view){

        int laQuantité=Integer.parseInt((String) Quantites.getText());
        Bundle extra=getIntent().getExtras();
        String idCUISi= extra.getString("IDduCuisinier");
        DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("Client/" + c.id + "/Commande/");
        DatabaseReference REF= FirebaseDatabase.getInstance().getReference("Cuisinier/" +idCUISi+"/Demandes/" );

        //String key=myRef.ge
        String key  = myRef.push().getKey();

        // DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("Client/"+Client.getInstance().id+"/Commande");
        //myRef.child("Commande").child("commande1");
        // /String key=myRef.push().getKey();




         String IdREPAS=extra.getString("RepasID");
         String nomCui=extra.getString("nomCuisinier");
        String nomREPAS= extra.getString("nomRepas");
        double lePrix=(Double) extra.getDouble("Prix");
        String AddresseDuCui= extra.getString("AdrCuisinier");
        String PHOTO= extra.getString("Tof");


        //Toast.makeText(this, c.id, Toast.LENGTH_SHORT).show();
        commandeModel Mycommand=new commandeModel( IdREPAS,  idCUISi,  key,  nomCui,  nomREPAS,  heure,  c.getId(),c.getFirstName(), lePrix, 0 /*generalRate*/,0,0,
         laQuantité,AddresseDuCui,PHOTO);
        REF.child(key).setValue(Mycommand);
        myRef.child(key).setValue(Mycommand);



        Intent intent =new Intent(getApplicationContext(),ClientEnvoieCommandeActivity.class);
        startActivityForResult(intent,0);
    }


}
