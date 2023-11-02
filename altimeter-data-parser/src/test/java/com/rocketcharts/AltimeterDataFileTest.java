package com.rocketcharts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

import com.rocketcharts.exceptions.InvalidAltimeterException;
import com.rocketcharts.models.FlightData;
import com.rocketcharts.models.MetaData;

class AltimeterDataFileTest {
    protected static final String QUANTUM_HEADER = "T,Alt,Veloc,FAlt,FVeloc,LDA,LowV,Apogee,N-O,Drogue,Main";
    protected static final String QUANTUM_DATA = "1.30,211.00,280.00,167.76,216.19,211,0,0,0,0,0";
    protected static final String FILENAME = "2020-07-18_Scorpion_on_I245G.csv";

    BufferedReader reader;

    public AltimeterDataFileTest() {
        StringBuilder builder = new StringBuilder();
        builder.append(QUANTUM_HEADER);
        builder.append('\n');
        builder.append(QUANTUM_DATA);
        builder.append('\n');
        reader = new BufferedReader(new StringReader(builder.toString()));
    }

    @Test
    void testParseFile() {
        try {
            AltimeterDataFile dataFile = new AltimeterDataFile(reader);
            FlightData fData = dataFile.parseFile();
            assertEquals(1.3, fData.telemetryData.get(0).getTime());
            assertEquals(1.3, fData.eventData.get(0).getTime());
            assert (true);
        } catch (InvalidAltimeterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testParseMetaDataFromFilename() {
        try {
            AltimeterDataFile dataFile = new AltimeterDataFile(reader);
            MetaData meta = dataFile.parseMetaDataFromFilename(FILENAME);
            assertEquals("2020-07-18", meta.date);
            assertEquals("Scorpion", meta.rocket);
            assertEquals("I245G", meta.motor);
        } catch (InvalidAltimeterException e) {
            e.printStackTrace();
        }
    }
}
