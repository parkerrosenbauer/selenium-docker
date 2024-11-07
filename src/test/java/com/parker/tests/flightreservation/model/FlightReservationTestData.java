package com.parker.tests.flightreservation.model;

public record FlightReservationTestData(
    String firstName,
    String lastName,
    String email,
    String password,
    String street,
    String city,
    String zip,
    boolean isRoundTrip,
    String numOfPassengers,
    String departingLocation,
    String arrivingLocation,
    String serviceClass,
    String expectedPrice
) {}
