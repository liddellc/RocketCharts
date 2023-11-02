package com.rocketcharts.altimeter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.rocketcharts.models.EventData;
import com.rocketcharts.models.telemetry.QuantumTelemetryData;
import com.rocketcharts.models.telemetry.TelemetryData;

class QuantumAltimeterTest {
    protected static final String QUANTUM_HEADER = "T,Alt,Veloc,FAlt,FVeloc,LDA,LowV,Apogee,N-O,Drogue,Main";
    protected static final String QUANTUM_DATA = "1.30,211.00,280.00,167.76,216.19,211,0,0,0,0,0";

    private QuantumAltimeter alt;
    private String[] data;

    public QuantumAltimeterTest() {
        alt = new QuantumAltimeter();
        data = QUANTUM_DATA.split(",");
    }

    @Test
    void testGetEventData() {
        EventData eData = alt.getEventData(data);
        assertInstanceOf(EventData.class, eData);
        assertEquals(1.3, eData.getTime());
        assertEquals("Launch Detection Altitude", eData.getDesc());
        assertEquals(211.0, eData.getData());
        assertEquals(167.76, eData.getAltData());
    }

    @Test
    void testFindEventData() {
        Integer col = alt.findEventData(data);
        assertEquals(5, col);
    }

    @Test
    void testNotFindEventData() {
        String[] temp = data.clone();
        temp[5] = "0";
        Integer col = alt.findEventData(temp);
        assertNull(col);
    }

    @Test
    void testGetTelemetryData() {
        TelemetryData tData = alt.getTelemetryData(data);
        assertInstanceOf(QuantumTelemetryData.class, tData);
        assertEquals(1.3, tData.getTime());
        assertEquals(211.0, tData.getAltitude());
        assertEquals(280.0, ((QuantumTelemetryData) tData).getVelocity());
        assertEquals(167.76, ((QuantumTelemetryData) tData).getfAltitude());
        assertEquals(216.19, ((QuantumTelemetryData) tData).getfVelocity());
    }

    @Test
    void testIsMatch() {
        assertTrue(alt.isMatch(QUANTUM_HEADER));
    }

    @Test
    void testIsMatchNotMatch() {
        assertFalse(alt.isMatch(QUANTUM_HEADER.substring(2)));
    }
}
