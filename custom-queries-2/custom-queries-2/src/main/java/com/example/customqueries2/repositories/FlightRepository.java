package com.example.customqueries2.repositories;

import com.example.customqueries2.entities.Flight;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    List<Flight> findAllByOrderByFromAirportAsc(Pageable pageable);

    List<Flight> findAllByStatusOrStatus(Flight.Status p1, Flight.Status p2);
}
