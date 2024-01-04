package com.example.newlook.artist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.newlook.MainActivityArtist;
import com.example.newlook.R;
import com.example.newlook.utils.DatabaseFunctions;

public class RejectOrderActivity extends AppCompatActivity {


    LinearLayout reject;
    Button rejectButton;
    CardView time,match,unavailable,others;
    String id, reason;
    CardView[] cardViews = new CardView[4];
    private String status,orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_reject);
        cardViews[0] = findViewById(R.id.time);
        cardViews[1]  = findViewById(R.id.not_a_match);
        cardViews[2]  = findViewById(R.id.materials);
        cardViews[3]  = findViewById(R.id.other_reason);

        reject = findViewById(R.id.whatsappButton);
        rejectButton = findViewById(R.id.rejectButton);
        Intent intent = getIntent();
        id = intent.getStringExtra("orderId");
        System.out.println(id);
        rejectButton.setOnClickListener(v -> {
            System.out.println("rejecting");
            if(id != null){
                DatabaseFunctions databaseFunctions = new DatabaseFunctions();
//                databaseFunctions.setStatus("Rejected", id);
                databaseFunctions.setValue( id,"status","Rejected");
                databaseFunctions.setValue(id,"rejectReason", reason);
                System.out.println("done rejecting"+ id);
                startActivity(new Intent(RejectOrderActivity.this, MainActivityArtist.class));
                finish();

            }
        });





        for (int i = 0; i < cardViews.length; i++) {
            int finalI = i;
            int finalI1 = i;
            cardViews[i].setOnClickListener(v -> {

                if(finalI1 == 1){
                    reason = "Time Contraints";
                } if(finalI1 == 2){
                    reason = "Not a match";
                } if(finalI1 == 3){
                    reason = "Unavailable materials";
                } if(finalI1 == 1){
                    reason = "Others";
                }

                for (int j = 0; j < cardViews.length; j++) {
                    if (finalI == j) {
                        // Clicked card - change background
                        cardViews[j].setCardBackgroundColor(getResources().getColor(R.color.offWhite, null));
                        cardViews[j].setCardElevation(12f); // Change elevation or any other visual indication
                    } else {
                        // Reset the background of other cards
                        cardViews[j].setCardBackgroundColor(getResources().getColor(R.color.white, null));
                        cardViews[j].setCardElevation(8f); // Reset elevation or any other change
                    }
                }
            });
        }


        }


}



