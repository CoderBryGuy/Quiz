package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    EditText mail, password;
    Button signIn;
    TextView signUp, forgotPassword;
    SignInButton signInGoogle;
    ProgressBar progressBar;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mail = findViewById(R.id.email_edittext_login);
        password = findViewById(R.id.password_edittext_login);
        signIn = findViewById(R.id.signin_btn_login);
        signInGoogle = findViewById(R.id.signInBtn_google_login);
        signUp = findViewById(R.id.create_account_textview_login);
        forgotPassword = findViewById(R.id.forgotpwd_textview_login);
        progressBar = findViewById(R.id.progressBar_login);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = mail.getText().toString().trim();
                String userPassword = password.getText().toString().trim();

                signInWithFirebase(userEmail, userPassword);
            }
        });

        signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginPage.this, Sign_Up_Page.class);
                startActivity(i);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void signInWithFirebase(String userEmail, String userPassword){
        progressBar.setVisibility(View.VISIBLE);
        signIn.setClickable(false);

        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                                Intent i = new Intent(LoginPage.this, MainActivity.class);
                                startActivity(i);
                                finish();
                                progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginPage.this, "Sign in is successful", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginPage.this, "Sign in is not successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}