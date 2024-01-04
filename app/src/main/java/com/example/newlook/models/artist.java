package com.example.newlook.models;

import com.example.newlook.R;

public class artist {

    public String name;

    public String email;
    public String password;
    public String bio;
    public String responseTime;
    public String skills;
    public int artistImg;
    public Double rating;
    public String categories;
    private String charges;
    private String imageUrl;


    public artist(){
    }
    public artist(String name, String email,String password, String bio, String responseTime, String skills,String category, Double rating,String charges, String imageUrl){
//        this.artistId = artistId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.responseTime = responseTime;
        this.skills = skills;
        this.rating = rating;
        this.artistImg = R.drawable.artist_home;
        this.categories = category;
        this.charges = charges;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getArtistImg() {
        return artistImg;
    }

    public void setArtistImg(int artistImg) {
        this.artistImg = artistImg;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }
}
