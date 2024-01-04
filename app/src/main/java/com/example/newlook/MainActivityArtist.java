package com.example.newlook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newlook.artist.POJO.OrderModel;
import com.example.newlook.artist.activity.ArtistSettingsActivity;
import com.example.newlook.artist.adapters.CustomerListAdapterNew;
import com.example.newlook.artist.fragments.ArtistOrdersFragment;
import com.example.newlook.artist.fragments.ArtistProfileFragment;
import com.example.newlook.data.UserDataManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityArtist extends AppCompatActivity {

    ImageButton filter;
    TextView switchCustomer, welcomeText,profile,orders, profileDash,ordersDash;
    ImageView homePage;
    String id;

    BottomSheetDialog dialog;

    DatabaseReference reference;

    RecyclerView recyclerView;
    CircleImageView artistProfile;
    CustomerListAdapterNew customerListAdapterNew;

    ArrayList<OrderModel> customerArrayList = new ArrayList<OrderModel>();

    String artistEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_artist);
        switchCustomer = findViewById(R.id.switchButton);
        artistProfile = findViewById(R.id.artistProfile);
//        recyclerView = findViewById(R.id.customerListRecyclerView);
//        filter =  findViewById(R.id.customer_filter_button);
        dialog = new BottomSheetDialog(this, R.style.dialog_filter);
        dialog.setContentView(R.layout.project_filter_dialog_view);
//        homePage = findViewById(R.id.artist_home_image);
//        welcomeText = findViewById(R.id.artist_welcome_text);

        profile = findViewById(R.id.profile);
        orders = findViewById(R.id.orders);
        profileDash = findViewById(R.id.profileDash);
        ordersDash = findViewById(R.id.ordersDash);

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        Intent intent = getIntent();
        artistEmail = intent.getStringExtra("artistId");
        String defaultArtist = UserDataManager.getInstance().getEmail();
        id = UserDataManager.getInstance().getEmail();
        System.out.println("Inside artist main activity");
       System.out.println(defaultArtist);
       System.out.println(artistEmail);
       System.out.println(id);
       replaceFragment(new ArtistProfileFragment());

       artistProfile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivityArtist.this, ArtistSettingsActivity.class));
           }
       });




        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ordersDash.setBackground(getResources().getDrawable(R.color.white));
                profileDash.setBackground(getResources().getDrawable(R.color.sunny));
                replaceFragment (new ArtistProfileFragment());

            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                profileDash.setBackground(getResources().getDrawable(R.color.white));
                ordersDash.setBackground(getResources().getDrawable(R.color.sunny));
                replaceFragment (new ArtistOrdersFragment());
            }
        });

        switchCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityArtist.this, MainActivityUser.class);
                intent.putExtra("role", "customer");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear back stack
                startActivity(intent);
                finish();
                Toast.makeText(MainActivityArtist.this, "Switching to customer", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.artistFramelayout, fragment);
        fragmentTransaction.addToBackStack(null).commit();

    }

    public void replaceFragment(Fragment fragment, Bundle args) {
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.artistFramelayout, fragment);
        fragmentTransaction.addToBackStack(null).commit();

    }
}