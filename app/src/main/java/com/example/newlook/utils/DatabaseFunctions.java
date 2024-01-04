package com.example.newlook.utils;

import androidx.annotation.NonNull;

import com.example.newlook.data.UserDataManager;
import com.example.newlook.models.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.security.auth.callback.Callback;

public class DatabaseFunctions {

    DatabaseReference reference;


    public void setRejectReason(String reason, String id){
        reference = FirebaseDatabase.getInstance().getReference("orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if (dataSnapshot.child("orderId").getValue().toString().equals(id)) {
                        System.out.println("done");
                        dataSnapshot.child("rejectReason").getRef().setValue(reason);
                        System.out.println(dataSnapshot.child("rejectReason").getValue().toString());

                    }
                }
            };
                @Override
                public void onCancelled (@NonNull DatabaseError error){

                }
            });
    }

    public void setValue(String id, String title, String value){
        reference = FirebaseDatabase.getInstance().getReference("orders");
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    System.out.println("title"+title);
                    if (dataSnapshot.child("orderId").getValue().toString().equals(id)) {
                        System.out.println("done");
                        dataSnapshot.child(title.toString()).getRef().setValue(value);
//                        System.out.println(dataSnapshot.child(title.toString()).getValue().toString());

                    }
                }
            };
            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }
        });
    }


    public void setLoginUserData(String id, String database, Callback callback) {
        System.out.println("inside setLoginUserData");
        System.out.println(id);
        System.out.println(database);
        reference = FirebaseDatabase.getInstance().getReference(database);
        reference.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        user User = task.getResult().getValue(user.class);
                        if(User !=null){
                            System.out.println("Iteration");
                            System.out.println("User name: " + User.name);
                            UserDataManager.getInstance().setName(User.name);
                            System.out.println("User email: " + User.email);
                            UserDataManager.getInstance().setEmail(User.email);
                            System.out.println("User role: " + User.role);
                            UserDataManager.getInstance().setRole(User.role);
                            System.out.println("User student id: " + User.studentID);
                            UserDataManager.getInstance().setStudentId(User.studentID);
                            callback.notify();
                        }
                    }

                }

            }
        });
//



    }
}

