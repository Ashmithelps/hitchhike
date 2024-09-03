// src/main/java/com/example/hitchhikingapp/service/RideService.java
package com.example.hitchhikking_app.service;

import com.example.hitchhikking_app.model.Ride;
import com.example.hitchhikking_app.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public Ride saveRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public List<Ride> findRidesByDestination(String destination) {
        return rideRepository.findByDestination(destination);
    }
}
