package com.example.newlook.artist.POJO;

public class OrderModel {
    String customerName;
    String category;
    String artistEmail;
    String description;
    String number;
    String location;
    String status;
    String rejectReason;
    String customerEmail;
    String artistName;
//    String userNumber;

    String orderId;
    String deadline;
    String date;

    public OrderModel(String customerName, String category, String artistEmail, String description, String number, String location, String status, String rejectReason, String customerEmail, String artistName, String orderId, String deadline, String date) {
        this.customerName = customerName;
        this.category = category;
        this.artistEmail = artistEmail;
        this.description = description;
        this.number = number;
        this.location = location;
        this.status = status;
        this.rejectReason = rejectReason;
        this.customerEmail = customerEmail;
        this.artistName = artistName;
        this.orderId = orderId;
        this.deadline = deadline;
        this.date = date;
//        this.userNumber = userNumber;
    }



    public String getOrderId() {
        return orderId;
    }

    public String getDeadline() {
        return deadline;
    }

    public OrderModel() {

    }
    public String getCustomerName() {
        return customerName;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getArtistEmail() {
        return artistEmail;
    }

    public String getDescription() {
        return description;
    }

    public String getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getArtistName() {
        return artistName;
    }


}

//utility create database with 2 steps
