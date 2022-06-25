package com.rocketcharts.dataparser;

import java.util.Map;

public class FlightData {
    public final Map<String, TelemetryData> data;
    public final Map<String, EventData> events;

    public FlightData(Map<String, TelemetryData> data, Map<String, EventData> events) {
        this.data = data;
        this.events = events;
    }
}
