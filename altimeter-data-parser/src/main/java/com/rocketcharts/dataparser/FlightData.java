package com.rocketcharts.dataparser;

import java.util.List;

public class FlightData {
    public final List<TelemetryData> data;
    public final EventData events;

    public FlightData(List<TelemetryData> data, EventData events) {
        this.data = data;
        this.events = events;
    }
}
