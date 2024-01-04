package com.example.newlook.customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.newlook.R;

public class ArtistRegisterViewActivity extends AppCompatActivity {

    Button btnRegister1,btnRegister2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_register_view);

        btnRegister1 = findViewById(R.id.regArtistbtn1);
        btnRegister2 = findViewById(R.id.regArtistbtn2);

        btnRegister1.setOnClickListener(view -> {
            startActivity(new Intent(ArtistRegisterViewActivity.this, ArtistRegisterActivity.class));
        });
        btnRegister2.setOnClickListener(view -> {
            startActivity(new Intent(ArtistRegisterViewActivity.this, ArtistRegisterActivity.class));
        });



    }
}