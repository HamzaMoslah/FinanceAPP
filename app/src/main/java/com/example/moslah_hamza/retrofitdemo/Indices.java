package com.example.moslah_hamza.retrofitdemo;

/**
 * Created by Moslah_Hamza on 03/05/2017.
 */

public class Indices {
    private String nom, ouverture, haut, bas, VolumeDT, titres, Dernier, variation;

    public Indices() {
    }
    public Indices(String nom, String ouverture, String haut, String bas, String VolumeDT, String titres, String dernier, String variation) {
        this.nom = nom;
        this.ouverture = ouverture;
        this.haut = haut;
        this.bas = bas;
        this.VolumeDT = VolumeDT;
        this.titres = titres;
        this.Dernier = dernier;
        this.variation = variation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getOuverture() {
        return ouverture;
    }

    public void setOuverture(String ouverture) {
        this.ouverture = ouverture;
    }

    public String getHaut() {
        return haut;
    }

    public void setHaut(String haut) {
        this.haut = haut;
    }

    public String getBas() {
        return bas;
    }

    public void setBas(String bas) {
        this.bas = bas;
    }

    public String getVolumeDT() {
        return VolumeDT;
    }

    public void setVolumeDT(String volumeDT) {
        this.VolumeDT = volumeDT;
    }

    public String getTitres() {
        return titres;
    }

    public void setTitres(String titres) {
        this.titres = titres;
    }

    public String getDernier() {
        return Dernier;
    }

    public void setDernier(String dernier) {
        this.Dernier = dernier;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }
}
