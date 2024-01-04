package com.example.newlook.customer.fragments.userProfileFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newlook.MainActivityUser;
import com.example.newlook.R;
import com.example.newlook.customer.POJO.CustomerOrderModel;
import com.example.newlook.customer.adapters.UserOrderAdapter;
import com.example.newlook.data.UserDataManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UserOrdersFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference reference;
    MainActivityUser mainActivityUser;
    UserOrderAdapter userOrderAdapter;
    ArrayList<CustomerOrderModel> customerOrderModelArrayList = new ArrayList<CustomerOrderModel>();



    public UserOrdersFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_orders, container, false);
        reference = FirebaseDatabase.getInstance().getReference("orders");
        recyclerView = v.findViewById(R.id.customerOrderRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userOrderAdapter = new UserOrderAdapter(getContext(), customerOrderModelArrayList);
        recyclerView.setAdapter(userOrderAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot snapshot) {
                customerOrderModelArrayList.clear();
                System.out.println("User email: "+UserDataManager.getInstance().getEmail());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    System.out.println();
                    if(dataSnapshot.exists()){
                        if (dataSnapshot.child("customerEmail").getValue().toString().equals(UserDataManager.getInstance().getEmail())){
                            CustomerOrderModel customerOrderModel = dataSnapshot.getValue(CustomerOrderModel.class);
                            customerOrderModelArrayList.add(customerOrderModel);
                        }

                    }

                }
                userOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
        }
    }
