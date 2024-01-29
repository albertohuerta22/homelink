package com.example.homelink.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Shelter {

    
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@SequenceGenerator(name="car_generator", sequenceName = "car_seq", allocationSize=50)
    private Long id;

    private String name;
    private Integer capacity;
    private String location;

    public Shelter() {
        // Default constructor required by JPA
    }

     public Shelter(String name, Integer capacity, String location, Long id) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    // Getters and setters

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
   

}
