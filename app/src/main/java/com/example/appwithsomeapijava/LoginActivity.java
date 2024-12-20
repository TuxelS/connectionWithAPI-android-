package com.example.appwithsomeapijava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity {

    private EditText emailEt, passwordEt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        emailEt = findViewById(R.id.emailET);
        passwordEt = findViewById(R.id.passwordEt);
        loginBtn = findViewById(R.id.button);
    }


    public void onClickBtn(View view) {
        if (emailEt.getText().toString().isEmpty() || passwordEt.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println(emailEt.getText().toString());
            FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEt.getText().toString(), passwordEt.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        }
                    });
        }

    }
    public  void onClickSign(View view){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
}