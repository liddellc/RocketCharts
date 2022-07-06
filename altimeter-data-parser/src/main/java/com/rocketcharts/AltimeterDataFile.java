package com.rocketcharts;

import java.io.BufferedReader;
import java.nio.channels.Channels;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.rocketcharts.models.EventData;
import com.rocketcharts.models.FlightData;
import com.rocketcharts.exceptions.InvalidAltimeterException;
import com.rocketcharts.models.telemetry.TelemetryData;

public class AltimeterDataFile {

    private String projectId;
    private String bucketName;
    private String objectName;
    private Altimeter model;
    private BufferedReader reader;

    public AltimeterDataFile(String projectId, String bucketName, String objectName) throws InvalidAltimeterException {
        this.projectId = projectId;
        this.bucketName = bucketName;
        this.objectName = objectName;

        if (!validateFile()) {
            throw new InvalidAltimeterException("Altimeter data file is not recoginzed/supported.");
        }
    }
    
    public boolean validateFile() {
        if (projectId == null || bucketName == null || objectName == null)
            return false;

        try {
            Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
            ReadChannel sReader = storage.reader(BlobId.of(bucketName, objectName));
            reader = new BufferedReader(Channels.newReader(sReader, "UTF8"));

            model = Altimeter.getAltimeter(reader.readLine());
            
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
    public String getProjectId() {
        return projectId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
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