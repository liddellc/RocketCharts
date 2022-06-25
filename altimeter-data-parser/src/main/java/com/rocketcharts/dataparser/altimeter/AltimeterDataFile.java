package com.rocketcharts.dataparser.altimeter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.rocketcharts.dataparser.EventData;
import com.rocketcharts.dataparser.FlightData;
import com.rocketcharts.dataparser.InvalidAltimeterException;
import com.rocketcharts.dataparser.TelemetryData;

public class AltimeterDataFile {

    private String fileName;
    private Altimeter model;
    private BufferedReader reader;

    public AltimeterDataFile(String fileName) throws InvalidAltimeterException {
        this.fileName = fileName;

        if (!validateFile()) {
            throw new InvalidAltimeterException("Altimeter data file is not recoginzed/supported.");
        }
    }
    
    public boolean validateFile() {
        if (fileName == null)
            return false;

        try {
            this.reader = new BufferedReader(new FileReader(fileName));
            this.model = Altimeter.getAltimeter(reader.readLine());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public FlightData parseFile() {
        if(model == null)
            return null;
        
        Map<String, TelemetryData> flightData = new HashMap<>();
        Map<String, EventData> eventData = new HashMap<>();
        Pattern pattern = Pattern.compile(",");
        try {
                getReader().lines().skip(1).forEach(line -> {
                    String[] x = pattern.split(line);
                    flightData.put(Altimeter.parseDataKey(x, getModel()), Altimeter.parseData(x, getModel()));
                    String eventKey = Altimeter.parseEventDataKey(x, getModel());
                    if (eventKey != null) eventData.put(eventKey, Altimeter.parseEventData(x, getModel()));
                });
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error while reading altimeter data file.", e);          
        }   
        
        return new FlightData(flightData, eventData);
    }

    // getters and setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Altimeter getModel() {
        return model;
    }

    public void setModel(Altimeter model) {
        this.model = model;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
}