package com.example.newlook.artist.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newlook.R;
import com.example.newlook.artist.POJO.OrderModel;
import com.example.newlook.artist.activity.ProjectSummaryActivity;
import com.example.newlook.utils.DatabaseFunctions;

import java.util.ArrayList;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder>{

    String rejectOrder;
    private final Context context;
    private final ArrayList<OrderModel> customerList;
    DatabaseFunctions databaseFunctions = new DatabaseFunctions();

    public CustomerListAdapter(Context context, ArrayList<OrderModel> customerList){
        this.context = context;
        this.customerList = customerList;
    }


    @NonNull
    @Override
    public CustomerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_user_project_details, parent, false);


        return new ViewHolder((ViewGroup) view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerListAdapter.ViewHolder holder, int position) {
                    OrderModel customer = customerList.get(position);
                    System.out.println("bhupen");
                    String  orderId = customer.getOrderId();
                    System.out.println("orderId"+orderId);
//                    if(projectDetailModel.getStatus().equals("Rejected")){
//                        holder.artist_accept.setVisibility(View.GONE);
//                        holder.artist_reject.setVisibility(View.GONE);
//                        holder.pendingLogo.setVisibility(View.GONE);
//                        holder.customer_name.setTextColor(Color.GRAY);
//                        holder.category.setTextColor(Color.GRAY);
//                        holder.deadline.setTextColor(Color.GRAY);
//                        holder.rejectLogo.setVisibility(View.VISIBLE);
//                        holder.customer_img.setAlpha(0.5f);
//                        holder.cardLayout.setAlpha(0.5f);
//                    }
                    holder.customer_name.setText(customer.getCustomerName());
                    holder.category.setText(customer.getCategory());
                    holder.deadline.setText(customer.getDeadline());

//                    holder.artist_accept.setOnClickListener(view -> {
//                        databaseFuntions.setStatus("Accepted", orderId);
//                        holder.artist_accept.setVisibility(View.GONE);
//                        holder.artist_reject.setVisibility(View.GONE);
//                        holder.pendingLogo.setVisibility(View.VISIBLE);
//                    });

//                    holder.artist_reject.setOnClickListener(view -> {
//                        Intent intent = new Intent(context, RejectOrderActivity.class);
//                        intent.putExtra("OrderId", orderId);
//                        holder.cardLayout.setAlpha(0.5f);
//                        holder.cardLayout.setClickable(false);
//
//                        context.startActivity(intent);
//                    });


                    holder.itemView.setOnClickListener(view -> {
                        Intent intent = new Intent(context, ProjectSummaryActivity.class);
                        context.startActivity(intent);
                    });

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView customer_name, category, deadline;
//        private final TextView artist_accept, artist_reject;
//        private final ImageView customer_img, pendingLogo, rejectLogo;
//        private final LinearLayout cardLayout;

        public ViewHolder(@NonNull ViewGroup itemView) {
            super(itemView);

            customer_name = itemView.findViewById(R.id.customer_name);
            category = itemView.findViewById(R.id.customize_category);
//            customer_img = itemView.findViewById(R.id.customer_img);
            deadline = itemView.findViewById(R.id.customer_deadline);
//            artist_accept = itemView.findViewById(R.id.artist_accept);
//            artist_reject = itemView.findViewById(R.id.artist_reject);
//            pendingLogo = itemView.findViewById(R.id.clock_logo);
//            rejectLogo = itemView.findViewById(R.id.prohibition);
//            cardLayout = itemView.findViewById(R.id.cardLayoutOrder);

        }
    }
}
