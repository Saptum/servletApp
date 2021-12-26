package com.example.demo;

public class Order {
    private int id;
    private String date;
    private String destination;
    private int userID;

    public Order(int id, String date, String destination, int userID) {
        this.id = id;
        this.date = date;
        this.destination = destination;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDestination() {
        return destination;
    }

    public int getUserID() {
        return userID;
    }
}
