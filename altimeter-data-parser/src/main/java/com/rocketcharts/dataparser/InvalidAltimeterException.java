package com.rocketcharts.dataparser;

public class InvalidAltimeterException extends Exception { 
    public InvalidAltimeterException(String errorMessage) {
        super(errorMessage);
    }
}