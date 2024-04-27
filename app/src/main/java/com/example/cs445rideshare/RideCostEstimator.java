package com.example.cs445rideshare;

/**
 * This class is used to estimate the cost of the ride using google maps api and the start and end
 * locations.
 */
public class RideCostEstimator {

    // Method to calculate the estimated cost of the ride based on start and end locations
    public static double calculateEstimatedCost(LocationInfo startLocation, LocationInfo endLocation) {
        // Use the start and end locations to calculate the distance between them
        // You can use the Haversine formula or Google Maps Distance Matrix API to calculate the distance

        // For demonstration purposes, let's assume a simple formula to calculate the cost
        double distance = calculateDistance(startLocation, endLocation);
        double costPerKilometer = 1.5; // Assume $1.5 per kilometer
        return distance * costPerKilometer;
    }

    // Method to calculate the distance between two locations (Haversine formula)
    private static double calculateDistance(LocationInfo startLocation, LocationInfo endLocation) {
        double earthRadius = 6371; // Radius of the Earth in kilometers

        double startLat = Math.toRadians(startLocation.getLatitude());
        double endLat = Math.toRadians(endLocation.getLatitude());
        double deltaLat = Math.toRadians(endLocation.getLatitude() - startLocation.getLatitude());
        double deltaLon = Math.toRadians(endLocation.getLongitude() - startLocation.getLongitude());

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(startLat) * Math.cos(endLat)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance;
    }
}
