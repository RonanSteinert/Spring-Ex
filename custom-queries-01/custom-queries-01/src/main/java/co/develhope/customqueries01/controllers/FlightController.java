package co.develhope.customqueries01.controllers;

import co.develhope.customqueries01.entities.Flight;
import co.develhope.customqueries01.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("")
    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    @PostMapping("")
    public List<Flight> createFlights(){
        List<Flight> flights = new ArrayList<>();
        Random random = new Random();
        for (long i = 1; i < 51; i++) {
            Flight flight = new Flight(
                    i,
                    "Flight " + i,
                    "From Airport " + random.nextInt(10),
                    "To Airport " + random.nextInt(10),
                    Flight.Status.ON_TIME);
            flights.add(flightRepository.save(flight));
        }
        return flights;
    }
}
