package com.rocketcharts.altimeter;

import com.rocketcharts.models.EventData;
import com.rocketcharts.models.telemetry.QuantumTelemetryData;
import com.rocketcharts.models.telemetry.TelemetryData;

public class QuantumAltimeter implements Altimeter {
    protected static final String HEADER = "T,Alt,Veloc,FAlt,FVeloc,LDA,LowV,Apogee,N-O,Drogue,Main";

    protected static final String[] LABEL_STRINGS = { "time", "alt", "vel", "fAlt", "fVel", "lda", "lowV", "apo", "no",
            "dro", "main" };
    protected static final String[] DESCRIPTION_STRINGS = { "Time", "Altitude", "Velocity", "Filtered Altitude",
            "Filtered Velocity", "Launch Detection Altitude", "Low Velocity",
            "Apogee", "Nose Over", "Drogue", "Main" };

    private static final int EVENT_DATA_START_COL = 5;
    private static final int EVENT_DATA_END_COL = 10;

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

    @Override
    public EventData getEventData(String[] data) {
        Double time = Double.parseDouble(data[0]);
        Double eventAltData = Double.parseDouble(data[3]);

        Integer result = findEventData(data);

        if (result != null)
            return new EventData(DESCRIPTION_STRINGS[result], time, Double.parseDouble(data[result]), eventAltData);
        else
            return null;
    }

    @Override
    public String getEventKey(String[] data) {
        Integer result = findEventData(data);

        if (result != null)
            return LABEL_STRINGS[result];
        else
            return null;
    }

    private static Integer findEventData(String[] data) {
        Double eventData;

        for (int i = EVENT_DATA_START_COL; i <= EVENT_DATA_END_COL; i++) {
            eventData = Double.parseDouble(data[i]);
            if (eventData > 0) {
                return i;
            }
        }

        return null;
    }

    public static boolean isMatch(String firstLine) {
        return firstLine.equals(HEADER);
    }
}
