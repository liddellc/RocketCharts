package com.rocketcharts.altimeter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.rocketcharts.models.EventData;
import com.rocketcharts.models.telemetry.ProtonTelemetryData;
import com.rocketcharts.models.telemetry.TelemetryData;

public class ProtonAltimeterTest {
    protected static final String PROTON_HEADER = "T,Alt,Veloc,AccelG,AccelFPSS,VAccel,AltAccel,Filt_Alt,FVeloc,FAccelG,FAccelFPSS,FAccelVel,FAccelAlt,LDA,MaxV,MaxA,BO#1,LowV,Apogee,N-O,CH1,CH2";
    protected static final String PROTON_DATA = "18.30,6454.00,80.00,-1.0,-33.15,23.8,6670,6443.03,54.86,-1.0,-33.63,33.5,6718,0,0,0,0,6454,0,0,0,0";

    private ProtonAltimeter alt;
    private String[] data;

    public ProtonAltimeterTest() {
        alt = new ProtonAltimeter();
        data = PROTON_DATA.split(",");
    }

    @Test
    void testGetEventData() {
        EventData eData = alt.getEventData(data);
        assertInstanceOf(EventData.class, eData);
        assertEquals(18.3, eData.getTime());
        assertEquals("Low Velocity", eData.getName());
        assertEquals(6454.0, eData.getData());
        assertEquals(6443.03, eData.getAltData());
    }

    @Test
    void testGetEventKey() {
        assertEquals("lowV", alt.getEventKey(data));
    }

    @Test
    void testGetTelemetryData() {
        TelemetryData tData = alt.getTelemetryData(data);
        assertInstanceOf(ProtonTelemetryData.class, tData);
        assertEquals(18.3, tData.getTime());
        assertEquals(6454.0, tData.getAltitude());
        assertEquals(80.0, ((ProtonTelemetryData) tData).getVelocity());
        assertEquals(-1.0, ((ProtonTelemetryData) tData).getAccelerationG());
        assertEquals(-33.15, ((ProtonTelemetryData) tData).getAccelerationFPSS());
        assertEquals(23.8, ((ProtonTelemetryData) tData).getVelocityAccel());
        assertEquals(6670, ((ProtonTelemetryData) tData).getAltimeterAccel());
        assertEquals(6443.03, ((ProtonTelemetryData) tData).getfAltitude());
        assertEquals(54.86, ((ProtonTelemetryData) tData).getfVelocity());
        assertEquals(-1.0, ((ProtonTelemetryData) tData).getfAccelerationG());
        assertEquals(-33.63, ((ProtonTelemetryData) tData).getfAccelerationFPSS());
        assertEquals(33.5, ((ProtonTelemetryData) tData).getfVelocityAccel());
        assertEquals(6718, ((ProtonTelemetryData) tData).getfAltitudeAccel());
    }

    @Test
    void testIsMatch() {
        assertTrue(ProtonAltimeter.isMatch(PROTON_HEADER));
    }

    @Test
    void testIsMatchNotMatch() {
        assertFalse(ProtonAltimeter.isMatch(PROTON_HEADER.substring(2)));
    }
}
