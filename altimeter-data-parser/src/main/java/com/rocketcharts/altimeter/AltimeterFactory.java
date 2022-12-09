package com.rocketcharts.altimeter;

public class AltimeterFactory {
    private AltimeterFactory() {
    }

    public static Altimeter getAltimeter(String firstLine) {
        if (firstLine == null || firstLine.isEmpty())
            return null;

        if (QuantumAltimeter.isMatch(firstLine))
            return new QuantumAltimeter();
        else
            return null;
    }
}
