package com.example.newlook.customer.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newlook.MainActivityUser;
import com.example.newlook.R;
import com.example.newlook.customer.POJO.ArtistModel;
import com.example.newlook.shared.fragments.ArtistProfile;


import java.util.ArrayList;

public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.ViewHolder>  {

    private final Context context;
    Bundle args;

    MainActivityUser mainActivityUser;


    private final ArrayList<ArtistModel> artistModelArrayList;

    public ArtistListAdapter(Context context, ArrayList<ArtistModel>  artistModelArrayList) {
        this.context = context;
        this.artistModelArrayList = artistModelArrayList;


    }


    @NonNull
    @Override
    public ArtistListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_artist_details, parent, false);
        return new ViewHolder((ViewGroup) view);

    }



    @Override
    public void onBindViewHolder(@NonNull ArtistListAdapter.ViewHolder holder, int position) {
        ArtistModel artistModel = artistModelArrayList.get(position);
        holder.artist_name.setText(artistModel.getName());
        holder.artist_rating.setRating(artistModel.getRating());
        holder.artist_categories.setText(artistModel.getCategories());
        holder.artist_img.setImageResource(artistModel.getArtistImg());

        holder.itemView.setOnClickListener(view -> {

            String value = artistModel.getName();
            String email = artistModel.getEmail();
            mainActivityUser = (MainActivityUser) context;
            args = new Bundle();
            args.putString("artist_name", value);
            args.putString("artist_email",email);
            mainActivityUser.replaceFragment(new ArtistProfile(), args);

        });
    }



    @Override
    public int getItemCount() {
        return artistModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView artist_name;
        private final RatingBar artist_rating;
        private final TextView artist_categories;
        private  final ImageView artist_img;

        public ViewHolder(@NonNull ViewGroup itemview) {
            super(itemview);
            artist_img = itemview.findViewById(R.id.artistImage);
            artist_name = itemview.findViewById(R.id.artistName);
            artist_rating = itemview.findViewById(R.id.artistRatingBar);
            artist_categories = itemview.findViewById(R.id.categories_artist);





        }
    }





}


