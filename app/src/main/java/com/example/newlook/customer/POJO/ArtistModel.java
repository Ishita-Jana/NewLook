package com.example.newlook.customer.POJO;

import android.text.TextUtils;

import java.util.ArrayList;

public class ArtistModel {

    private String name;
    private int rating;
    private int artistImg;
    private String email;
    private String artistId;
    private String categories;

    public String getName() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public int getRating() {
        return rating;
    }

    public int getArtistImg() {
        return artistImg;
    }

    public String getEmail() {
        return email;
    }

    public String getCategories() {
        return categories;
    }
// Constructor

}
