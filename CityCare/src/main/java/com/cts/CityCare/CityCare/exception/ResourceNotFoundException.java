package com.cts.CityCare.CityCare.exception;


//Usage: When an Admin tries to find a Staff member or a Facility that doesn't exist in the database.

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
