package com.example.newlook.backend;

import com.example.newlook.data.UserDataManager;
import com.example.newlook.models.user;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ArtistDataApi {

    DatabaseReference reference;
    SetDataCallback setDataCallback;

    public ArtistDataApi(){

    }
    public void setApiCallback(SetDataCallback setDataCallback){
        this.setDataCallback = setDataCallback;
    }

    public void setLoginData(String id, String databaseName){
        reference = FirebaseDatabase.getInstance().getReference(databaseName);
        reference.child(id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if(task.getResult().exists()){
                    user User = task.getResult().getValue(user.class);
                    if(User != null){
                        UserDataManager.getInstance().setName(User.name);
                        UserDataManager.getInstance().setEmail(User.email);
                        UserDataManager.getInstance().setRole(User.role);
                        UserDataManager.getInstance().setStudentId(User.studentID);
//                        System.out.println("User name: " + User.name);
//                        System.out.println("User email: " + User.email);
//                        System.out.println("User role: " + User.role);
//                        System.out.println("User student id: " + User.studentID);
                        setDataCallback.onSuccess();
                    }
                }

            } else {
                setDataCallback.onFailure(task.getException().getMessage());
            }
        });

    }

    public void setArtistProfile(String databaseName, int pic){

    }



}

