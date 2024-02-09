package com.example.homelink.dto;

public class ShelterDTO {
    private String centerName;
    private String borough;
    private String address;
    private Integer latitude;
    private Integer longitude;

    // Constructors, if needed

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

    public Integer getLatitude(){
        return latitude;
    }

    public void setLatitude(Integer latitude){
        this.latitude = latitude;
    }

    public Integer getLongitude(){
        return longitude;
    }

    public void setLongitude(Integer longitude){
        this.longitude = longitude;
    }
}

