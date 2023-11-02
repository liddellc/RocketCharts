package com.rocketcharts.altimeter;

import com.rocketcharts.models.telemetry.QuantumTelemetryData;
import com.rocketcharts.models.telemetry.TelemetryData;

public class QuantumAltimeter extends Altimeter {

    protected static final String[] LABEL_STRINGS = {
            "time",
            "alt",
            "vel",
            "fAlt",
            "fVel",
            "lda",
            "lowV",
            "apo",
            "no",
            "dro",
            "main" };

    protected static final String[] DESCRIPTION_STRINGS = {
            "Time",
            "Altitude",
            "Velocity",
            "Filtered Altitude",
            "Filtered Velocity",
            "Launch Detection Altitude",
            "Low Velocity",
            "Apogee",
            "Nose Over",
            "Drogue",
            "Main" };

    public QuantumAltimeter() {
        this.setHeader("T,Alt,Veloc,FAlt,FVeloc,LDA,LowV,Apogee,N-O,Drogue,Main");
        this.setLabels(new String[] {
                "time",
                "alt",
                "vel",
                "fAlt",
                "fVel",
                "lda",
                "lowV",
                "apo",
                "no",
                "dro",
                "main" });
        this.setDescriptions(new String[] {
                "Time",
                "Altitude",
                "Velocity",
                "Filtered Altitude",
                "Filtered Velocity",
                "Launch Detection Altitude",
                "Low Velocity",
                "Apogee",
                "Nose Over",
                "Drogue",
                "Main" });
        this.setTimeCol(0);
        this.setDataCol(1);
        this.setFiltDataCol(3);
        this.setEventDataStartCol(5);
        this.setEventDataEndCol(10);
    }

    @Override
    public TelemetryData getTelemetryData(String[] data) {
        QuantumTelemetryData telemetryData = new QuantumTelemetryData();

        telemetryData.setTime(Double.parseDouble(data[0]));
        telemetryData.setAltitude(Double.parseDouble(data[1]));
        telemetryData.setVelocity(Double.parseDouble(data[2]));
        telemetryData.setfAltitude(Double.parseDouble(data[3]));
        telemetryData.setfVelocity(Double.parseDouble(data[4]));

        return telemetryData;
    }
}
