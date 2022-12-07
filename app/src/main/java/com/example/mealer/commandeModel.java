package com.example.mealer;

import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class commandeModel {

    String idDuRepas, idDuCuisinier, idDeLaCommande, nomDuCuisinier, nomDuRepas;
    double prix, rate, myRate;
    int statutDeLaCommande;


    public commandeModel() {
    }

    public commandeModel(String idDuRepas, String idDuCuisinier,
                         String nomDuCuisinier, String nomDuRepas, double prix, double rate, double myRate,
                         int statutDeLaCommande) {
        this.idDuRepas = idDuRepas;
        this.idDuCuisinier = idDuCuisinier;
        this.nomDuCuisinier = nomDuCuisinier;
        this.nomDuRepas = nomDuRepas;
        this.prix = prix;
        this.rate = rate;
        this.myRate = myRate;
        this.statutDeLaCommande = statutDeLaCommande;
    }

    public double getMyRate() {
        return myRate;
    }

    public void setMyRate(double myRate) {
        this.myRate = myRate;
    }

    public String getNomDuCuisinier() {
        return nomDuCuisinier;
    }

    public void setNomDuCuisinier(String nomDuCuisinier) {
        this.nomDuCuisinier = nomDuCuisinier;
    }

    public String getNomDuRepas() {
        return nomDuRepas;
    }

    public void setNomDuRepas(String nomDuRepas) {
        this.nomDuRepas = nomDuRepas;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getIdDuCuisinier() {
        return idDuCuisinier;
    }

    public void setIdDuCuisinier(String idDuCuisinier) {
        this.idDuCuisinier = idDuCuisinier;
    }

    public String getIdDeLaCommande() {
        return idDeLaCommande;
    }

    public void setIdDeLaCommande(String idDeLaCommande) {
        this.idDeLaCommande = idDeLaCommande;
    }

    public String getIdDuRepas() {
        return idDuRepas;
    }

    public void setIdDuRepas(String idDuRepas) {
        this.idDuRepas = idDuRepas;
    }

    public String getStatutDeLaCommande() {
        switch (statutDeLaCommande) {
            case (-1):
                return "Désolé votre commande a été rejeté.";
            case(0):
                return "En attente d'une réponse du cuisinier";
            case(1):
                return "le cuisinier est entrain de préparer votre commande";
            case(2):
                return "Votre commande est prête";
            default:
                return "le cuisinier est entrain de préparer votre commande";
        }
    }

    public void setStatutDeLaCommande(int statutDeLaCommande) {
        this.statutDeLaCommande = statutDeLaCommande;
    }
}
