package com.example.mealer.adminclasses;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer.adminclasses.PlainteActivity;
import com.example.mealer.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class dialogue extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    static String id;
    static String idCuisinier;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_suspension);
        initDatePicker();


    }

//    private String getTodaysDate()
//    {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        month = month + 1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        return makeDateString(day, month, year);
//    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
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
        return day + "/"+getMonthFormat(month) + "/" + year;
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
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Cuisinier/"+idCuisinier);
                    ref.child("suspended").setValue(true);
                    ref.child("suspensionTime").setValue(makeDateString(day, month, year));
                    onReject(view);
                }
            });

    }
    public void onListe(View view){
        Intent intent = new Intent(getApplicationContext(), PlainteActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onReject(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Plainte/" + id);
        ref.removeValue();
        onListe(view);

    }

    public void onDefinitiveSusupension(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Cuisinier/"+idCuisinier);
        ref.child("suspended").setValue(true);
        ref.child("suspensionTime").setValue("-1");
        onReject(view);
    }

    public static void setIds(String id, String idCuisinier){
        dialogue.id = id;
        dialogue.idCuisinier = idCuisinier;
    }


}