package com.rocketcharts.models.telemetry;

public class ProtonTelemetryData extends TelemetryData {

    private double velocity;
    private double accelerationG;
    private double accelerationFPSS;
    private double velocityAccel;
    private double altimeterAccel;
    private double fAltitude;
    private double fVelocity;
    private double fAccelerationG;
    private double fAccelerationFPSS;
    private double fVelocityAccel;
    private double fAltitudeAccel;

    public ProtonTelemetryData() {
        super(0.0, 0.0);
        velocity = 0.0;
        accelerationG = 0.0;
        accelerationFPSS = 0.0;
        velocityAccel = 0.0;
        altimeterAccel = 0.0;
        fAltitude = 0.0;
        fVelocity = 0.0;
        fAccelerationG = 0.0;
        fAccelerationFPSS = 0.0;
        fVelocityAccel = 0.0;
        fAltitudeAccel = 0.0;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getAccelerationG() {
        return accelerationG;
    }

    public void setAccelerationG(double accelerationG) {
        this.accelerationG = accelerationG;
    }

    public double getAccelerationFPSS() {
        return accelerationFPSS;
    }

    public void setAccelerationFPSS(double accelerationFPSS) {
        this.accelerationFPSS = accelerationFPSS;
    }

    public double getVelocityAccel() {
        return velocityAccel;
    }

    public void setVelocityAccel(double velocityAccel) {
        this.velocityAccel = velocityAccel;
    }

    public double getAltimeterAccel() {
        return altimeterAccel;
    }

    public void setAltimeterAccel(double altimeterAccel) {
        this.altimeterAccel = altimeterAccel;
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

    public double getfAccelerationG() {
        return fAccelerationG;
    }

    public void setfAccelerationG(double fAccelerationG) {
        this.fAccelerationG = fAccelerationG;
    }

    public double getfAccelerationFPSS() {
        return fAccelerationFPSS;
    }

    public void setfAccelerationFPSS(double fAccelerationFPSS) {
        this.fAccelerationFPSS = fAccelerationFPSS;
    }

    public double getfVelocityAccel() {
        return fVelocityAccel;
    }

    public void setfVelocityAccel(double fVelocityAccel) {
        this.fVelocityAccel = fVelocityAccel;
    }

    public double getfAltitudeAccel() {
        return fAltitudeAccel;
    }

    public void setfAltitudeAccel(double fAltitudeAccel) {
        this.fAltitudeAccel = fAltitudeAccel;
    }
}