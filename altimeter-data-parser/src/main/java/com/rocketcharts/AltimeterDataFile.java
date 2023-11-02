package com.rocketcharts;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.rocketcharts.models.EventData;
import com.rocketcharts.models.FlightData;
import com.rocketcharts.models.MetaData;
import com.rocketcharts.altimeter.Altimeter;
import com.rocketcharts.altimeter.AltimeterFactory;
import com.rocketcharts.exceptions.InvalidAltimeterException;
import com.rocketcharts.models.telemetry.TelemetryData;

public class AltimeterDataFile {

    private Altimeter altimeter;
    private BufferedReader reader;

    public AltimeterDataFile(BufferedReader reader) throws InvalidAltimeterException {
        this.reader = reader;
        try {
            AltimeterFactory af = new AltimeterFactory();
            this.altimeter = af.getAltimeter(reader.readLine());
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Issue", e);
        }
        if (this.altimeter == null)
            throw new InvalidAltimeterException("Altimeter data file unsupported");
    }

    public FlightData parseFile() {
        List<TelemetryData> flightData = new ArrayList<>();
        List<EventData> eventData = new ArrayList<>();
        Pattern pattern = Pattern.compile(",");
        try {
            getReader().lines().forEach(line -> {
                String[] sData = pattern.split(line);
                TelemetryData tData = altimeter.getTelemetryData(sData);
                flightData.add(tData);
                EventData eData = altimeter.getEventData(sData);
                if (eData != null)
                    eventData.add(eData);
            });
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error while reading altimeter data file.", e);
        }

        return new FlightData(flightData, eventData);
    }

    public MetaData parseMetaDataFromFilename(String fileName) {
        MetaData metaData = null;
        // pull data from file name if it matches the format
        String[] fields = fileName.split("\\.")[0].split("_");
        if (fields.length == 4) {
            metaData = new MetaData(fields[0], "", fields[1], fields[3]);
        }

        return metaData;
    }

    // getters and setters
    public Altimeter getAltimeter() {
        return altimeter;
    }

    public void setAltimeter(Altimeter altimeter) {
        this.altimeter = altimeter;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
}