package com.rocketcharts.exceptions;

public class InvalidAltimeterException extends Exception { 
    public InvalidAltimeterException(String errorMessage) {
        super(errorMessage);
    }
}