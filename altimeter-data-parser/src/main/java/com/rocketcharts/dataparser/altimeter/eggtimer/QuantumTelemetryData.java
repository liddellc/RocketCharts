package com.rocketcharts.dataparser.altimeter.eggtimer;

import com.rocketcharts.dataparser.EventData;
import com.rocketcharts.dataparser.TelemetryData;

public class QuantumTelemetryData extends TelemetryData {

    public final double velocity;
    public final double fAltitude;
    public final double fVelocity;
 
    public static final int COLUMNS = 11;
    public static final String HEADER = "T,Alt,Veloc,FAlt,FVeloc,LDA,LowV,Apogee,N-O,Drogue,Main";
    protected static final String[] LABELS = {"Time", "Altitude", "Velocity", "Filtered Altitude", 
                                            "Filtered Velocity", "Launch Detection Altitude", "Low Velocity",
                                            "Apogee", "Nose Over", "Drogue", "Main"};

    public QuantumTelemetryData(double time, double altitude, double velocity, double fAltitude, double fVelocity) {
        super(time, altitude);
        this.velocity = velocity;
        this.fAltitude = fAltitude;
        this.fVelocity = fVelocity;
     }

    public static QuantumTelemetryData parseData(String[] data) {
        return new QuantumTelemetryData(Double.parseDouble(data[0]), 
                                             Double.parseDouble(data[1]), 
                                             Double.parseDouble(data[2]), 
                                             Double.parseDouble(data[3]), 
                                             Double.parseDouble(data[4]) );
 
    }

    public static EventData parseEventData(String[] data) {
        Double time = Double.parseDouble(data[0]);
        Double eventData;
        Double eventAltData = Double.parseDouble(data[3]);
       
        for(int i = 5; i < 11; i++) {
            eventData = Double.parseDouble(data[i]);
            if(eventData > 0) {
                return new EventData(LABELS[i], time, eventData, eventAltData);
            }
        }

        return null;
     }
}
