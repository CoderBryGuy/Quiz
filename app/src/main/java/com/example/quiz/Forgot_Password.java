package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.zip.InflaterInputStream;

public class Forgot_Password extends AppCompatActivity {

    EditText mail;
    Button button;
    ProgressBar progressBar;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mail = findViewById(R.id.editTextTextEmailAddress_forgotpwd);
        button = findViewById(R.id.button_forgotpwd);
        progressBar = findViewById(R.id.progressBar_forgotpwd);
        progressBar.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String userEmail = mail.getText().toString();
                    resetPassword(userEmail);
            }
        });
    }

    public void resetPassword(String userEmail){
            progressBar.setVisibility(View.VISIBLE);
            auth.sendPasswordResetEmail(userEmail)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Forgot_Password.this, "We sent you an email to your reset password", Toast.LENGTH_SHORT).show();
                                    button.setClickable(false);
                                    progressBar.setVisibility(View.INVISIBLE);
                                    finish();
                                }else{
                                    Toast.makeText(Forgot_Password.this, "Sorry, there is a problem, please try again", Toast.LENGTH_SHORT).show();
                                }

                        }
                    });
    }
}