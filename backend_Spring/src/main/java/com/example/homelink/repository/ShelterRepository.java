package com.example.homelink.repository;

import com.example.homelink.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    // You can add custom query methods if needed
}
