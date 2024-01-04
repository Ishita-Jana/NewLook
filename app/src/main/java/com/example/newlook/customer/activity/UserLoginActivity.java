package com.example.newlook.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newlook.MainActivityUser;
import com.example.newlook.R;
import com.example.newlook.backend.ArtistDataApi;
import com.example.newlook.backend.SetDataCallback;
import com.example.newlook.data.UserDataManager;
import com.example.newlook.utils.DatabaseFunctions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.security.auth.callback.Callback;

public class UserLoginActivity extends AppCompatActivity implements SetDataCallback {

    TextInputEditText etLoginEmail;
    TextInputEditText etLoginPassword;
    TextView tvRegisterHere;
    Button btnLogin;
    DatabaseFunctions databaseFunctions;
//    ArtistDataApi artistDataApi;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_activity);

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPass);
        tvRegisterHere = findViewById(R.id.tvRegisterHere);
        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
        tvRegisterHere.setOnClickListener(view -> {
            startActivity(new Intent(UserLoginActivity.this, UserRegisterActivity.class));
        });


    }

    private void loginUser() {
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etLoginEmail.setError("Email cannot be empty");
            etLoginEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etLoginPassword.setError("Password cannot be empty");
            etLoginPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        try {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String ID = user.getUid();
                            System.out.println("User id: " +ID );
                            ArtistDataApi artistDataApi = new ArtistDataApi();
                            artistDataApi.setLoginData(ID,"users");
                            artistDataApi.setApiCallback(UserLoginActivity.this);


                        }
                        catch (Exception e){
                            System.out.println("Error: "+e.getMessage());
                        }

                    } else {
                        Toast.makeText(UserLoginActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(UserLoginActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(UserLoginActivity.this, MainActivityUser.class));
        finish();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(UserLoginActivity.this, "Log in Error: " + message, Toast.LENGTH_SHORT).show();
    }
}