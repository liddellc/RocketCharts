package com.rocketcharts.altimeter;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AltimeterFactoryTest {
    protected static final String QUANTUM_HEADER = "T,Alt,Veloc,FAlt,FVeloc,LDA,LowV,Apogee,N-O,Drogue,Main";
    protected static final String PROTON_HEADER = "T,Alt,Veloc,AccelG,AccelFPSS,VAccel,AltAccel,Filt_Alt,FVeloc,FAccelG,FAccelFPSS,FAccelVel,FAccelAlt,LDA,MaxV,MaxA,BO#1,LowV,Apogee,N-O,CH1,CH2";

    private AltimeterFactory altimeterFactory;

    public AltimeterFactoryTest() {
        altimeterFactory = new AltimeterFactory();
    }

    @Test
    void testGetAltimeter() {
        assertInstanceOf(QuantumAltimeter.class, altimeterFactory.getAltimeter(QUANTUM_HEADER));
        assertInstanceOf(ProtonAltimeter.class, altimeterFactory.getAltimeter(PROTON_HEADER));
    }

    @Test
    void testGetAltimeterErrorCases() {
        assertNull(altimeterFactory.getAltimeter(null));
        assertNull(altimeterFactory.getAltimeter(""));
    }

    @Test
    void testGetAltimeterNotFoundCase() {
        assertNull(altimeterFactory.getAltimeter(QUANTUM_HEADER.substring(2)));
    }
}
