package com.rocketcharts.models;

public class MetaData {

    public final String date;
    public final String site;
    public final String rocket;
    public final String motor;

    public MetaData() {
        this.date = null;
        this.site = null;
        this.rocket = null;
        this.motor = null;
    }

    public MetaData(String date, String site, String rocket, String motor) {
        this.date = date;
        this.site = site;
        this.rocket = rocket;
        this.motor = motor;
    }
}
