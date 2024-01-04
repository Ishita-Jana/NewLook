package com.example.newlook.shared.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.newlook.MainActivityUser;
import com.example.newlook.R;
import com.example.newlook.shared.POJO.ArtistAllDetailsModel;
import com.example.newlook.customer.fragments.UserOrderRegisterFragment;
import com.example.newlook.data.UserDataManager;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ArtistProfile extends Fragment {

    TextView artist_name,about_artist,skills,avg_response_time,artist_categories,charges;
    Button approach_artist;
    MainActivityUser mainActivityUser;
    RatingBar artist_rating;
    Bundle args;
    ImageButton edit_button;
    DatabaseReference reference;

    FirebaseUser user;


    public ArtistProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_artist_profile, container, false);


        reference = FirebaseDatabase.getInstance().getReference("artists");

        artist_name = v.findViewById(R.id.artist_name);
        artist_rating = v.findViewById(R.id.artist_rating);
        about_artist = v.findViewById(R.id.artist_about_me);
        skills = v.findViewById(R.id.artist_skill);
        avg_response_time = v.findViewById(R.id.artist_resp_time);
        charges = v.findViewById(R.id.artist_charges);
        artist_categories = v.findViewById(R.id.artist_categories);
        approach_artist = v.findViewById(R.id.approach_artist);
//        .


        args = new Bundle();
       //get thr userid from artistList fragment
        System.out.println("artist name is " + getArguments().getString("artist_name"));
        System.out.println("artist email is " + getArguments().getString("artist_email"));
        String artistName = getArguments().getString("artist_name");
        String artistEmail = getArguments().getString("artist_email");

        artist_name.setText(artistName);
//        edit_button.setVisibility(View.GONE);




//            String customer_ID = UserDataManager.getInstance().getUid();
            String customer_name = UserDataManager.getInstance().getName();
            System.out.println("customer name is " + customer_name);



            args.putString("artist_name", artistName);
            args.putString("artist_email", artistEmail);
            args.putString("customer_name", customer_name);
            System.out.println("artist name is " + artistName);



            reference.orderByChild("email").equalTo(artistEmail).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Retrieve the ArtistModel data with the matching emailD
                        ArtistAllDetailsModel artist = snapshot.getValue(ArtistAllDetailsModel.class);
                        if (artist != null) {

                            System.out.println("artist bio is " + artist.getBio());

                            System.out.println("artist bio is " + artist.getBio());
                            artist_name.setText(artist.getName());
                            about_artist.setText(artist.getBio());
                            skills.setText(artist.getSkills());
                            avg_response_time.setText(artist.getResponseTime());
                            charges.setText(artist.getCharges());
                            artist_categories.setText(artist.getCategories());
                            double rating = artist.getRating();
                            int convertedRating = (int) rating;
                            artist_rating.setRating(convertedRating);


                        }
//                        String artistName = snapshot.child("name").getValue(String.class);
//                        System.out.println("artist name is " + artistName);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle the error
                }
            });

//            reference.child("artists").child(artistEmail).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DataSnapshot> task) {
//                    if(task.isSuccessful()){
//                        ArtistAllDetailsModel artist = task.getResult().getValue(ArtistAllDetailsModel.class);
//                        if (artist != null) {
//
//                            System.out.println("artist bio is " + artist.getBio());
//                            artist_name.setText(artist.getName());
//                            about_artist.setText(artist.getBio());
//                            skills.setText(artist.getSkills());
//                            avg_response_time.setText(artist.getResponseTime());
//                            charges.setText("Rs. 30 - 500 /hr");
//                            artist_categories.setText(artist.getCategories());
//                            double rating = artist.getRating();
//                            int convertedRating = (int) rating;
//                            artist_rating.setRating(convertedRating);
//                    }
//                    if (!task.isSuccessful()) {
//                        Log.e("firebase", "Error getting data", task.getException());
//                    }
//                    else {
//                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                    }
//                }
//            }  ;




        approach_artist.setOnClickListener(view1 -> {
            mainActivityUser = (MainActivityUser) getContext();

            mainActivityUser.replaceFragment(new UserOrderRegisterFragment(), args);
        });



        return v;
        }



}



