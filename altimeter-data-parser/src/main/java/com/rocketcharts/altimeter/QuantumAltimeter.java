package com.rocketcharts.models.altimeter;

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
        EventData eventData = new EventData();

        // event data only occurs once per line so if we find a value we're done
        for (int i = EVENT_DATA_START_COL; i <= EVENT_DATA_END_COL; i++) {
            Double value = Double.parseDouble(data[i]);
            if (value > 0) {
                eventData.setTime(Double.parseDouble(data[0]));
                eventData.setName(DESCRIPTION_STRINGS[i]);
                eventData.setData(value);
                eventData.setAltData(Double.parseDouble(data[3]));
                break;
            }
        }

        return eventData;
    }

    @Override
    public String getEventDataKey(String[] data) {
        for (int i = EVENT_DATA_START_COL; i <= EVENT_DATA_END_COL; i++) {
            Double value = Double.parseDouble(data[i]);
            if (value > 0) {
                return LABEL_STRINGS[i];
            }
        }

        return null;
    }

    public static boolean isMatch(String firstLine) {
        return firstLine.equals(HEADER);
    }

}
