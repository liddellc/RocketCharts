package com.rocketcharts.dataparser.altimeter;

import java.util.regex.Pattern;

import com.rocketcharts.dataparser.EventData;
import com.rocketcharts.dataparser.TelemetryData;
import com.rocketcharts.dataparser.altimeter.eggtimer.QuantumTelemetryData;

public enum Altimeter {
    ET_QUANTUM(QuantumTelemetryData.COLUMNS, QuantumTelemetryData.HEADER),
    ET_PROTON(44, null);

    private int columnCount;
    private String headerSignature;

    Altimeter(int columnCount, String headerSignature) {
        this.columnCount = columnCount;
        this.headerSignature = headerSignature;
    }

    public static Altimeter getAltimeter(String firstLine) {
        if (firstLine != null && !firstLine.isEmpty()) {
            String[] header = Pattern.compile(",").split(firstLine);
        
            for( Altimeter a : Altimeter.values() ) {
                if(header.length == a.columnCount && firstLine.equals(a.headerSignature))
                    return a;
            }
        }

        return null;
    }

    public static TelemetryData parseData(String[] data, Altimeter a) {
        if (a == ET_QUANTUM && data.length == ET_QUANTUM.columnCount) {
            return QuantumTelemetryData.parseData(data);
         } else {
            return null;
        }
    }

    public static EventData parseEventData(String[] data, Altimeter a) {
        if (a == ET_QUANTUM && data.length == ET_QUANTUM.columnCount) {
            return QuantumTelemetryData.parseEventData(data);
         } else {
            return null;
        }
    }
}