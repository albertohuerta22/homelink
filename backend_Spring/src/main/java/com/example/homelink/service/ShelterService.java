package com.example.homelink.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//my local files
import com.example.homelink.entity.Shelter;
import com.example.homelink.entity.ShelterRepository;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    // Other methods for handling business logic and interacting with the repository
}
