package com.example.newlook.shared.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import com.example.newlook.R;
import com.example.newlook.artist.POJO.ArtistImgShowModel;
import com.example.newlook.artist.adapters.AristImageListAdapter;

import java.util.ArrayList;

public class ArtistProfileViewActivity extends AppCompatActivity {
    GridView coursesGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_profile_view);
//        GridLayout gridLayout = findViewById(R.id.gridLayout);
        coursesGV = findViewById(R.id.idGVcourses);
        ArrayList<ArtistImgShowModel> courseModelArrayList = new ArrayList<ArtistImgShowModel>();

        courseModelArrayList.add(new ArtistImgShowModel("DSA", R.drawable.about_us_vector));
        courseModelArrayList.add(new ArtistImgShowModel("DSA", R.drawable.about_us_vector));
        courseModelArrayList.add(new ArtistImgShowModel("DSA", R.drawable.about_us_vector));
        courseModelArrayList.add(new ArtistImgShowModel("DSA", R.drawable.about_us_vector));
        courseModelArrayList.add(new ArtistImgShowModel("DSA", R.drawable.about_us_vector));
        courseModelArrayList.add(new ArtistImgShowModel("DSA", R.drawable.about_us_vector));
        courseModelArrayList.add(new ArtistImgShowModel("DSA", R.drawable.about_us_vector));
        courseModelArrayList.add(new ArtistImgShowModel("DSA", R.drawable.about_us_vector));


       AristImageListAdapter adapter = new AristImageListAdapter(this, courseModelArrayList);
        coursesGV.setAdapter(adapter);



    }
}
