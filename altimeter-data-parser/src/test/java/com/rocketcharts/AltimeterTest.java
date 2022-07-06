package com.rocketcharts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.rocketcharts.models.EventData;
import com.rocketcharts.models.telemetry.TelemetryData;
import com.rocketcharts.models.telemetry.QuantumTelemetryData;

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
        String oneLessColumn = etQuantumHeader.substring(0, etQuantumHeader.lastIndexOf(',')-1);
        String oneMoreColumn = etQuantumHeader.concat(",0");

        Pattern pattern = Pattern.compile(",");
        
        String[] goodData = pattern.split(goodString);
        TelemetryData goodResult = Altimeter.parseData(goodData, Altimeter.ET_QUANTUM);
        assertInstanceOf(QuantumTelemetryData.class, goodResult);

        String[] oneLessColumnData = pattern.split(oneLessColumn);
        assertNull(Altimeter.parseData(oneLessColumnData, Altimeter.ET_QUANTUM));

        String[] oneMoreColumnData = pattern.split(oneMoreColumn);
        assertNull(Altimeter.parseData(oneMoreColumnData, Altimeter.ET_QUANTUM));
    }

    @Test
    void testParseEventData() {
        String goodString = etQuantumData;

        Pattern pattern = Pattern.compile(",");
        
        String[] goodData = pattern.split(goodString);
        EventData goodResult = Altimeter.parseEventData(goodData, Altimeter.ET_QUANTUM);
        assertInstanceOf(EventData.class, goodResult);
    }
}
