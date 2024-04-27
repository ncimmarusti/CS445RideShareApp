package com.example.cs445rideshare;


import java.util.Calendar;

/**
 * This class contains information and methods for when the user is currently being driven by the driver.
 * It contains information on the start and end times and distances as well as the identities of
 * the driver and user.
 */

public class CurrentRide {
    private static int nextId = 1;
    private int id;
    private UserInfo passenger;
    private UserInfo driver;
    private LocationInfo startLocation;
    private LocationInfo endLocation;
    private Calendar startTime;
    private Calendar endTime;
    private RideStatus rideStatus;
    private RideCostEstimator cost;

    public CurrentRide(UserInfo passenger, UserInfo driver, LocationInfo startLocation, LocationInfo endLocation,
                       Calendar startTime, Calendar endTime, RideStatus rideStatus, RideCostEstimator cost) {
        this.id = nextId++;
        this.passenger = passenger;
        this.driver = driver;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rideStatus = rideStatus;
        this.cost = cost;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public UserInfo getPassenger() {
        return passenger;
    }

    public void setPassenger(UserInfo passenger) {
        this.passenger = passenger;
    }

    public UserInfo getDriver() {
        return driver;
    }

    public void setDriver(UserInfo driver) {
        this.driver = driver;
    }

    public LocationInfo getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LocationInfo startLocation) {
        this.startLocation = startLocation;
    }

    public LocationInfo getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LocationInfo endLocation) {
        this.endLocation = endLocation;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public RideCostEstimator getCost() {
        return cost;
    }

    public void setCost(RideCostEstimator cost) {
        this.cost = cost;
    }
}