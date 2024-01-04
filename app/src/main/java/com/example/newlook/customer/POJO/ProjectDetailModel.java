package com.example.newlook.customer.POJO;

import static com.example.newlook.utils.OrderIDGenerator.generateOrderID;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectDetailModel {

    String artistEmail,artistName,customerName,customerEmail,description,category,location;
    String number;
    String deadline;
    String status;
    String rejectReason=" ";
    String orderId;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String date = sdf.format(new Date());
    public ProjectDetailModel(){

    }
    public ProjectDetailModel(String artistEmail,String artistName,String customerEmail,String name,String number,String description,String location, String deadline, String category){

        this.orderId = generateOrderID();
        this.artistEmail = artistEmail;
        this.artistName = artistName;
        this.customerName = name;
        this.customerEmail = customerEmail;
        this.number = number;
        this.description = description;
        this.category = category;
        this.location = location;
        this.deadline = deadline;
        this.status = "pending";
        this.rejectReason = null;



    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getNumber() {
        return number;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getArtistEmail() {
        return artistEmail;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getRejectReason() {
        return rejectReason;
    }

}
