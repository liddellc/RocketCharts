package com.rocketcharts.dataparser;

import java.util.Date;

public class MetaData {

    public final Date date;
    public final String site;
    public final String rocket;
    public final String motor;
    
   public MetaData() {
        this.date = null;
        this.site = null;
        this.rocket = null;
        this.motor = null;       
    }

    public MetaData(Date date, String site, String rocket, String motor) {
        this.date = date;
        this.site = site;
        this.rocket = rocket;
        this.motor = motor;       
    }
}
