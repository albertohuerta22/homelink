package com.example.homelink.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChargerDTO {
    // @NotBlank(message="Name is required")
    // @Size(min= 1, max = 50, message = "Valid String Required")
    // private String name;
     @NotBlank(message = "Street address is required")
    @Size(max = 100, message = "Street address must not exceed 100 characters")
    private String streetAddress;
    @NotBlank(message = "Location is required")
    @Size(min= 1, max = 50, message = "Valid String Required")
    private String location;
    private double latitude;
    private double longitude;

    // Constructors, if needed

    public ChargerDTO(){
        
    }

    public ChargerDTO(String streetAddress, String location, double latitude, double longitude) {
        // this.name = name;
        this.streetAddress = streetAddress;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter for 'name' NEED NAMES FOR CHARGERS IN THE FUTURE
    // public String getName() {
    //     return name;
    // }

    // // Setter for 'name'
    // public void setName(String name) {
    //     this.name = name;
    // }

    // Getter for 'streetAddress'
    public String getStreetAddress() {
        return streetAddress;
    }

    // Setter for 'streetAddress'
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    // Getter for 'location'
    public String getLocation() {
        return location;
    }

    // Setter for 'location'
    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
}

