package com.example.newlook.artist.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newlook.MainActivityArtist;
import com.example.newlook.R;
import com.example.newlook.artist.POJO.OrderModel;
import com.example.newlook.artist.adapters.CustomerListAdapterNew;
import com.example.newlook.data.UserDataManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArtistOrdersFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference reference;
    MainActivityArtist mainActivityArtist;
    CustomerListAdapterNew customerListAdapterNew;

    ImageView homePage;
    BottomSheetDialog dialog;
    TextView homeText;
    ImageButton filter;
    boolean isFilterActive = false;
    String selectedStatus;

    ArrayList<OrderModel> customerArrayList = new ArrayList<OrderModel>();
    ArrayList<OrderModel> filteredCustomerArrayList = new ArrayList<OrderModel>();

    public ArtistOrdersFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_artist_orders, container, false);

        dialog = new BottomSheetDialog(getActivity(), R.style.dialog_filter);
        dialog.setContentView(R.layout.project_filter_dialog_view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        filter = v.findViewById(R.id.filterStatusButton);
        filter.setVisibility(View.INVISIBLE);
        recyclerView = v.findViewById(R.id.ordersListRecyclerView);
        homePage = v.findViewById(R.id.artist_home_image);
        homeText = v.findViewById(R.id.artist_welcome_text);
        reference = FirebaseDatabase.getInstance().getReference("orders");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        customerListAdapterNew = new CustomerListAdapterNew(getContext(), customerArrayList);
        recyclerView.setAdapter(customerListAdapterNew);
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        String defaultArtist = UserDataManager.getInstance().getEmail();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customerArrayList.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    if (dataSnapshot.child("artistEmail").getValue().toString().equals(defaultArtist)) {
                        System.out.println("hello");
                        OrderModel orderModel = dataSnapshot.getValue(OrderModel.class);
                        customerArrayList.add(orderModel);

                    }



                }
                System.out.println("customerArrayList: " + customerArrayList.size());




                customerListAdapterNew.notifyDataSetChanged();
                if(customerArrayList.size() == 0){
                    homePage.setVisibility(View.VISIBLE);
                    homeText.setVisibility(View.VISIBLE);
                }
                else{
                    homePage.setVisibility(View.GONE);
                    homeText.setVisibility(View.GONE);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



        });
//         customerListAdapterNew = new CustomerListAdapterNew(this, customerArrayList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(customerListAdapterNew);


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                RadioGroup group = dialog.findViewById(R.id.radioGroup_filter);
                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        RadioButton button = radioGroup.findViewById(i);
                        selectedStatus = button.getText().toString();
                        isFilterActive = !selectedStatus.equals("All");
                        filterList();

                        Toast.makeText(getContext(), button.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });





        return v;
    }

    private void filterList() {
        filteredCustomerArrayList.clear();
        for (OrderModel orderModel : customerArrayList) {
            if (orderModel.getStatus().equals(selectedStatus)) {
                System.out.println(orderModel.getArtistName());
                filteredCustomerArrayList.add(orderModel);
            }
        }
        customerListAdapterNew = new CustomerListAdapterNew(getContext(), filteredCustomerArrayList);

        customerListAdapterNew.notifyDataSetChanged();
    }
}