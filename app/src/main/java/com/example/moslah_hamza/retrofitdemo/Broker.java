package com.example.moslah_hamza.retrofitdemo;

/**
 * Created by Moslah_Hamza on 04/05/2017.
 */

public class Broker {
    private double lat, lon;
    private String name;

    public Broker(double lat, double lon, String name) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
