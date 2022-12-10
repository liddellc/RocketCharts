package com.rocketcharts.altimeter;

import com.rocketcharts.models.EventData;
import com.rocketcharts.models.telemetry.ProtonTelemetryData;
import com.rocketcharts.models.telemetry.TelemetryData;

public class ProtonAltimeter implements Altimeter {
    protected static final String HEADER = "T,Alt,Veloc,AccelG,AccelFPSS,VAccel,AltAccel,Filt_Alt,FVeloc,FAccelG,FAccelFPSS,FAccelVel,FAccelAlt,LDA,MaxV,MaxA,BO#1,LowV,Apogee,N-O,CH1,CH2";

    protected static final String[] LABEL_STRINGS = { "time", "alt", "vel", "accelG", "accelFPSS", "velAcc", "altAcc",
            "fAlt", "fVel", "fAccelG", "fAccelFPSS", "fVelAcc", "fAltAcc", "lda",
            "maxV", "maxA", "bo1", "lowV", "apo", "no", "dro", "main" };
    protected static final String[] DESCRIPTION_STRINGS = { "Time", "Altitude", "Velocity", "Acceleration Gs",
            "Acceleration FPSS", "Velocity (Accelerometer)", "Altitude (Accelerometer)", "Filtered Altitude",
            "Filtered Velocity", "Filtered Acceleration Gs", "Filtered Acceleration FPSS",
            "Filtered Velocity (Accelerometer)",
            "Filtered Altitude (Accelerometer)", "Launch Detection Altitude", "Max Velocity", "Max Aacceleration",
            "Boost #1", "Low Velocity",
            "Apogee", "Nose Over", "Drogue", "Main" };

    private static final int EVENT_DATA_START_COL = 13;
    private static final int EVENT_DATA_END_COL = 21;

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

    @Override
    public EventData getEventData(String[] data) {
        Double time = Double.parseDouble(data[0]);
        Double eventAltData = Double.parseDouble(data[7]);

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
