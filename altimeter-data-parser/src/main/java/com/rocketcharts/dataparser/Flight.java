package com.rocketcharts.dataparser;

import java.util.List;

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
            System.out.println(e.getMessage());
        }
   }

    private AltimeterDataFile altimeterDataFile;
    private MetaData metaData;
    private List<TelemetryData> data;
    private EventData eventData;

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

    public EventData getEventData() {
        return eventData;
    }

    public void setEventData(EventData eventData) {
        this.eventData = eventData;
    }
}
