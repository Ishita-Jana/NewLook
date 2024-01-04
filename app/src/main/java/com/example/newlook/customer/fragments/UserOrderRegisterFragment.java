package com.example.newlook.customer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newlook.MainActivityUser;
import com.example.newlook.R;
import com.example.newlook.data.UserDataManager;
import com.example.newlook.customer.POJO.ProjectDetailModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserOrderRegisterFragment extends Fragment {

    Button approach;
    EditText customerName,number,projectDescription,projectCategory,location,projectDeadline;
    DatabaseReference reference;
    String artistId, customerId,customer_name,artist_name;
    MainActivityUser mainActivityUser;


    public UserOrderRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_project_details, container, false);
        customerName = v.findViewById(R.id.customer_name);
        number = v.findViewById(R.id.number);
        projectDescription = v.findViewById(R.id.description);
        projectCategory = v.findViewById(R.id.category);
        location = v.findViewById(R.id.location);
        projectDeadline = v.findViewById(R.id.deadline);
        approach = v.findViewById(R.id.approach_artist);






        artistId = getArguments().getString("artist_email");
        artist_name = getArguments().getString("artist_name");
        customerId = UserDataManager.getInstance().getEmail();
        customer_name = getArguments().getString("customer_name");



        reference = FirebaseDatabase.getInstance().getReference();
        approach.setOnClickListener(view ->{
            createOrder();
        });

        return v;
    }



    private void createOrder() {
        String customer = customerName.getText().toString();
        String number = this.number.getText().toString();
        String category =  projectCategory.getText().toString();
        String description = projectDescription.getText().toString();
        String location = this.location.getText().toString();
        String deadline = projectDeadline.getText().toString();



        mainActivityUser = (MainActivityUser) getActivity();




        if (customer.isEmpty()){
            customerName.setError("Name cannot be empty");
            customerName.requestFocus();
        } else if (number.isEmpty()){
            this.number.setError("Number cannot be empty");
            this.number.requestFocus();
        }else if (category.isEmpty()){
            projectCategory.setError("Category cannot be empty");
            projectCategory.requestFocus();
        }else if (description.isEmpty()) {
            projectDescription.setError("Description cannot be empty");
            projectDescription.requestFocus();
        }else if (location.isEmpty()){
            this.location.setError("Location cannot be empty");
            this.location.requestFocus();
        } else if (deadline.isEmpty()) {
            projectDeadline.setError("Deadline cannot be empty");
            projectDeadline.requestFocus();
        } else if (number.isEmpty()) {
            this.number.setError("Number cannot be empty");
            this.number.requestFocus();
        }
         else{

            if(!number.isEmpty()){
                number = number.replaceAll("[^0-9]", "");
                if (number.length() != 10) {
                    this.number.setError("Number should be of 10 digits and from India");
                    this.number.requestFocus();
                }
                else{
                    System.out.println("artist id inside the function "+artistId);
                    System.out.println("customer id inside the function "+customerId);
                    System.out.println("customer name inside the function "+artist_name);

//
                    DatabaseReference customerOrdersRef = FirebaseDatabase.getInstance().getReference("orders");
//            DatabaseReference customerRef = customerOrdersRef.child(UserDataManager.getInstance().getUid()); // Reference to customerId node
                    DatabaseReference newOrderRef = customerOrdersRef.push();


                    ProjectDetailModel detail = new ProjectDetailModel(artistId, artist_name, customerId, customer_name, number, description, location, deadline, category);
                    newOrderRef.setValue(detail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getContext(), "Order placed successfully", Toast.LENGTH_SHORT).show();
                            mainActivityUser.replaceFragment(new UserHomeFragment());
                        }
                    });
                }
            }


        }

    }
}