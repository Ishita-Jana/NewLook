package com.example.newlook.customer.POJO;

public class CustomerModel {

    private String customer_name;
    private String project_category;
    private String date;
    private int customer_img;

    public CustomerModel(){

    }

    public CustomerModel(String customer_name, String project_category, String date, int customer_img) {
        this.customer_name = customer_name;
        this.project_category = project_category;
        this.date = date;
        this.customer_img = customer_img;

    }

    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getProject_category() {
        return project_category;
    }
    public void setProject_category(String project_category) {
        this.project_category = project_category;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomer_img(int customer_img) {
        this.customer_img = customer_img;
    }

    public int getCustomer_img() {
        return customer_img;
    }
}
