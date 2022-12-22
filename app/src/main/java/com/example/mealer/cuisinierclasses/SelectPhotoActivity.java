package com.example.mealer.cuisinierclasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer.R;


public class SelectPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
    }
    public void SetFoodIcon(View view){
        Intent returnIntent=new Intent();
        ImageView selectedImage=(ImageView)view;
        returnIntent.putExtra("imageID",selectedImage.getId());
        setResult(RESULT_OK,returnIntent);
        finish();
    }
}