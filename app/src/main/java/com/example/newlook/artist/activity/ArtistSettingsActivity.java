package com.example.newlook.artist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newlook.MainActivityArtist;
import com.example.newlook.MainActivityUser;
import com.example.newlook.R;
import com.example.newlook.customer.activity.UserLoginActivity;
import com.example.newlook.data.UserDataManager;
import com.example.newlook.shared.activity.AboutUsActivity;
import com.example.newlook.shared.activity.ContactUsActivity;
import com.example.newlook.shared.fragments.ContactUs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ArtistSettingsActivity extends AppCompatActivity {

    LinearLayout editProfile,contactUs, aboutUs;
    TextView logout, switchAccount, name, email;
    String artistName, artistEmail;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_account_edit);
        editProfile = findViewById(R.id.artist_editDetails);
        contactUs = findViewById(R.id.artist_contactUs);
        aboutUs = findViewById(R.id.artist_AboutUs);
        switchAccount = findViewById(R.id.switchArtist);
        name = findViewById(R.id.showUserName);
        email = findViewById(R.id.showUserEmail);
        logout= findViewById(R.id.logoutArtist);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        artistName = UserDataManager.getInstance().getName();
        artistEmail = UserDataManager.getInstance().getEmail();

        name.setText(artistName);
        email.setText(artistEmail);

        editProfile.setOnClickListener(v -> {

            startActivity(new Intent(ArtistSettingsActivity.this, ArtistEditAccountActivity.class));
        });

        contactUs.setOnClickListener(v -> {
            startActivity(new Intent(ArtistSettingsActivity.this, ContactUsActivity.class));
        });
        aboutUs.setOnClickListener(v -> {
            startActivity(new Intent(ArtistSettingsActivity.this, AboutUsActivity.class));
        });

        switchAccount.setOnClickListener(v -> {
            Intent intent = new Intent(ArtistSettingsActivity.this, MainActivityUser.class);
            intent.putExtra("role", "customer");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear back stack
            startActivity(intent);
            finish();
            Toast.makeText(ArtistSettingsActivity.this, "Switching to customer", Toast.LENGTH_SHORT).show();

        });

        logout.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(ArtistSettingsActivity.this, UserLoginActivity.class));
        });

    }
}
