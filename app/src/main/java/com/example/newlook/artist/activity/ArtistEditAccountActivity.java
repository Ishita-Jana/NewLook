package com.example.newlook.artist.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newlook.MainActivityArtist;
import com.example.newlook.R;
import com.example.newlook.data.ArtistDataManager;
import com.example.newlook.data.UserDataManager;
import com.example.newlook.models.artist;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;


import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArtistEditAccountActivity extends AppCompatActivity {

    private CircleImageView profileImageEdit;
    private TextView cancel, save;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private EditText name, bio, skills, categories, resp_time, charges;

    private TextView profileChangeButton;
    private Uri imageUri;
    private String myUri = "";
    private StorageTask uploadTask;
    private StorageReference storageProfilePicRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_edit_account);

        reference = FirebaseDatabase.getInstance().getReference().child("artists");
        storageProfilePicRef = FirebaseStorage.getInstance().getReference().child("ProfilePics");
        name = findViewById(R.id.artistName);
        bio = findViewById(R.id.artistBio);
        skills = findViewById(R.id.artistSkills);
        categories = findViewById(R.id.artistCategories);
        resp_time = findViewById(R.id.artistResponseTime);
        charges = findViewById(R.id.artistCharges);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        profileImageEdit = findViewById(R.id.profilePicEdit);
        artist updatedArtistData = ArtistDataManager.getInstance().getArtistData();
        System.out.println("showing data" + updatedArtistData);
        System.out.println(updatedArtistData.name);

        //fetching and setting the original data
        name.setText(updatedArtistData.name);
        bio.setText(updatedArtistData.bio);
        skills.setText(updatedArtistData.skills);
        categories.setText(updatedArtistData.categories);
        resp_time.setText(updatedArtistData.responseTime);
        charges.setText(updatedArtistData.getCharges());


        cancel.setOnClickListener(v -> {
            startActivity(new Intent(ArtistEditAccountActivity.this, MainActivityArtist.class));
        });

        save.setOnClickListener(v -> {
            updateDetails();
//            uploadProfilePic();
//            saveDetails();
        });

        profileImageEdit.setOnClickListener(v -> {

//            CropImage.activity().setAspectRatio(1, 1).start(ArtistEditAccountActivity.this);

        });

//        getUserinfo();


    }

    private void updateDetails() {
        Query query = reference.orderByChild("email").equalTo(UserDataManager.getInstance().getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    dataSnapshot.getRef().child("name").setValue(name.getText().toString());
                    dataSnapshot.getRef().child("bio").setValue(bio.getText().toString());
                    dataSnapshot.getRef().child("skills").setValue(skills.getText().toString());
                    dataSnapshot.getRef().child("categories").setValue(categories.getText().toString());
                    dataSnapshot.getRef().child("responseTime").setValue(resp_time.getText().toString());
                    dataSnapshot.getRef().child("charges").setValue(charges.getText().toString());
                }
                Toast.makeText(ArtistEditAccountActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ArtistEditAccountActivity.this, MainActivityArtist.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ArtistEditAccountActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });




        }


    }
