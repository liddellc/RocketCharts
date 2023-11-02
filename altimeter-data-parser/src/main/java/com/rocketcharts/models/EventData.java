package com.rocketcharts.models;

public class EventData {
    // events
    private String label;
    private String desc;
    private Double time;
    private Double data;
    private Double altData;

    public EventData() {
        this.label = null;
        this.desc = null;
        this.time = null;
        this.data = null;
        this.altData = null;
    }

    public EventData(String label, String desc, Double time, Double data, Double altData) {
        this.label = label;
        this.desc = desc;
        this.time = time;
        this.data = data;
        this.altData = altData;
    }

    public String getLabel() {
        return label;
    }

    public String getDesc() {
        return desc;
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

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
