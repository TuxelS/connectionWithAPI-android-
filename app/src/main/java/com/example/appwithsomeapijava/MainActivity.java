package com.example.appwithsomeapijava;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appwithsomeapijava.entity.Joke;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    interface RequestJoke {
        @GET("/joke/Any?type=twopart")
        Call<Joke> getJoke();
    }
    private DatabaseReference mDataBase;
    private String USER_KEY = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private Button buttonFind;
    private TextView textView2;
    private MediaPlayer smex;
    private  String jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v2.jokeapi.dev")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        RequestJoke requestJoke = retrofit.create(RequestJoke.class);

        buttonFind = findViewById(R.id.btnTapIt);
        textView2 = findViewById(R.id.jokeView);
        smex = MediaPlayer.create(this , R.raw.joke_sound);
        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestJoke.getJoke().enqueue(new Callback<Joke>() {
                    @Override
                    public void onResponse(Call<Joke> call, Response<Joke> response) {
                        System.out.println(response.body());
                        //response.body() - объект класса Joke, его нам и нужно сохранить в бд, можно даже отдельно две части.
                        jokeText = "first part: " + response.body().getSetup() + "\n \n second part: " + response.body().getDelivery();
                        textView2.setText(jokeText);
                        smex.setVolume(10,100);
                        smex.start();
                    }

                    @Override
                    public void onFailure(Call<Joke> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });

            }
        });

    }
    public void onClickSave(View view)
    {
        if(!jokeText.isEmpty())
        {
            mDataBase.push().setValue(jokeText);
            Toast.makeText(getApplicationContext(), "Joke saved", Toast.LENGTH_SHORT).show();
        }
        else{

        }
    }
    public void onClickRead(View view)
    {
        Intent i = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(i);
    }

    public void onClickOut(View view){
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

}