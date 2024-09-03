// src/main/java/com/example/hitchhikingapp/repository/RideRepository.java
package com.example.hitchhikking_app.repository;

import com.example.hitchhikking_app.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByDestination(String destination);
}
