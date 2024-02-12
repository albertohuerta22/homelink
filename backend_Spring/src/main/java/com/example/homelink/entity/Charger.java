package com.example.homelink.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Charger {

    
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;
    // private String name;
    private String streetAddress;
    private String location;
    private double latitude;
    private double longitude;

    public Charger() {
        // Default constructor required by JPA
    }

     public Charger(String streetAddress, String location, double latitude, double longitude) {
        // this.name = name;
        this.streetAddress = streetAddress;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    // Getters and setters

    public void setId(Long id) {
        this.id = id;
    }

    // public String getName(){
    //     return name;
    // }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setName(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getLocation() {
        return location;
    }

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
