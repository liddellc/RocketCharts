package com.rocketcharts.altimeter;

import com.rocketcharts.models.telemetry.ProtonTelemetryData;
import com.rocketcharts.models.telemetry.TelemetryData;

public class ProtonAltimeter extends Altimeter {

    public ProtonAltimeter() {
        this.setHeader(
                "T,Alt,Veloc,AccelG,AccelFPSS,VAccel,AltAccel,Filt_Alt,FVeloc,FAccelG,FAccelFPSS,FAccelVel,FAccelAlt,LDA,MaxV,MaxA,BO#1,LowV,Apogee,N-O,CH1,CH2");
        this.setLabels(new String[] {
                "time",
                "alt",
                "vel",
                "accelG",
                "accelFPSS",
                "velAcc",
                "altAcc",
                "fAlt",
                "fVel",
                "fAccelG",
                "fAccelFPSS",
                "fVelAcc",
                "fAltAcc",
                "lda",
                "maxV",
                "maxA",
                "bo1",
                "lowV",
                "apo",
                "no",
                "dro",
                "main" });
        this.setDescriptions(new String[] {
                "Time",
                "Altitude",
                "Velocity",
                "Acceleration Gs",
                "Acceleration FPSS",
                "Velocity (Accelerometer)",
                "Altitude (Accelerometer)",
                "Filtered Altitude",
                "Filtered Velocity",
                "Filtered Acceleration Gs",
                "Filtered Acceleration FPSS",
                "Filtered Velocity (Accelerometer)",
                "Filtered Altitude (Accelerometer)",
                "Launch Detection Altitude",
                "Max Velocity",
                "Max Aacceleration",
                "Boost #1",
                "Low Velocity",
                "Apogee",
                "Nose Over",
                "Drogue",
                "Main" });
        this.setDataCol(1);
        this.setTimeCol(0);
        this.setFiltDataCol(7);
        this.setEventDataStartCol(13);
        this.setEventDataEndCol(21);
    }

    @Override
    public TelemetryData getTelemetryData(String[] data) {
        ProtonTelemetryData telemetryData = new ProtonTelemetryData();

        telemetryData.setTime(Double.parseDouble(data[0]));
        telemetryData.setAltitude(Double.parseDouble(data[1]));
        telemetryData.setVelocity(Double.parseDouble(data[2]));
        telemetryData.setAccelerationG(Double.parseDouble(data[3]));
        telemetryData.setAccelerationFPSS(Double.parseDouble(data[4]));
        telemetryData.setVelocityAccel(Double.parseDouble(data[5]));
        telemetryData.setAltimeterAccel(Double.parseDouble(data[6]));
        telemetryData.setfAltitude(Double.parseDouble(data[7]));
        telemetryData.setfVelocity(Double.parseDouble(data[8]));
        telemetryData.setfAccelerationG(Double.parseDouble(data[9]));
        telemetryData.setfAccelerationFPSS(Double.parseDouble(data[10]));
        telemetryData.setfVelocityAccel(Double.parseDouble(data[11]));
        telemetryData.setfAltitudeAccel(Double.parseDouble(data[12]));

        return telemetryData;
    }

}
