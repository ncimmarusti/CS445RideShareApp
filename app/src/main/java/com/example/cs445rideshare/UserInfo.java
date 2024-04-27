package com.example.cs445rideshare;

import com.google.firebase.firestore.PropertyName;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {

    private static int nextId = 1;
    private int userId;
    private String username;
    private String email;
    private String password;
    private String phoneNumber; // Add this field
    private List<PaymentActivity> paymentActivity;
    private List<DriverInfo> driverInfo;
    private boolean isDriver;

    // Constructor for user

    public UserInfo() {
        // Required by Firestore for deserialization
    }

    public UserInfo(boolean isDriver, int userId, String email, DriverInfo driverInfo, PaymentActivity paymentActivity) {
        this.isDriver = isDriver;
        this.userId = userId;
        this.email = email;
        this.driverInfo = new ArrayList<>();
        // Constructor for user
        this.paymentActivity = new ArrayList<>();
    }

    public UserInfo(String email, boolean isDriver, String password) {
        this.email = email;
        this.isDriver = isDriver;
        this.password = password;

    }

    public void addPaymentMethod(PaymentActivity paymentActivity) {
        this.paymentActivity.add(paymentActivity);
    }

    public void removePaymentMethod(PaymentActivity paymentActivity) {
        this.paymentActivity.remove(paymentActivity);
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    @PropertyName("isDriver")
    public boolean isDriver() {
        return this.isDriver;
    }

    // Getters and setters
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<DriverInfo> getDriverInfo() {
        return this.driverInfo;
    }

    public void setDriverInfo(List<DriverInfo> driverInfo) {
        this.driverInfo = driverInfo;
    }

    public List<PaymentActivity> getPaymentActivity() {
        return this.paymentActivity;
    }

    public void setPaymentActivity(List<PaymentActivity> paymentActivity) {
        this.paymentActivity = paymentActivity;
    }
}

