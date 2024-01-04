package com.example.newlook.customer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newlook.MainActivityUser;
import com.example.newlook.R;
import com.example.newlook.customer.POJO.ArtistModel;
import com.example.newlook.customer.adapters.ArtistListAdapter;
import com.example.newlook.data.UserDataManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ArtistListFragment extends Fragment {


    RecyclerView recyclerView;
    DatabaseReference reference;
    MainActivityUser mainActivityUser;
    ArtistListAdapter artistListAdapter;

    ArrayList<ArtistModel> artistModelArrayList = new ArrayList<ArtistModel>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artist_list, container, false);

        recyclerView = view.findViewById(R.id.artistListRecyclerView);
        reference = FirebaseDatabase.getInstance().getReference("artists");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        artistListAdapter = new ArtistListAdapter(getContext(), artistModelArrayList);
        recyclerView.setAdapter(artistListAdapter);




        String value = getArguments().getString("category");
//


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                artistModelArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    System.out.println(dataSnapshot.getValue());
                    ArtistModel artistModel = dataSnapshot.getValue(ArtistModel.class);
//                        System.out.println("artistModel = " + dataSnapshot.getValue());
//                        System.out.println("artistModel = " + dataSnapshot.getKey());
                        String key = dataSnapshot.getKey();
                        artistModel.setArtistId(key);
                    if (artistModel != null && categoryMatches(artistModel.getCategories(), value) && !artistModel.getEmail().equals(UserDataManager.getInstance().getEmail()) ) {
                        artistModelArrayList.add(artistModel);
                    }

                }



                artistListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }
    private boolean categoryMatches(String categories, String filterValue) {
        if (categories != null && filterValue != null) {
            String[] categoryList = categories.split(",");
            for (String category : categoryList) {
                if (category.trim().toLowerCase().contains(filterValue.trim().toLowerCase())) {
                    return true; // Substring match found
                }
            }
        }
        return false; // No match found
    }
}