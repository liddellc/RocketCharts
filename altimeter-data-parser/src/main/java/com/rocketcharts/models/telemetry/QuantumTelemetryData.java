package com.rocketcharts.models.telemetry;

public class QuantumTelemetryData extends TelemetryData {

    private double velocity;
    private double fAltitude;
    private double fVelocity;

    public QuantumTelemetryData() {
        super(0.0, 0.0);
        velocity = 0.0;
        fAltitude = 0.0;
        fVelocity = 0.0;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getfAltitude() {
        return fAltitude;
    }

    public void setfAltitude(double fAltitude) {
        this.fAltitude = fAltitude;
    }

    public double getfVelocity() {
        return fVelocity;
    }

    public void setfVelocity(double fVelocity) {
        this.fVelocity = fVelocity;
    }
}