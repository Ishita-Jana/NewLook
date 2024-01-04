package com.example.newlook.customer.POJO;

import java.util.Date;

public class CustomerOrderModel {

    String artistName;
    String status;
    String deadline;
    String category;
    String date;
    String orderId;
    String orderKey;
    String number;
    CustomerOrderModel(){

    }

    public String getArtistName() {
        return artistName;
    }

    public String getStatus() {
        return status;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }
    public String getOrderKey() {
        return orderKey;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
