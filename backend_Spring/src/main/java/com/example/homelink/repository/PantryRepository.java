package com.example.homelink.repository;

import com.example.homelink.entity.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantryRepository extends JpaRepository<Pantry, Long> {
    // You can add custom query methods if needed
}
