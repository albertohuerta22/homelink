package com.example.homelink.exception.shelter;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    // Optionally, add constructors for throwable causes or other details
}
