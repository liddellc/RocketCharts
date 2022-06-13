package com.rocketcharts.dataparser.altimeter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.rocketcharts.dataparser.EventData;
import com.rocketcharts.dataparser.TelemetryData;
import com.rocketcharts.dataparser.altimeter.eggtimer.QuantumTelemetryData;

class AltimeterTest {
    String etQuantumHeader = "T,Alt,Veloc,FAlt,FVeloc,LDA,LowV,Apogee,N-O,Drogue,Main";
    String etQuantumData = "1.30,211.00,280.00,167.76,216.19,211,0,0,0,0,0";

    @Test
    void testGetAltimeter() {
        String goodAlt = etQuantumHeader;
        String badAlt = etQuantumHeader.substring(0, etQuantumHeader.length()-2);
        String nullAlt = null;

        Altimeter goodResult = Altimeter.getAltimeter(goodAlt);
        assertEquals(Altimeter.ET_QUANTUM, goodResult);
        
        Altimeter badResult = Altimeter.getAltimeter(badAlt);
        assertEquals(null, badResult);

        Altimeter nullResult = Altimeter.getAltimeter(nullAlt);
        assertEquals(null, nullResult);
    }

    @Test
    void testParseData() {
        String goodString = etQuantumData;
        String correctColumnBadDataString = etQuantumHeader;
        String oneLessColumn = etQuantumHeader.substring(0, etQuantumHeader.lastIndexOf(',')-1);
        String oneMoreColumn = etQuantumHeader.concat(",0");
        String emptyString = "";

        Pattern pattern = Pattern.compile(",");
        
        String[] goodData = pattern.split(goodString);
        TelemetryData goodResult = Altimeter.parseData(goodData, Altimeter.ET_QUANTUM);
        assertInstanceOf(QuantumTelemetryData.class, goodResult);
        assertEquals(1.30, ((QuantumTelemetryData)goodResult).time);
        assertEquals(211.00, ((QuantumTelemetryData)goodResult).altitude);
        assertEquals(280.00, ((QuantumTelemetryData)goodResult).velocity);
        assertEquals(167.76, ((QuantumTelemetryData)goodResult).fAltitude);
        assertEquals(216.19, ((QuantumTelemetryData)goodResult).fVelocity);

        String[] correctColumnBadData = pattern.split(correctColumnBadDataString);
        assertThrows(NumberFormatException.class, () -> Altimeter.parseData(correctColumnBadData, Altimeter.ET_QUANTUM));

        String[] oneLessColumnData = pattern.split(oneLessColumn);
        assertNull(Altimeter.parseData(oneLessColumnData, Altimeter.ET_QUANTUM));

        String[] oneMoreColumnData = pattern.split(oneMoreColumn);
        assertNull(Altimeter.parseData(oneMoreColumnData, Altimeter.ET_QUANTUM));

        String[] emptyStringData = pattern.split(emptyString);
        assertNull(Altimeter.parseData(emptyStringData, Altimeter.ET_QUANTUM));
    }

    @Test
    void testParseEventData() {
        String goodString = etQuantumData;
        String correctColumnBadDataString = etQuantumHeader;

        Pattern pattern = Pattern.compile(",");
        
        String[] goodData = pattern.split(goodString);
        EventData goodResult = Altimeter.parseEventData(goodData, Altimeter.ET_QUANTUM);
        assertInstanceOf(EventData.class, goodResult);
        assertEquals(211, goodResult.getLaunchDetectionAltitude());

        String[] correctColumnBadData = pattern.split(correctColumnBadDataString);
        assertThrows(NumberFormatException.class, () -> Altimeter.parseData(correctColumnBadData, Altimeter.ET_QUANTUM));
    }
}
