package ua.edu.chmnu.network.java.lab91vanzhula.model;

import java.time.LocalDate;

public class Order {
    private LocalDate date;
    private double amount;
    private String paymentMethod;

    public Order() {}

    public Order(LocalDate date, double amount, String paymentMethod) {
        this.date = date;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
