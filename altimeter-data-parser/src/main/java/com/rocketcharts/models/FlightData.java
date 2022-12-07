package com.rocketcharts.models;

import java.util.Map;

import com.rocketcharts.models.telemetry.TelemetryData;

public class FlightData {
    public final Map<String, TelemetryData> data;
    public final Map<String, EventData> events;
    private MetaData meta;

    public FlightData(Map<String, TelemetryData> data, Map<String, EventData> events) {
        this.data = data;
        this.events = events;
        this.meta = null;
    }

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }
}
