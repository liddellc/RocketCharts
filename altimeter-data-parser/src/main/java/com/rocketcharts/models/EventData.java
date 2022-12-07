package com.rocketcharts.models;

public class EventData {
    // events
    private String name;
    private Double time;
    private Double data;
    private Double altData;

    public EventData() {
        this.name = null;
        this.time = null;
        this.data = null;
        this.altData = null;
    }

    public EventData(String name, Double time, Double data, Double altData) {
        this.name = name;
        this.time = time;
        this.data = data;
        this.altData = altData;
    }

    public String getName() {
        return name;
    }

    public Double getTime() {
        return time;
    }

    public Double getData() {
        return data;
    }

    public Double getAltData() {
        return altData;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public void setAltData(Double altData) {
        this.altData = altData;
    }
}
