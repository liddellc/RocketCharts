package com.rocketcharts.models;

import java.util.List;

import com.rocketcharts.models.telemetry.TelemetryData;

public class FlightData {
    public final List<TelemetryData> telemetryData;
    public final List<EventData> eventData;
    private MetaData metaData;

    public FlightData(List<TelemetryData> telemetryData, List<EventData> eventData) {
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
