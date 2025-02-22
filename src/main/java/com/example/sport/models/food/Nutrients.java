package com.example.sport.models.food;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Nutrients {

    //API-ul Edamam trimite datele pt nutrienti cu majuscule
    //in Java conventia implica sa folosim litere mici sau camel case
    //deci folosim @JsonProperty pentru ca Jackson sa mapeze corect
    //un anumit camp din JSON la atributul din clasa Java

    @JsonProperty("PROCNT")
    private Nutrient procnt;
    @JsonProperty("FAT")
    private Nutrient fat;
    @JsonProperty("CHOCDF")
    private Nutrient chocdf;
    @JsonProperty("CHOLE")
    private Nutrient chole;
    @JsonProperty("NA")
    private Nutrient na;
    @JsonProperty("CA")
    private Nutrient ca;
    @JsonProperty("MG")
    private Nutrient mg;
    @JsonProperty("K")
    private Nutrient k;
    @JsonProperty("FE")
    private Nutrient fe;

    public Nutrient getCa() {
        return ca;
    }

    public void setCa(Nutrient ca) {
        this.ca = ca;
    }

    public Nutrient getChocdf() {
        return chocdf;
    }

    public void setChocdf(Nutrient chocdf) {
        this.chocdf = chocdf;
    }

    public Nutrient getChole() {
        return chole;
    }

    public void setChole(Nutrient chole) {
        this.chole = chole;
    }

    public Nutrient getFat() {
        return fat;
    }

    public void setFat(Nutrient fat) {
        this.fat = fat;
    }

    public Nutrient getFe() {
        return fe;
    }

    public void setFe(Nutrient fe) {
        this.fe = fe;
    }

    public Nutrient getK() {
        return k;
    }

    public void setK(Nutrient k) {
        this.k = k;
    }

    public Nutrient getMg() {
        return mg;
    }

    public void setMg(Nutrient mg) {
        this.mg = mg;
    }

    public Nutrient getNa() {
        return na;
    }

    public void setNa(Nutrient na) {
        this.na = na;
    }

    public Nutrient getProcnt() {
        return procnt;
    }

    public void setProcnt(Nutrient procnt) {
        this.procnt = procnt;
    }
}
