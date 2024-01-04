package com.example.newlook.customer.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newlook.MainActivityUser;
import com.example.newlook.R;


public class UserCategoriesFragment extends Fragment {

    View shoe;
    View denim, phone_case,diary_cover,bottles,tshirt,hoodie,jeans,shirts;
    MainActivityUser mainActivityUser;
    Bundle args;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_categories, container, false);
        shoe = v.findViewById(R.id.shoe);
        denim = v.findViewById(R.id.denim);
        phone_case = v.findViewById(R.id.phonecase);
        diary_cover = v.findViewById(R.id.diarycover);
        bottles = v.findViewById(R.id.bottles);
        tshirt = v.findViewById(R.id.tshirt);




        denim.setOnClickListener(view -> {
            replaceFragmentWithValue("denim");
        });

        shoe.setOnClickListener(view -> {
           replaceFragmentWithValue("shoe");

        });
        phone_case.setOnClickListener(view -> {
            replaceFragmentWithValue("phone");

        });
        diary_cover.setOnClickListener(view -> {
            replaceFragmentWithValue("diary");

        });
        bottles.setOnClickListener(view -> {
            replaceFragmentWithValue("bottle");

        });
        tshirt.setOnClickListener(view -> {
            replaceFragmentWithValue("tshirt");

        });


        return v;
    }

    public void replaceFragmentWithValue(String value) {
        mainActivityUser = (MainActivityUser) getActivity();
        args = new Bundle();
        args.putString("category", value);
        mainActivityUser.replaceFragment(new ArtistListFragment(), args);

    }

}