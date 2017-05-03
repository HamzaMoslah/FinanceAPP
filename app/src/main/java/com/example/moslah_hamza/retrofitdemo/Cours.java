package com.example.moslah_hamza.retrofitdemo;

/**
 * Created by Moslah_Hamza on 02/05/2017.
 */

public class Cours {
    private String nom;
    private String unit;
    private String sell;
    private String buy;
    private String code;

    public Cours() {
    }

    public Cours(String nom, String unit, String sell, String buy, String code) {
        this.nom = nom;
        this.unit = unit;
        this.buy = buy;
        this.sell = sell;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

