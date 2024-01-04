package com.example.newlook.customer.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newlook.MainActivityUser;
import com.example.newlook.R;


public class UserHomeFragment extends Fragment {

    View seeMore;
    CardView shoe, denim,phone_case,diary_cover;
    MainActivityUser mainActivityUser ;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_home, container, false);
        seeMore = v.findViewById(R.id.seeMore);
        shoe = v.findViewById(R.id.shoe);
        denim = v.findViewById(R.id.denim);
        phone_case = v.findViewById(R.id.phonecase);
        diary_cover = v.findViewById(R.id.diarycover);
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

        seeMore.setOnClickListener(view -> {
            mainActivityUser = (MainActivityUser) getActivity();
//            assert mainActivity != null;
            mainActivityUser.replaceFragment(new UserCategoriesFragment());
        });


        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Create an OnBackPressedCallback
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                mainActivityUser = (MainActivityUser) getActivity();
                mainActivityUser.finish();
                // Handle the back button press for the specific fragment (UserHomeFragment)
                // For example, show a Toast message
                Toast.makeText(requireContext(), "Back button press is disabled for this fragment", Toast.LENGTH_SHORT).show();
            }
        };

        // Add the callback to the OnBackPressedDispatcher
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);

        // Set up your fragment's UI and logic here
        // For example, you can find views and set click listeners
    }

    private void replaceFragmentWithValue(String value) {
        mainActivityUser = (MainActivityUser) getActivity();
        Bundle args = new Bundle();
        args.putString("category", value);
        mainActivityUser.replaceFragment(new ArtistListFragment(), args);
    }
}