package com.example.appwithsomeapijava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEt, passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        usernameEt = findViewById(R.id.emailET);
        passwordEt = findViewById(R.id.passwordEt);

    }

    public void onClickRegistr(View view) {
        if (usernameEt.getText().toString().isEmpty() || passwordEt.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(usernameEt.getText().toString(), passwordEt.getText().toString());

            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }
    }
}