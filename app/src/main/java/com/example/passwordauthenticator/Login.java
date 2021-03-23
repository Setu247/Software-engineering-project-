package com.example.passwordauthenticator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginBtn;
    Button mLoginBtn2;
    TextView mCreateBtn;
    FirebaseAuth fAuth;
    Button mRed;
    Button mBlue;
    Button mGreen;
    Button mViolet;
    Button mOrange;
    Button mYellow;
    String colorCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRed= findViewById(R.id.red2);
        mBlue= findViewById(R.id.blue2);
        mOrange= findViewById(R.id.orange2);
        mViolet= findViewById(R.id.violet2);
        mYellow= findViewById(R.id.yellow2);
        mGreen= findViewById(R.id.green2);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.textPassword);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.regButton);
        mLoginBtn2 = findViewById(R.id.login2);
        mCreateBtn = findViewById(R.id.loginText);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Receive and enforce requirements for email and password entries
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required.");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be at least six characters.");
                }

                // Authenticate user credentials

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Success!", Toast.LENGTH_SHORT).show();
                            mYellow.setVisibility(View.VISIBLE);
                            mRed.setVisibility(View.VISIBLE);
                            mBlue.setVisibility(View.VISIBLE);
                            mOrange.setVisibility(View.VISIBLE);
                            mViolet.setVisibility(View.VISIBLE);
                            mGreen.setVisibility(View.VISIBLE);
                            mLoginBtn.setVisibility(View.INVISIBLE);
                            mEmail.setVisibility(View.INVISIBLE);
                            mPassword.setVisibility(View.INVISIBLE);
                            mLoginBtn2.setVisibility(View.VISIBLE);
                            mCreateBtn.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(Login.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
        mOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorCode = colorCode + 'o';
                //mOrange.setVisibility(View.INVISIBLE);
            }
        });
        mGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorCode = colorCode + 'g';
                //mGreen.setVisibility(View.INVISIBLE);
            }
        });
        mRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorCode = colorCode + 'r';
                //mRed.setVisibility(View.INVISIBLE);
            }
        });
        mYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorCode = colorCode + 'y';
                //mYellow.setVisibility(View.INVISIBLE);
            }
        });
        mBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorCode = colorCode + 'b';
               //mBlue.setVisibility(View.INVISIBLE);
            }
        });
        mViolet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorCode = colorCode + 'v';
                //mViolet.setVisibility(View.INVISIBLE);
            }
        });
        mLoginBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}