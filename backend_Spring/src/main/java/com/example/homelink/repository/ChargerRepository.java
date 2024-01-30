package com.example.homelink.repository;

import com.example.homelink.entity.Charger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargerRepository extends JpaRepository<Charger, Long> {
    // You can add custom query methods if needed
}
