package com.example.mealer;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties



public class PlainteModel {

        private String nameOfClient;
        private String nameOfCuisinier;
        private String titre, description;
        private String id, idCuisinier;

    public PlainteModel(String nameOfClient, String nameOfCuisinier, String titre, String description, String id, String idCuisinier) {
        this.nameOfClient = nameOfClient;
        this.nameOfCuisinier = nameOfCuisinier;
        this.titre = titre;
        this.description = description;
        this.id = id;
        this.idCuisinier = idCuisinier;
    }

    public PlainteModel(){}

    public String getNameOfClient() {
        return nameOfClient;
    }

    public void setNameOfClient(String nameOfClient) {
        this.nameOfClient = nameOfClient;
    }

    public String getNameOfCuisinier() {
        return nameOfCuisinier;
    }

    public void setNameOfCuisinier(String nameOfCuisinier) {
        this.nameOfCuisinier = nameOfCuisinier;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCuisinier() {
        return idCuisinier;
    }

    public void setIdCuisinier(String idCuisinier) {
        this.idCuisinier = idCuisinier;
    }
}



