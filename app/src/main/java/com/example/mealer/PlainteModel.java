package com.example.mealer;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties



public class PlainteModel {

        private String nameOfClient;
        private String nameOfCuisinier;
        private String Description;

        public PlainteModel(String nameOfClient, String nameOfCuisinier,String Description ){
            this.nameOfClient=nameOfClient;
            this.nameOfCuisinier=nameOfCuisinier;
            this.Description=Description;
        }
    public PlainteModel(){}





        public String getNameOfClient() {
            return nameOfClient;
        }

        public String getNameOfCuisinier() {
            return nameOfCuisinier;
        }

        public String getDescription() {
            return Description;
        }

        public void setNameOfClient(String NameClientt) {
            nameOfClient= NameClientt;
        }

        public void setNameOfCuisinier(String nameOfCuisinierr) {
            nameOfCuisinier = nameOfCuisinierr;
        }

        public void setDescription(String description) {
            Description = description;
        }
    }



