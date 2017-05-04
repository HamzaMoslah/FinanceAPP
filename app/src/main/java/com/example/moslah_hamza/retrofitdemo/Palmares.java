package com.example.moslah_hamza.retrofitdemo;

/**
 * Created by Moslah_Hamza on 04/05/2017.
 */

public class Palmares {
    private String nom, haut, bas, Dernier, volume, variation;

    public Palmares() {
    }
    public Palmares(String nom, String haut, String bas, String dernier, String volume, String variation) {
        this.nom = nom;
        this.haut = haut;
        this.bas = bas;
        Dernier = dernier;
        this.volume = volume;
        this.variation = variation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getDernier() {
        return Dernier;
    }

    public void setDernier(String dernier) {
        Dernier = dernier;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }
}
