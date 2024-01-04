package com.example.newlook.artist.activity;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newlook.R;
import com.example.newlook.artist.POJO.OrderModel;
import com.example.newlook.utils.DatabaseFunctions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProjectDetailViewActivity extends AppCompatActivity {


    DatabaseReference reference;
    TextView category,deadline,description,username;
    LinearLayout whatsappLayout,whatsapp;
    String status;
    Button delivered;
    String number;

    private static final int REQUEST_CODE_WHATSAPP = 123;
    String text= "Hi, I am interested in your work. I would like to discuss more about the project.";
    private DialogInterface progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        username = findViewById(R.id.showUserName);
        category = findViewById(R.id.category);
        deadline = findViewById(R.id.deadline_date);
        description = findViewById(R.id.description);
        whatsappLayout = findViewById(R.id.whatsappButton);
        whatsapp = findViewById(R.id.whatsapp);
        delivered = findViewById(R.id.deliveredButton);


        Intent intent = getIntent();
        String id = intent.getStringExtra("orderId");
        System.out.println("orderId"+id);

        //getting orders details of the customer

        getOrderDetails(id);

        delivered.setOnClickListener(v ->{
            DatabaseFunctions databaseFunctions = new DatabaseFunctions();
            databaseFunctions.setValue( id,"status","Delivered");
        });

        whatsappLayout.setOnClickListener(v -> {
            System.out.println("whatsapp clicked");
            Toast.makeText(this, "testing", Toast.LENGTH_SHORT).show();
            if(status != null && status.equals("Accepted")) {
                Toast.makeText(ProjectDetailViewActivity.this, "status is accepted", Toast.LENGTH_SHORT).show();
                Toast.makeText(ProjectDetailViewActivity.this, "Whatsapp installed "+isWhatsappInstalled(), Toast.LENGTH_SHORT).show();
//                if(isWhatsappInstalled()){
//                  Toast.makeText(ProjectDetailViewActivity.this, "Whatsapp installed successfully", Toast.LENGTH_SHORT).show();
//                    Intent sendIntent = new Intent();
//                    sendIntent.setAction(Intent.ACTION_SEND);
//                    sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                    sendIntent.setType("text/plain");
//                    startActivity(sendIntent);
//                }

//                else{
//                    System.out.println("Whatsapp not installed");
//                }
                // Show ProgressDialog while waiting for WhatsApp
//                ProgressDialog progressDialog = ProgressDialog.show(
//                        ProjectDetailViewActivity.this,
//                        "Opening WhatsApp",
//                        "Please wait...",
//                        true,
//                        true
//                );
//                progressDialog.show();
//                System.out.println("number before replace "+number);
//                number = number.replaceAll("[^0-9]","");
//                System.out.println("number after replace "+number);
                if(number !=null && number.length() == 10){

                    number = "+91"+number;
                    System.out.println(number);
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+number+"&text="+text));
//                sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                    finish();
                }

//                progressDialog.dismiss();
            }
        });




    }




//    private void startActivity(Intent sendIntent, DialogInterface.OnDismissListener onDismissListener) {
//    }

    private void getOrderDetails(String id) {
        reference = FirebaseDatabase.getInstance().getReference("orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if (dataSnapshot.child("orderId").getValue().toString().equals(id)) {
                        System.out.println("getting customer details");
                        OrderModel orderModel = dataSnapshot.getValue(OrderModel.class);
                        if(orderModel.getCategory() != null && orderModel.getDate() != null && orderModel.getDescription() != null && orderModel.getStatus() != null){
                            username.setText(orderModel.getCustomerName());
                            category.setText(orderModel.getCategory());
                            deadline.setText(orderModel.getDate());
                            description.setText(orderModel.getDescription());
                            status = orderModel.getStatus();
                            number = orderModel.getNumber();

                            if(status.equals("Accepted")){
                                whatsapp.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF5623")));
                            } else if (status.equals("Delivered")) {
                                whatsapp.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D3D3D3")));
                                delivered.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D3D3D3")));
                            }

                        }

//                        System.out.println(orderModel.getArtistEmail());
//                        System.out.println(orderModel.getCategory());
//                        System.out.println(orderModel.getDate());
//                        System.out.println(orderModel.getDescription());
//                        System.out.println(orderModel.getStatus());
//                        System.out.println(status);



                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        }


    private boolean isWhatsappInstalled() {
        PackageManager packageManager = getPackageManager();
        boolean is_installed;
        try {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
           is_installed = true;
        } catch (Exception e) {
            is_installed = false;
            e.printStackTrace();

        }
        return is_installed;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_WHATSAPP) {
////            progressDialog.dismiss();
//        }
//    }
}
