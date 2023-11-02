package com.rocketcharts.altimeter;

import com.rocketcharts.models.EventData;
import com.rocketcharts.models.telemetry.TelemetryData;

public abstract class Altimeter {
    private String header;
    private String[] labels;
    private String[] descriptions;
    private int timeCol;
    private int dataCol;
    private int filtDataCol;
    private int eventDataStartCol;
    private int eventDataEndCol;

    public abstract TelemetryData getTelemetryData(String[] data);

    public EventData getEventData(String[] data) {
        Double time = Double.parseDouble(data[getTimeCol()]);
        Double eventAltData = Double.parseDouble(data[getFiltDataCol()]);

        Integer result = findEventData(data);

        if (result != null)
            return new EventData(getLabels()[result], getDescriptions()[result], time,
                    Double.parseDouble(data[result]), eventAltData);
        else
            return null;
    }

    public Integer findEventData(String[] data) {
        Double eventData;

        for (int i = getEventDataStartCol(); i <= getEventDataEndCol(); i++) {
            eventData = Double.parseDouble(data[i]);
            if (eventData > 0) {
                return i;
            }
        }

        return null;
    }

    // GETTERS AND SETTERS
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public String[] getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String[] descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isMatch(String firstLine) {
        return firstLine.equals(getHeader());
    }

    public int getTimeCol() {
        return timeCol;
    }

    public void setTimeCol(int timeCol) {
        this.timeCol = timeCol;
    }

    public int getDataCol() {
        return dataCol;
    }

    public void setDataCol(int dataCol) {
        this.dataCol = dataCol;
    }

    public int getFiltDataCol() {
        return filtDataCol;
    }

    public void setFiltDataCol(int filtDataCol) {
        this.filtDataCol = filtDataCol;
    }

    public int getEventDataStartCol() {
        return eventDataStartCol;
    }

    public void setEventDataStartCol(int eventDataStartCol) {
        this.eventDataStartCol = eventDataStartCol;
    }

    public int getEventDataEndCol() {
        return eventDataEndCol;
    }

    public void setEventDataEndCol(int eventDataEndCol) {
        this.eventDataEndCol = eventDataEndCol;
    }
}
