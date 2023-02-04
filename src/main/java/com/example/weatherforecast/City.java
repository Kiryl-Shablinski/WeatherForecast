package com.example.weatherforecast;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private String lat;
    private String lon;

    public City(String name, String lat, String lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

}
