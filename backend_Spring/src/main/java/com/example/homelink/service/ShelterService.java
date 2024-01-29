package com.example.homelink.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//my local files
import com.example.homelink.entity.Shelter;
import com.example.homelink.repository.ShelterRepository;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    public Optional<Shelter> getShelterById(Long id) {
    System.out.println("Searching for shelter with ID: " + id);
    Optional<Shelter> shelter = shelterRepository.findById(id);
    
    if (shelter.isPresent()) {
        System.out.println("Found shelter: " + shelter.get());
    } else {
        System.out.println("Shelter not found for ID: " + id);
    }
    
    return shelter;
}

    // public Shelter saveShelter(Shelter shelter) {
    //     return shelterRepository.save(shelter);
    // }

    // public void deleteShelter(UUID id) {
    //     shelterRepository.deleteById(id);
    // }

    
}
