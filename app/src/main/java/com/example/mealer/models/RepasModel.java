package com.example.mealer.models;

public class RepasModel {

    String repasId, nom, typeDeRepas, typeDeCuisine,
            ingredients, allergenes,IdDuCuisinier, description, nameCuisinier;
    boolean inRepasDuJour;
    double prix,rate;
    String AdresseDuCuisinier;
    String photoRepas;




    public RepasModel(){

    }

    public RepasModel(String nom, String typeDeRepas, String typeDeCuisine, String ingredients, String allergenes, String idDuCuisinier, String description, String nameCuisinier, boolean inRepasDuJour, double prix, double rate,String AdresseDuCuisinier,String photoRepas) {
        this.nom = nom;
        this.typeDeRepas = typeDeRepas;
        this.typeDeCuisine = typeDeCuisine;
        this.ingredients = ingredients;
        this.allergenes = allergenes;
        IdDuCuisinier = idDuCuisinier;
        this.description = description;
        this.nameCuisinier = nameCuisinier;
        this.inRepasDuJour = inRepasDuJour;

        this.prix = prix;
        this.rate = rate;
        this.AdresseDuCuisinier=AdresseDuCuisinier;
        this.photoRepas=photoRepas;
    }

    public String getPhotoRepas() {
        return photoRepas;
    }

    public void setPhotoRepas(String photoRepas) {
        this.photoRepas = photoRepas;
    }

    public String getAdresseDuCuisinier() {
        return AdresseDuCuisinier;
    }

    public void setAdresseDuCuisinier(String adresseDuCuisinier) {
        AdresseDuCuisinier = adresseDuCuisinier;
    }

    public String getNameCuisinier(){

       return nameCuisinier;
    }

    public String getIdDuCuisinier() {
        return IdDuCuisinier;
    }



    protected void setRepasId(String key){
        repasId = key;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getRepasId(){
        return repasId;
    }
    public String getNom() {
        return nom;
    }

    public String getTypeDeRepas() {
        return typeDeRepas;
    }

    public String getTypeDeCuisine() {
        return typeDeCuisine;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getAllergenes() {
        return allergenes;
    }

    public String getDescription() {
        return description;
    }

    public double getPrix() {
        return prix;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTypeDeRepas(String typeDeRepas) {
        this.typeDeRepas = typeDeRepas;
    }

    public void setTypeDeCuisine(String typeDeCuisine) {
        this.typeDeCuisine = typeDeCuisine;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setAllergenes(String allergenes) {
        this.allergenes = allergenes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInRepasDuJour(boolean v){
        inRepasDuJour = v;
    }

    public boolean isInRepasDuJour(){
            return inRepasDuJour;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setIdDuCuisinier(String idDuCuisinier) {
        this.IdDuCuisinier = idDuCuisinier;
    }
}
