package com.example.newlook.customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newlook.MainActivityArtist;
import com.example.newlook.R;
import com.example.newlook.models.artist;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ArtistRegisterActivity extends AppCompatActivity {

    TextInputEditText etRegName,etBio,etResponseTime, etSkills, pass, categories,etCharges;
    TextView etBack;
    Button btnArtistRegister;
    FirebaseUser user;
    Double rating = 1.0;
    String artistImg = "0";

    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_register);

        etRegName = findViewById(R.id.etRegName);
        etBio = findViewById(R.id.aboutMe);
        etResponseTime = findViewById(R.id.responseTime);
        etSkills = findViewById(R.id.skills);
        btnArtistRegister = findViewById(R.id.registerArtistBtn);
        pass = findViewById(R.id.etRegPassword);
        categories = findViewById(R.id.etCategoryArtist);
        etCharges = findViewById(R.id.etChargesArtist);
        mAuth = FirebaseAuth.getInstance();
        etBack = findViewById(R.id.takeMeBackBtn);
        user = FirebaseAuth.getInstance().getCurrentUser();

        etBack.setOnClickListener(view -> {
           getOnBackPressedDispatcher().onBackPressed();
        });

        btnArtistRegister.setOnClickListener(view -> {
            createArtist();

//            startActivity(new Intent(ArtistRegisterActivity.this, MainActivityArtist.class));
        });






    }

    private void createArtist() {
        String name = etRegName.getText().toString();
        String bio = etBio.getText().toString();
        String responseTime = etResponseTime.getText().toString();
        String skills = etSkills.getText().toString();
        String password = pass.getText().toString();
        String category = categories.getText().toString();
        String charges = etCharges.getText().toString();
        System.out.println("category: " + category);

        if (name.isEmpty()){
            etRegName.setError("Name is required");
            etRegName.requestFocus();
        }else if (bio.isEmpty()){
            etBio.setError("Bio is required");
            etBio.requestFocus();
        }else if (responseTime.isEmpty()){
            etResponseTime.setError("Response Time is required");
            etResponseTime.requestFocus();
        }else if (skills.isEmpty()){
            etSkills.setError("Skills are required");
            etSkills.requestFocus();
        }else if (password.isEmpty()){
            pass.setError("Password is required");
            pass.requestFocus();
        }else if (category.isEmpty()){
            categories.setError("Category is required");
            categories.requestFocus();
        }else if (charges.isEmpty()){
            etCharges.setError("Category is required");
            etCharges.requestFocus();
        }else {
            String email = user.getEmail();
            DatabaseReference artistOrdersRef = FirebaseDatabase.getInstance().getReference("artists");
//            DatabaseReference customerRef = customerOrdersRef.child(UserDataManager.getInstance().getUid()); // Reference to customerId node
            DatabaseReference newOrderRef = artistOrdersRef.push();

            artist Artist = new artist(name, email , password,bio, responseTime, skills, category, rating,charges, artistImg);
            System.out.println(FirebaseAuth.getInstance().getCurrentUser().getUid());
            FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("role").setValue("artist");


                    newOrderRef.setValue(Artist).addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            Toast.makeText(ArtistRegisterActivity.this, "Artist registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ArtistRegisterActivity.this, MainActivityArtist.class);
                            intent.putExtra("artistId", email);
                            startActivity(intent);
                            finish();
                        }
                    });
        }
    }
}