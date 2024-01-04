package com.example.newlook;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.example.newlook.artist.activity.ArtistEditAccountActivity;
import com.example.newlook.customer.activity.ArtistRegisterActivity;
import com.example.newlook.customer.activity.UserLoginActivity;
import com.example.newlook.data.UserDataManager;
import com.example.newlook.models.user;

import com.example.newlook.customer.fragments.UserCategoriesFragment;
import com.example.newlook.customer.fragments.UserHomeFragment;
import com.example.newlook.customer.fragments.UserProfileFragment;
import com.example.newlook.shared.activity.ArtistProfileViewActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivityUser extends AppCompatActivity {

    View home, categories, profile;
    TextView userNameTV, registerArtist,test;
    private String userID, Name, studentId, role;
    private String userName, userEmail;
    FirebaseAuth mAuth;
    FirebaseUser user;
    TextView logOut;
    DatabaseReference reference;

    ArrayList<String> testArtistList;




    String type ="artist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
//        fetchData();
        replaceFragment(new UserHomeFragment());

        home = findViewById(R.id.bottomNav).findViewById(R.id.homeBottomNav);
        categories = findViewById(R.id.bottomNav).findViewById(R.id.categoriesBottomNav);
        profile = findViewById(R.id.bottomNav).findViewById(R.id.profileBottomNav);
        userNameTV = findViewById(R.id.userName);

        logOut = findViewById(R.id.logout);
//        progressBar = findViewById(R.id.mainProgressBar);
        registerArtist = findViewById(R.id.registerAsArtist);

//        progressBar.setVisibility(View.GONE);
//        progressBar.setVisibility(View.VISIBLE);
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        Intent intent = getIntent();
        type = intent.getStringExtra("role");

        System.out.println("type: " + type);



        Name = UserDataManager.getInstance().getName();
        studentId = UserDataManager.getInstance().getStudentId();
        userEmail = UserDataManager.getInstance().getEmail();
        role = UserDataManager.getInstance().getRole();
        String[] parts = Name.split(" ");
        String firstWord = parts[0];
        System.out.println("main User: name "+Name);
        System.out.println("main User: studentId "+studentId);
        System.out.println("main User: userEmail "+userEmail);
        System.out.println("main User: role "+role);

        if(type != null && type.equals("customer")){

            System.out.println("inside if");
            replaceFragment (new UserHomeFragment());
            registerArtist.setText("Switch to artist");
            userNameTV.setText("Hi, " + firstWord + "!");

        }

        else if(role.equals("artist") ){
//                            System.out.println("inside else if");
            startActivity(new Intent(MainActivityUser.this, MainActivityArtist.class));
            finish();
        }
        else{
            System.out.println("inside else");
            registerArtist.setText("Register as artist");
            replaceFragment (new UserHomeFragment());
//                            finish();
            userNameTV.setText("Hi, " + firstWord + "!");
            UserDataManager.getInstance().setName(Name);
            UserDataManager.getInstance().setStudentId(studentId);
            UserDataManager.getInstance().setEmail(userEmail);
            UserDataManager.getInstance().setUid(userID);
        }







        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainActivityUser.this, UserLoginActivity.class));
            }
        });




        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment (new UserHomeFragment());
//                finish();

            }
        });

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment (new UserCategoriesFragment());
//                finish();

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment (new UserProfileFragment());


            }
        });


        registerArtist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(type != null && type.equals("customer")){
                    type = "artist";
                    startActivity(new Intent(MainActivityUser.this, MainActivityArtist.class));
                    finish();
                }
                else{
                    startActivity(new Intent(MainActivityUser.this, ArtistRegisterActivity.class));

                }
            }
        });




    }




    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.userFrameLayout, fragment);
        fragmentTransaction.addToBackStack(null).commit();

    }

    public void replaceFragment(Fragment fragment, Bundle args) {
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.userFrameLayout, fragment);
        fragmentTransaction.addToBackStack(null).commit();

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user == null) {
//            startActivity(new Intent(MainActivityUser.this, UserLoginActivity.class));
//        }
//
//    }








    public String getName(){
        return userName;
    }
    public String getStudentId(){
        return studentId;
    }

}