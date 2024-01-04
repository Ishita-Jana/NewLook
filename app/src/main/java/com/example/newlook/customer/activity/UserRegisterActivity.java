package com.example.newlook.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newlook.R;
import com.example.newlook.models.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegisterActivity extends AppCompatActivity {
    TextInputEditText etRegEmail;
    TextInputEditText etRegPassword;
    TextInputEditText etRegName;
    TextInputEditText etRegStudentID;
    TextInputEditText etRegPhoneNumber;
    TextView tvLoginHere;
    Button btnRegister;

    FirebaseAuth mAuth;
    //..................

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_signup_activity);

        etRegEmail = findViewById(R.id.etRegEmail);
        //...................................................................
        // name field added
        etRegName = findViewById(R.id.etRegName);
        etRegStudentID = findViewById(R.id.etRegStudentID);
        etRegPassword = findViewById(R.id.etRegPass);
        etRegPhoneNumber = findViewById(R.id.etRegPhoneNumber);
        tvLoginHere = findViewById(R.id.tvLoginHere);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
//        mStore = FirebaseFirestore.getInstance();

        btnRegister.setOnClickListener(view -> {
            createUser();
        });

        tvLoginHere.setOnClickListener(view -> {
            startActivity(new Intent(UserRegisterActivity.this, UserLoginActivity.class));
        });
    }

    private void createUser(){
        String email = etRegEmail.getText().toString();
        //...................................................................
        // name field added
        String name = etRegName.getText().toString();
        String studentID = etRegStudentID.getText().toString();
        String password = etRegPassword.getText().toString();
        String phoneNumber = etRegPhoneNumber.getText().toString();
        System.out.println("phoneNumber:"+phoneNumber);

        if (TextUtils.isEmpty(email)) {
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        }
        //...................................................................
        // name field added
        else if (TextUtils.isEmpty(name)) {
            etRegName.setError("Name cannot be empty");
            etRegName.requestFocus();
        }else if (TextUtils.isEmpty(studentID)) {
            etRegStudentID.setError("Student ID cannot be empty");
            etRegStudentID.requestFocus();
        }
        else if (TextUtils.isEmpty(phoneNumber)) {
            etRegPhoneNumber.setError("Phone Number cannot be empty");
            etRegPhoneNumber.requestFocus();
        }else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        user User = new user(name, email, password, studentID, "customer",phoneNumber);
                        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(UserRegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UserRegisterActivity.this, UserLoginActivity.class));
                            }
                        });
                    }
//
//                    }
                    else {
                        Toast.makeText(UserRegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
