package com.rocketcharts.models.telemetry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.rocketcharts.models.EventData;

public class QuantumTelemetryDataTest {
    String etQuantumHeader = "T,Alt,Veloc,FAlt,FVeloc,LDA,LowV,Apogee,N-O,Drogue,Main";
    String etQuantumData = "1.30,211.00,280.00,167.76,216.19,211,0,0,0,0,0";

    @Test
    void testParseData() {
        String goodString = etQuantumData;
        String correctColumnBadDataString = etQuantumHeader;
        String emptyString = "";

        Pattern pattern = Pattern.compile(",");

        String[] goodData = pattern.split(goodString);
        QuantumTelemetryData goodResult = QuantumTelemetryData.parseData(goodData);
        assertEquals(1.30, goodResult.time);
        assertEquals(211.00, goodResult.altitude);
        assertEquals(280.00, goodResult.velocity);
        assertEquals(167.76, goodResult.fAltitude);
        assertEquals(216.19, goodResult.fVelocity);

        String[] correctColumnBadData = pattern.split(correctColumnBadDataString);
        assertThrows(NumberFormatException.class, () -> QuantumTelemetryData.parseData(correctColumnBadData));

        String[] emptyStringData = pattern.split(emptyString);
        assertThrows(NumberFormatException.class, () -> QuantumTelemetryData.parseData(emptyStringData));
 
    }

    @Test
    void testParseEventData() {
        String goodString = etQuantumData;
        String correctColumnBadDataString = etQuantumHeader;

        Pattern pattern = Pattern.compile(",");
        
        String[] goodData = pattern.split(goodString);
        EventData goodResult = QuantumTelemetryData.parseEventData(goodData);
        assertEquals(211, goodResult.getData());
        assertEquals(167.76, goodResult.getAltData());

        String[] correctColumnBadData = pattern.split(correctColumnBadDataString);
        assertThrows(NumberFormatException.class, () -> QuantumTelemetryData.parseData(correctColumnBadData));
 
    }

    @Test
    void testParseDataKey() {
        String goodString = etQuantumData;

        Pattern pattern = Pattern.compile(",");

        String[] goodData = pattern.split(goodString);
        String goodResult = QuantumTelemetryData.parseDataKey(goodData);
        assertEquals("1.30", goodResult);
    }

    @Test
    void testParseEventDataKey() {
        String goodString = etQuantumData;

        Pattern pattern = Pattern.compile(",");

        String[] goodData = pattern.split(goodString);
        String goodResult = QuantumTelemetryData.parseEventDataKey(goodData);
        assertEquals("lda", goodResult);
    }
}
