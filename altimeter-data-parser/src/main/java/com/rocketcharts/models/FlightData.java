package com.rocketcharts.models;

import java.util.Map;

import com.rocketcharts.models.telemetry.TelemetryData;

public class FlightData {
    public final Map<String, TelemetryData> telemetryData;
    public final Map<String, EventData> eventData;
    private MetaData metaData;

    public FlightData(Map<String, TelemetryData> telemetryData, Map<String, EventData> eventData) {
        this.telemetryData = telemetryData;
        this.eventData = eventData;
        this.metaData = null;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
