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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText mFullName,mEmail,mPassword;
    Button mRegisterBtn;
    Button mRed;
    Button mBlue;
    Button mGreen;
    Button mViolet;
    Button mOrange;
    Button mYellow;
    Button mRegisterBtn2;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    String colorCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName   = findViewById(R.id.fullName);
        mEmail      = findViewById(R.id.email);
        mPassword   = findViewById(R.id.textPassword);
        mRegisterBtn= findViewById(R.id.regButton);
        mRegisterBtn2= findViewById(R.id.regButton2);
        mRed= findViewById(R.id.red);
        mBlue= findViewById(R.id.blue);
        mOrange= findViewById(R.id.orange);
        mViolet= findViewById(R.id.violet);
        mYellow= findViewById(R.id.yellow);
        mGreen= findViewById(R.id.green);
        mLoginBtn   = findViewById(R.id.loginText);

        fAuth = FirebaseAuth.getInstance();

        // Check to see if user is already logged in
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Receive and enforce requirements for email and password entries
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String fullName = mFullName.getText().toString();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be at least six characters.");
                }

                // Register user in Firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // On successful registration, make text password entry portion invisible and color entry portion visible
                            mYellow.setVisibility(View.VISIBLE);
                            mRed.setVisibility(View.VISIBLE);
                            mBlue.setVisibility(View.VISIBLE);
                            mOrange.setVisibility(View.VISIBLE);
                            mViolet.setVisibility(View.VISIBLE);
                            mGreen.setVisibility(View.VISIBLE);
                            mRegisterBtn.setVisibility(View.INVISIBLE);
                            mFullName.setVisibility(View.INVISIBLE);
                            mEmail.setVisibility(View.INVISIBLE);
                            mPassword.setVisibility(View.INVISIBLE);
                            mRegisterBtn2.setVisibility(View.VISIBLE);
                            mLoginBtn.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(Register.this, "Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }


                  });
        }
    });
        // Each button adds a color signifier to the string representing the color entry code
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
        // On "Already Registered? Login Here" click, switch to the login page
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        // On "Register" click, store color code string in Firestore using UserID, then load main page
        mRegisterBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("colorCode",colorCode);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
}
}
