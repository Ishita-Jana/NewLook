package com.example.newlook.data;

import com.example.newlook.models.artist;

public class ArtistDataManager {
    private static ArtistDataManager instance;
    private artist artistData;

    private ArtistDataManager() {
        // Private constructor to prevent instantiation
    }

    public static synchronized ArtistDataManager getInstance() {
        if (instance == null) {
            instance = new ArtistDataManager();
        }
        return instance;
    }

    public artist getArtistData() {
        return artistData;
    }

    public void setArtistData(artist data) {
        this.artistData = data;
    }
}

