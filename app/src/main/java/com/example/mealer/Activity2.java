package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mealer.clientclasses.SignUpClientActivity;
import com.example.mealer.cuisinierclasses.SignUpCuisinierActivity;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }
    public void OnClickReturnMain(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);}
    public void OnClickAsCuisinier(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpCuisinierActivity.class);
        startActivityForResult(intent, 0);}
    public void OnClickAsClient(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpClientActivity.class);
        startActivityForResult(intent, 0);}

}