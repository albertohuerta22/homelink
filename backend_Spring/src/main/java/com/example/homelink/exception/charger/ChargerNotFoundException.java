package com.example.homelink.exception.charger;

public class ChargerNotFoundException extends RuntimeException {
    public ChargerNotFoundException(String message) {
        super(message);
    }
}
