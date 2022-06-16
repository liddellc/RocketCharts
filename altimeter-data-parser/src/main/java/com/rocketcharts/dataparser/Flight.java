package com.rocketcharts.dataparser;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rocketcharts.dataparser.altimeter.AltimeterDataFile;

public class Flight {
    public Flight(String csvFile) {
        this(csvFile, null);
    }

     public Flight(String csvFile, MetaData metaData) {
        this.metaData = metaData;
        this.data = null;

        try {
            altimeterDataFile = new AltimeterDataFile(csvFile);
            altimeterDataFile.validateFile();
        } catch(Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Issue", e);
        }
   }

    private AltimeterDataFile altimeterDataFile;
    private MetaData metaData;
    private List<TelemetryData> data;
    private List<EventData> eventData;

    public void readFile() {
        FlightData flightData = altimeterDataFile.parseFile();
        setData(flightData.data);
        setEventData(flightData.events);
    }

    public List<TelemetryData> getData() {
        return data;
    }

    public void setData(List<TelemetryData> data) {
        this.data = data;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<EventData> getEventData() {
        return eventData;
    }

    public void setEventData(List<EventData> eventData) {
        this.eventData = eventData;
    }
}
