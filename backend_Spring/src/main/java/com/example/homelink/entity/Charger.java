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
    private String streetAddress;
    private String location;

    public Charger() {
        // Default constructor required by JPA
    }

     public Charger(String streetAddress, String location, Long id) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    // Getters and setters

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
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

}
