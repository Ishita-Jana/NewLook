package com.example.newlook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.newlook.customer.activity.UserLoginActivity;
import com.example.newlook.customer.fragments.UserHomeFragment;
import com.example.newlook.data.UserDataManager;
import com.example.newlook.models.user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth mAuth;
    FirebaseUser user;
    private String userID, Name, studentId, role;
    private String userName, userEmail;
    TextView logOut;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();




    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, UserLoginActivity.class));
        } else {
            if(user != null){
                userID = user.getUid();
                reference = FirebaseDatabase.getInstance().getReference("users");
                reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        com.example.newlook.models.user UserName = snapshot.getValue(com.example.newlook.models.user.class);
                        if(UserName != null){

                            Name = UserName.name;
                            studentId = UserName.studentID;
                            userEmail = UserName.email;
                            role = UserName.role;
//                            System.out.println("role: " + role);
//                            String[] parts = Name.split(" ");
//                            String firstWord = parts[0];
                            UserDataManager.getInstance().setName(Name);
                            UserDataManager.getInstance().setStudentId(studentId);
                            UserDataManager.getInstance().setEmail(userEmail);
                            UserDataManager.getInstance().setUid(userID);
                            UserDataManager.getInstance().setRole(role);


                            if(role.equals("artist")) {

                                startActivity(new Intent(MainActivity.this, MainActivityArtist.class));
                                finish();
                            }else if(role.equals("customer")){
                                startActivity(new Intent(MainActivity.this, MainActivityUser.class));
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }

    }
}