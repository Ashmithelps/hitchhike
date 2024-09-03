// src/main/java/com/example/hitchhikking_app/controller/RideController.java
package com.example.hitchhikking_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hitchhikking_app.model.Ride;
import com.example.hitchhikking_app.service.RideService;

@Controller
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/add")
    public String addRide(@RequestBody Ride ride, Model model) {
        Ride savedRide = rideService.saveRide(ride);
        model.addAttribute("ride", savedRide);
        return "ride-details"; // Redirects to a view named ride-details.html
    }

    @GetMapping("/search")
    public String searchRides(@RequestParam String destination, Model model) {
        List<Ride> rides = rideService.findRidesByDestination(destination);
        model.addAttribute("rides", rides);
        return "search-results"; // Redirects to a view named search-results.html
    }
}
