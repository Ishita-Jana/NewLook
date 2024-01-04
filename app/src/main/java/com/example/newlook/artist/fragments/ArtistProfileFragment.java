package com.example.newlook.artist.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.newlook.R;
import com.example.newlook.artist.activity.ArtistEditAccountActivity;
import com.example.newlook.artist.activity.ArtistSettingsActivity;
import com.example.newlook.data.ArtistDataManager;
import com.example.newlook.data.UserDataManager;
import com.example.newlook.models.artist;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ArtistProfileFragment extends Fragment {

    TextView artistName, bio,categories, skills, avg_resp_time,charges;
    RatingBar rating;
    String artistEmail;
    ImageButton edit_button;

    DatabaseReference reference;
    public ArtistProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_artist_profile2, container, false);
        artistName = v.findViewById(R.id.artist_name);
        bio = v.findViewById(R.id.artist_about_me);
        categories = v.findViewById(R.id.artist_categories);
        skills = v.findViewById(R.id.artist_skill);
        avg_resp_time = v.findViewById(R.id.artist_resp_time);
        charges = v.findViewById(R.id.artist_charges);
        edit_button = v.findViewById(R.id.artist_editDetails);
        rating = v.findViewById(R.id.artist_rating);

        //get artist Data
        artistEmail = UserDataManager.getInstance().getEmail();
        System.out.println(artistEmail);
        getArtistData(artistEmail);

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("edit button clicked");
                Intent intent = new Intent(getActivity(), ArtistEditAccountActivity.class);
                startActivity(intent);
            }
        });
        return v;

    }

    public void getArtistData(String artistEmail) {
        reference = FirebaseDatabase.getInstance().getReference("artists");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    System.out.println(dataSnapshot.getValue());
                    if(dataSnapshot.child("email").getValue().toString().equals(artistEmail)){
                        System.out.println("getting artist details");
                        artist Artist  = dataSnapshot.getValue(artist.class);
                        System.out.println("Artist details");
                        if(Artist.getName() != null && Artist.getBio() != null && Artist.getCharges() != null && Artist.getCategories() != null && Artist.getEmail() != null && Artist.getSkills() != null && Artist.getResponseTime() != null && Artist.getRating() != null){
                            ArtistDataManager.getInstance().setArtistData(Artist);
//                            artist updatedArtistData = ArtistDataManager.getInstance().getArtistData();

                            artistName.setText(Artist.getName());
                            bio.setText(Artist.getBio());
                            categories.setText(Artist.getCategories());
                            skills.setText(Artist.getSkills());
                            avg_resp_time.setText(Artist.getResponseTime());
                            charges.setText(Artist.getCharges());


                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}