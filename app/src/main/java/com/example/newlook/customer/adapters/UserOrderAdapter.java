package com.example.newlook.customer.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newlook.MainActivityUser;
import com.example.newlook.R;
import com.example.newlook.customer.POJO.CustomerOrderModel;
import com.example.newlook.customer.fragments.UserHomeFragment;

import java.util.ArrayList;

public class UserOrderAdapter extends RecyclerView.Adapter<UserOrderAdapter.ViewHolder> {

    private final Context context;
    Bundle args;
    MainActivityUser mainActivityUser;
    public ArrayList<CustomerOrderModel> customerOrderModelArrayList;

    public UserOrderAdapter(Context context, ArrayList<CustomerOrderModel> customerOrderModelArrayList){
        this.context = context;
        this.customerOrderModelArrayList = customerOrderModelArrayList;
    }


    @NonNull
    @Override
    public UserOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_order_card_layout, parent, false);
        return new ViewHolder((ViewGroup) view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserOrderAdapter.ViewHolder holder, int position) {
        customerOrderModelArrayList.get(position);
        holder.artistname.setText(customerOrderModelArrayList.get(position).getArtistName());
        holder.status.setText(customerOrderModelArrayList.get(position).getStatus());
        holder.deadline.setText(customerOrderModelArrayList.get(position).getDeadline());
        holder.category.setText(customerOrderModelArrayList.get(position).getCategory());
        holder.orderDate.setText(customerOrderModelArrayList.get(position).getDate());
        holder.orderId.setText(customerOrderModelArrayList.get(position).getOrderId());

        holder.itemView.setOnClickListener(view -> {
            mainActivityUser = (MainActivityUser) context;
//            args = new Bundle();
//            args.putString("artist_name", value);
//            args.putString("artist_email",email);
            mainActivityUser.replaceFragment(new UserHomeFragment());

        });
    }



    @Override
    public int getItemCount() {
        return customerOrderModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView artistname, status, deadline, category, orderDate, orderId;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            artistname = itemView.findViewById(R.id.artistName);
            status = itemView.findViewById(R.id.status);
            deadline = itemView.findViewById(R.id.deadline);
            category = itemView.findViewById(R.id.category);
            orderDate = itemView.findViewById(R.id.orderDate);
            orderId = itemView.findViewById(R.id.orderId);

        }
    }
}
