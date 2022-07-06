package com.rocketcharts;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rocketcharts.models.EventData;
import com.rocketcharts.models.FlightData;
import com.rocketcharts.models.MetaData;
import com.rocketcharts.models.telemetry.TelemetryData;

public class Flight {
    public Flight(String projectId, String bucketName, String objectName) {
        this(projectId, bucketName, objectName, null);
    }

     public Flight(String projectId, String bucketName, String objectName, MetaData metaData) {
        this.metaData = metaData;
        this.telemetryData = null;

        try {
            altimeterDataFile = new AltimeterDataFile(projectId, bucketName, objectName);
            altimeterDataFile.validateFile();
        } catch(Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Issue", e);
        }
   }

    private AltimeterDataFile altimeterDataFile;
    private MetaData metaData;
    private Map<String, TelemetryData> telemetryData;
    private Map<String, EventData> eventData;

    public void readFile() {
        FlightData flightData = altimeterDataFile.parseFile();
        setTelemetryData(flightData.data);
        setEventData(flightData.events);
    }

    public Map<String, TelemetryData> getTelemetryData() {
        return telemetryData;
    }

    public void setTelemetryData(Map<String, TelemetryData> telemetryData) {
        this.telemetryData = telemetryData;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Map<String, EventData> getEventData() {
        return eventData;
    }

    public void setEventData(Map<String, EventData> eventData) {
        this.eventData = eventData;
    }
}
