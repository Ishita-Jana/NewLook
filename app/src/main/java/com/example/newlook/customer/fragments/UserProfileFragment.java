package com.example.newlook.customer.fragments;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newlook.MainActivityUser;
import com.example.newlook.R;
import com.example.newlook.customer.activity.ArtistRegisterViewActivity;
import com.example.newlook.customer.activity.UserLoginActivity;
import com.example.newlook.customer.fragments.userProfileFragments.UserEditAccountFragment;
import com.example.newlook.customer.fragments.userProfileFragments.UserNotificationFragment;
import com.example.newlook.customer.fragments.userProfileFragments.UserOrdersFragment;
import com.example.newlook.data.UserDataManager;
import com.example.newlook.shared.fragments.AboutUs;
import com.example.newlook.shared.fragments.ContactUs;
import com.google.firebase.auth.FirebaseAuth;


public class UserProfileFragment extends Fragment {

    View editDetails, orders, notif, contactUs, aboutUs, registerArtist, logout;
    MainActivityUser mainActivityUser;
    FirebaseAuth mAuth;

    TextView userNameTV;
    TextView userEmailTV;
//    private UserProfileFragmentListener listener;

    public UserProfileFragment() {
        // Required empty public constructor
    }

//    public interface UserProfileFragmentListener {
//            void inputUserProfileFragmentSent(CharSequence input);
//    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);
        userNameTV = v.findViewById(R.id.showUserName);
        userEmailTV = v.findViewById(R.id.showUserEmail);
        editDetails = v.findViewById(R.id.editDetails);
        orders = v.findViewById(R.id.orderHistory);
        notif = v.findViewById(R.id.notif);
        contactUs = v.findViewById(R.id.contactUs);
        aboutUs = v.findViewById(R.id.aboutUs);
        registerArtist = v.findViewById(R.id.registerAsArtist);
        logout = v.findViewById(R.id.logOutUser1);
        mAuth = FirebaseAuth.getInstance();


        userNameTV.setText(UserDataManager.getInstance().getName());
        userEmailTV.setText(UserDataManager.getInstance().getEmail());

        editDetails.setOnClickListener(view -> {
            mainActivityUser = (MainActivityUser) getActivity();
            assert mainActivityUser != null;

            mainActivityUser.replaceFragment(new UserEditAccountFragment());
        });
        orders.setOnClickListener(view -> {
            mainActivityUser = (MainActivityUser) getActivity();
            assert mainActivityUser != null;
            mainActivityUser.replaceFragment(new UserOrdersFragment());
        });
        notif.setOnClickListener(view -> {
            mainActivityUser = (MainActivityUser) getActivity();
            assert mainActivityUser != null;
            mainActivityUser.replaceFragment(new UserNotificationFragment());
        });
        contactUs.setOnClickListener(view -> {
            mainActivityUser = (MainActivityUser) getActivity();
            assert mainActivityUser != null;
            mainActivityUser.replaceFragment(new ContactUs());
        });
        aboutUs.setOnClickListener(view -> {
            mainActivityUser = (MainActivityUser) getActivity();
            assert mainActivityUser != null;
            mainActivityUser.replaceFragment(new AboutUs());
        });
        registerArtist.setOnClickListener(view -> {
            if(!UserDataManager.getInstance().getRole().equals("artist")){
                startActivity(new android.content.Intent(getActivity(), ArtistRegisterViewActivity.class));
            }
            else{
                registerArtist.setBackground(getResources().getDrawable(R.color.grey));
            }

        });
        logout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new android.content.Intent(getActivity(), UserLoginActivity.class));
        });





        return v;
    }
}