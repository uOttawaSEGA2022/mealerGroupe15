package com.example.mealer.models;

import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class commandeModel {

    String idDuRepas, idDuCuisinier, idDeLaCommande, nomDuCuisinier, nomDuRepas,HeuredeCeuillette;
    String IDduClient,NomDUClient;
    double prix, rate, myRate;
    int statutDeLaCommande,Quantite;


    public commandeModel() {
    }

    public commandeModel(String idDuRepas, String idDuCuisinier, String idDeLaCommande, String nomDuCuisinier, String nomDuRepas, String heuredeCeuillette, String IDduClient, String nomDUClient, double prix, double rate, double myRate, int statutDeLaCommande, int quantite) {
        this.idDuRepas = idDuRepas;
        this.idDuCuisinier = idDuCuisinier;
        this.idDeLaCommande = idDeLaCommande;
        this.nomDuCuisinier = nomDuCuisinier;
        this.nomDuRepas = nomDuRepas;
        HeuredeCeuillette = heuredeCeuillette;
        this.IDduClient = IDduClient;
        NomDUClient = nomDUClient;
        this.prix = prix;
        this.rate = rate;
        this.myRate = myRate;
        this.statutDeLaCommande = statutDeLaCommande;
        Quantite = quantite;
    }

    public String getIdDuRepas() {
        return idDuRepas;
    }

    public void setIdDuRepas(String idDuRepas) {
        this.idDuRepas = idDuRepas;
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

    public String getHeuredeCeuillette() {
        return HeuredeCeuillette;
    }

    public void setHeuredeCeuillette(String heuredeCeuillette) {
        HeuredeCeuillette = heuredeCeuillette;
    }

    public String getIDduClient() {
        return IDduClient;
    }

    public void setIDduClient(String IDduClient) {
        this.IDduClient = IDduClient;
    }

    public String getNomDUClient() {
        return NomDUClient;
    }

    public void setNomDUClient(String nomDUClient) {
        NomDUClient = nomDUClient;
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

    public double getMyRate() {
        return myRate;
    }

    public void setMyRate(double myRate) {
        this.myRate = myRate;
    }

    public int getStatutDeLaCommande() {
        return statutDeLaCommande;
    }

    public void setStatutDeLaCommande(int statutDeLaCommande) {
        this.statutDeLaCommande = statutDeLaCommande;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int quantite) {
        Quantite = quantite;
    }
}
