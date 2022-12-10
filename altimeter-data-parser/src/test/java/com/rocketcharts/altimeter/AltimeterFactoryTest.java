package com.rocketcharts.altimeter;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class AltimeterFactoryTest {
    protected static final String QUANTUM_HEADER = "T,Alt,Veloc,FAlt,FVeloc,LDA,LowV,Apogee,N-O,Drogue,Main";
    protected static final String PROTON_HEADER = "T,Alt,Veloc,AccelG,AccelFPSS,VAccel,AltAccel,Filt_Alt,FVeloc,FAccelG,FAccelFPSS,FAccelVel,FAccelAlt,LDA,MaxV,MaxA,BO#1,LowV,Apogee,N-O,CH1,CH2";

    @Test
    void testGetAltimeter() {
        assertInstanceOf(QuantumAltimeter.class, AltimeterFactory.getAltimeter(QUANTUM_HEADER));
        assertInstanceOf(ProtonAltimeter.class, AltimeterFactory.getAltimeter(PROTON_HEADER));
    }

    @Test
    void testGetAltimeterErrorCases() {
        assertNull(AltimeterFactory.getAltimeter(null));
        assertNull(AltimeterFactory.getAltimeter(""));
    }

    @Test
    void testGetAltimeterNotFoundCase() {
        assertNull(AltimeterFactory.getAltimeter(QUANTUM_HEADER.substring(2)));
    }
}
