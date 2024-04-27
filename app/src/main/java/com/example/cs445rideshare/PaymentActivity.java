package com.example.cs445rideshare;

public class PaymentActivity {
    private String cardType;
    private String cardNumber;
    private String expirationDate;

    public PaymentActivity(String cardType, String cardNumber, String expirationDate) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    public void remove(PaymentActivity paymentActivity) {
    }

    public void add(PaymentActivity paymentActivity) {
    }
}
