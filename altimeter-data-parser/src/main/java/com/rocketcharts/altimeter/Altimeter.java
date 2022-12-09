package com.rocketcharts.altimeter;

import com.rocketcharts.models.EventData;
import com.rocketcharts.models.telemetry.TelemetryData;

public interface Altimeter {
    public static final String HEADER = "";

    public TelemetryData getTelemetryData(String[] data);

    public EventData getEventData(String[] data);

    public String getEventDataKey(String[] data);
}
