package com.example.cs445rideshare;

import java.util.Calendar;

/**
 * This class handles the ride request and creates a new ride when accepted.
 */
public class RideRequest {
    private LocationInfo startLocation;
    private LocationInfo endLocation;
    private LocationInfo userLocation; // User's current location
    private LocationInfo driverLocation; // Driver's current location
    private boolean rideAccepted;

    // Constructor
    public RideRequest(LocationInfo startLocation, LocationInfo endLocation, LocationInfo userLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.userLocation = userLocation;
        this.rideAccepted = false; // Ride not yet accepted
    }

    // Getters and setters for start and end locations, user location, and ride accepted status

    public void acceptRide() {
        this.rideAccepted = true;
    }

    public boolean isRideAccepted() {
        return rideAccepted;
    }

    public CurrentRide startRide(UserInfo passenger, UserInfo driver, Calendar startTime, Calendar endTime, RideStatus rideStatus, RideCostEstimator cost) {
        if (!rideAccepted) {
            throw new IllegalStateException("Cannot start ride before it is accepted by the driver");
        }

        // Create CurrentRide instance with relevant information
        CurrentRide currentRide = new CurrentRide(passenger, driver, startLocation, endLocation, startTime, endTime, rideStatus, cost);

        // Reset ride request fields
        startLocation = null;
        endLocation = null;
        userLocation = null;
        driverLocation = null;
        rideAccepted = false;

        return currentRide;
    }
}

