package com.rocketcharts.dataparser.altimeter.eggtimer;

import com.rocketcharts.dataparser.EventData;
import com.rocketcharts.dataparser.TelemetryData;

public class QuantumTelemetryData extends TelemetryData {

    public final double velocity;
    public final double fAltitude;
    public final double fVelocity;
 
    public static final int COLUMNS = 11;
    public static final String HEADER = "T,Alt,Veloc,FAlt,FVeloc,LDA,LowV,Apogee,N-O,Drogue,Main";

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
        return new EventData(Integer.parseInt(data[5]), 
                                    Integer.parseInt(data[6]),
                                    Integer.parseInt(data[7]),
                                    Integer.parseInt(data[8]),
                                    Integer.parseInt(data[9]),
                                    Integer.parseInt(data[10]) );
    }
}
