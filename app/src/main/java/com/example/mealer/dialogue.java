package com.example.mealer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class dialogue extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private static String plainteid;
    private boolean found;
    private String nameOfCuisinier;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        plainteid = "";
        found = false;
        nameOfCuisinier = "";

    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    onListe(view);
                }
            });
        }

    }
    public void onListe(View view){
        Intent intent = new Intent(getApplicationContext(), PlainteActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onReject(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(plainteid);
        if(!plainteid.isEmpty()){
            ref.removeValue();
        }
        onListe(view);

    }

    public void onDefinitiveSusupension(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Plainte/"+plainteid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nameOfCuisinier = "";
                for(DataSnapshot snap : snapshot.getChildren()){
                    if(snap.getKey().toString().equals("nameOfCuisinier")){
                        if(!snap.getValue().toString().isEmpty()){
                            nameOfCuisinier = snap.getValue().toString();
                        }

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        if(plainteid != "" && nameOfCuisinier != ""){
            Toast.makeText(getApplicationContext(), "name is " + nameOfCuisinier, Toast.LENGTH_SHORT).show();
            FirebaseDatabase.getInstance().getReference("Cuisinier/"+nameOfCuisinier+"/suspended").setValue(true);
            FirebaseDatabase.getInstance().getReference("Cuisinier/"+nameOfCuisinier+"/suspensionTime").setValue(-1);
            FirebaseDatabase.getInstance().getReference("Plainte/"+plainteid).removeValue();
            plainteid = "";
            nameOfCuisinier = "";
            onListe(view);
        }

    }

    public static void setPlainteid(String id){
        plainteid = id;
    }

}