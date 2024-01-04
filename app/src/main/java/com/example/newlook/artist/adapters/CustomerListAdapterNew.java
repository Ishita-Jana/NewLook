package com.example.newlook.artist.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newlook.R;
import com.example.newlook.artist.POJO.OrderModel;
import com.example.newlook.artist.activity.ProjectDetailViewActivity;
import com.example.newlook.artist.activity.RejectOrderActivity;
import com.example.newlook.utils.DatabaseFunctions;

import java.util.ArrayList;

public class CustomerListAdapterNew extends RecyclerView.Adapter<CustomerListAdapterNew.ViewHolder>{

    private final Context context;
    private final ArrayList<OrderModel> customerList;

    public CustomerListAdapterNew(Context context, ArrayList<OrderModel> customerList){
        this.context = context;
        this.customerList = customerList;
    }


    @NonNull
    @Override
    public CustomerListAdapterNew.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_user_project_details, parent, false);
        return new ViewHolder((ViewGroup) v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerListAdapterNew.ViewHolder holder, int position) {
            OrderModel customer = customerList.get(position);
            holder.customer_name.setText(customer.getCustomerName());
            holder.category.setText(customer.getCategory());
            holder.deadline.setText(customer.getDeadline());
            String orderid = customer.getOrderId();
            System.out.println("orderId"+orderid);
            if(customer.getStatus().equals("Pending")){

                holder.accept.setVisibility(View.VISIBLE);
                holder.reject.setVisibility(View.VISIBLE);
            }
            else if(customer.getStatus().equals("Accepted")){
                holder.pending.setVisibility(View.VISIBLE);
                holder.accept.setVisibility(View.GONE);
                holder.reject.setVisibility(View.GONE);
                holder.delivered.setVisibility(View.GONE);
            }
            else if(customer.getStatus().equals("Rejected")){
                holder.denied.setVisibility(View.VISIBLE);
                holder.accept.setVisibility(View.GONE);
                holder.reject.setVisibility(View.GONE);
                holder.delivered.setVisibility(View.GONE);
                holder.layout.setAlpha(0.3f);

            }
            else if(customer.getStatus().equals("Delivered")){
                holder.pending.setVisibility(View.GONE);
                holder.accept.setVisibility(View.GONE);
                holder.reject.setVisibility(View.GONE);
                holder.delivered.setVisibility(View.VISIBLE);
            }
            holder.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    DatabaseFunctions databaseFunctions = new DatabaseFunctions();
                    System.out.println("inside accept click"+customer.getOrderId());
                    databaseFunctions.setValue(customer.getOrderId(),"status","Accepted");


                } });
            holder.reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(context, RejectOrderActivity.class);
                    System.out.println("inside reject click"+customer.getOrderId());
                    intent.putExtra("orderId",customer.getOrderId());
                    context.startActivity(intent);
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(customer.getStatus().equals("Rejected")){
                        return;
                    }else {
                        Intent intent = new Intent(context, ProjectDetailViewActivity.class);
                        System.out.println(customer.getOrderId());
                        intent.putExtra("orderId",customer.getOrderId());
                        context.startActivity(intent);
                    }

                }
            });

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView customer_name,category,deadline,accept,reject;
        private final ImageView pending,denied,delivered;
        private final LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_name = itemView.findViewById(R.id.customer_name);
            category = itemView.findViewById(R.id.customize_category);
            deadline = itemView.findViewById(R.id.customer_deadline);
            accept = itemView.findViewById(R.id.artist_accept);
            reject = itemView.findViewById(R.id.artist_reject);
            pending = itemView.findViewById(R.id.clock_logo);
            denied = itemView.findViewById(R.id.prohibition);
            layout = itemView.findViewById(R.id.cardLayoutOrder);
            delivered = itemView.findViewById(R.id.delivered);
        }
    }
}
