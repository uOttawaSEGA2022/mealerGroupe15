package com.example.mealer;

public class RepasModel {

    String repasId, nom, typeDeRepas, typeDeCuisine,
            ingredients, allergenes, description;
    boolean inRepasDuJour;
    double prix;



    public RepasModel(){

    }

    public RepasModel(String nom, String typeDeRepas, String typeDeCuisine, String ingredients,
                      String allergenes, Double prix, String description){
        this.nom = nom;
        this.typeDeRepas = typeDeRepas;
        this.typeDeCuisine = typeDeCuisine;
        this.ingredients = ingredients;
        this.allergenes = allergenes;
        this.prix = prix;
        this.description = description;
        this.inRepasDuJour = false;

    }

    protected void setRepasId(String key){
        repasId = key;
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
}
