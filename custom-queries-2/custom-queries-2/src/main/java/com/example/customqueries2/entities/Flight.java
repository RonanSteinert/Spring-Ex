package com.example.customqueries2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    public enum Status {
        ON_TIME,
        DELAYED,
        CANCELLED;

        private static final List<Status> VALUES =

                Collections.unmodifiableList(Arrays.asList(values()));

        private static final int SIZE = VALUES.size();

        private static final Random RANDOM = new Random();

        public static Status randomStatus() {

            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private String fromAirport;
    private String toAirport;
    @Enumerated(EnumType.STRING)
    private Status status;
}

