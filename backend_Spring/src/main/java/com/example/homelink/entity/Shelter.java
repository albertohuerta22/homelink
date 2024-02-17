package com.example.homelink.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Shelter {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;
    private String centerName;
    private String borough;
    private String address;
    private double latitude;
    private double longitude;
    private String comments;

    public Shelter() {
        // Default constructor required by JPA
    }

     public Shelter(String centerName, String borough, String address, double latitude, double longitude, String comments) {
        this.centerName = centerName;
        this.borough = borough;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    // Getters and setters

    public void setId(Long id) {
        this.id = id;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getBorough() {
        return this.borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getComments(){
        return comments;
    }

    public void setComments(String comments){
        this.comments = comments;
    }
}
