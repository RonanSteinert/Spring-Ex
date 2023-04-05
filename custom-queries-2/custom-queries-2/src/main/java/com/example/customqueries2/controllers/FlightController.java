package com.example.customqueries2.controllers;

import com.example.customqueries2.entities.Flight;
import com.example.customqueries2.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;


    @PostMapping("")
    public List<Flight> createFlights(@RequestParam(required = false) Optional<Integer> n) {
        List<Flight> flights = new ArrayList<>();
        Random random = new Random();
        if (n.isPresent()) {
            for (int i = 0; i < (n.get() + 1); i++) {
                Flight flight = new Flight(
                        i,
                        "Flight " + i,
                        "From Airport " + random.nextInt(1,10),
                        "To Airport " + random.nextInt(1,10),
                        Flight.Status.randomStatus());
                flights.add(flightRepository.save(flight));
            }
        } else {
            for (int i = 0; i < 101; i++) {
                Flight flight = new Flight(
                        i,
                        "Flight " + i,
                        "From Airport " + random.nextInt(1,100),
                        "To Airport " + random.nextInt(1,100),
                        Flight.Status.randomStatus());
                flights.add(flightRepository.save(flight));
            }
        }
        return flights;
    }

    @GetMapping("/order")
    public List<Flight> getAllFlightsOrdered(@RequestParam Integer page,@RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size);
        List<Flight> flightPage = flightRepository.findAllByOrderByFromAirportAsc(pageable);
        return flightPage;
    }

    @GetMapping("/status")
    public List<Flight> getAllOnTimeFlights(){
        List<Flight> onTimeFlights = flightRepository.findAll().stream().filter(flight -> flight.getStatus().equals(Flight.Status.ON_TIME)).toList();
        return onTimeFlights;
    }

    @GetMapping("/statuses")
    public List<Flight> getAllP1OrP2Flights(@RequestParam Flight.Status p1, @RequestParam Flight.Status p2){
        List<Flight> p1OrP2Flights = flightRepository.findAllByStatusOrStatus(p1,p2);
        return p1OrP2Flights;
    }
}
