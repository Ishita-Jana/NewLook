package com.example.newlook.customer.fragments.userProfileFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newlook.MainActivityUser;
import com.example.newlook.R;
import com.example.newlook.customer.fragments.UserProfileFragment;
import com.example.newlook.data.UserDataManager;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class UserEditAccountFragment extends Fragment {

    EditText userName, stdID, etEmail, userConfirmPassword;
    FirebaseUser user;
    Button saveChanges;
    MainActivityUser mainActivityUser;
    AuthCredential credential;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_edit_account, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userName = v.findViewById(R.id.userNameEdit);
        stdID = v.findViewById(R.id.studentIdEdit);
        etEmail = v.findViewById(R.id.emailEdit);
        saveChanges = v.findViewById(R.id.updateDetailsButton);
        userConfirmPassword = v.findViewById(R.id.confirmPassword);
        mainActivityUser = (MainActivityUser) getContext();

        String name = UserDataManager.getInstance().getName();
        String studentId = UserDataManager.getInstance().getStudentId();
        String email = UserDataManager.getInstance().getEmail();

        userName.setText(name);
        stdID.setText(studentId);
        etEmail.setText(email);

        DatabaseReference useRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());

//        saveChanges.setOnClickListener(view->{
//            Toast.makeText(getContext(), stdID.getText().toString(), Toast.LENGTH_SHORT).show();
//        });
        saveChanges.setOnClickListener(view -> {
            if(userConfirmPassword.getText().toString().isEmpty() ){
                userConfirmPassword.setError("Please enter your password");
                userConfirmPassword.requestFocus();

            }

            else{
                credential = EmailAuthProvider.getCredential(email, userConfirmPassword.getText().toString());
                Map<String, Object> updates = new HashMap<>();
                if(!userName.getText().toString().isEmpty() && !stdID.getText().toString().isEmpty() && !etEmail.getText().toString().isEmpty()){
                    updates.put("name", userName.getText().toString());
                    //only updating the name
//                    updates.put("studentID", stdID.getText().toString());
//                    updates.put("email", etEmail.getText().toString());
//
                    useRef.updateChildren(updates).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            UserDataManager.getInstance().setName(updates.get("name").toString());
//                            UserDataManager.getInstance().setStudentId(updates.get("studentID").toString());
//                            UserDataManager.getInstance().setEmail(updates.get("email").toString());
                            Toast.makeText(getContext(), "User Details Updated", Toast.LENGTH_SHORT).show();
                            mainActivityUser.replaceFragment(new UserProfileFragment());
                        }
                    });
                }
                else{
                    Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }


            }
        });









        return v;
    }
}