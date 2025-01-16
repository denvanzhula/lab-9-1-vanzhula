package ua.edu.chmnu.network.java.lab91vanzhula.model;

import java.util.List;

public class Customer {
    private String name;
    private String phone;
    private String address;
    private String photo;
    private List<Order> orders;

    public Customer() {}

    public Customer(String name, String phone, String address, String photo, List<Order> orders) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.photo = photo;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoto() {
        return photo;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
