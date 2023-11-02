package com.rocketcharts.altimeter;

public class AltimeterFactory {

    public Altimeter getAltimeter(String firstLine) {
        if (firstLine == null || firstLine.isEmpty())
            return null;

        QuantumAltimeter qa = new QuantumAltimeter();
        if (qa.isMatch(firstLine))
            return qa;

        ProtonAltimeter pa = new ProtonAltimeter();
        if (pa.isMatch(firstLine))
            return pa;
        else
            return null;
    }
}
