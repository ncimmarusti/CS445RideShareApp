package com.example.cs445rideshare;

/**
 * This is the LocationInfo class. It is responsible for handling the coordinates of a user and driver.
 * The coordinates can be used to help a driver find a user and vice versa.
 */
public class LocationInfo {
    private int id;
    private double latitude; // Changed to double for better precision
    private double longitude; // Changed to double for better precision

    // Constructor
    public LocationInfo(int id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
