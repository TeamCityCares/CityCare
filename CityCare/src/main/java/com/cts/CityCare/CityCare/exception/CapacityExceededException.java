package com.cts.CityCare.CityCare.exception;


//Usage: Specific to your Facility logic. If an Admin tries to admit a patient but the totalCapacity
// of the facility is already full.

public class CapacityExceededException extends RuntimeException{
    public CapacityExceededException(String message) {
        super(message);
    }
}
