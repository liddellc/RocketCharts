package com.rocketcharts.models.telemetry;

public class TelemetryData {
    private double time;
    private double altitude;

    public TelemetryData(double time, double altitude) {
        this.time = time;
        this.altitude = altitude;
    }

    // getters and setters
    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
