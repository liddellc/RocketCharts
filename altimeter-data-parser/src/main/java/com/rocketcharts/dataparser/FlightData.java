package com.rocketcharts.dataparser;

import java.util.List;

public class FlightData {
    public final List<TelemetryData> data;
    public final List<EventData> events;

    public FlightData(List<TelemetryData> data, List<EventData> events) {
        this.data = data;
        this.events = events;
    }
}
