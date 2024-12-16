package com.example.appwithsomeapijava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appwithsomeapijava.entity.Joke;

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

    private Button buttonFind;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v2.jokeapi.dev")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        RequestJoke requestJoke = retrofit.create(RequestJoke.class);

        buttonFind = findViewById(R.id.buttonFind);
        textView2 = findViewById(R.id.textView2);
        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestJoke.getJoke().enqueue(new Callback<Joke>() {
                    @Override
                    public void onResponse(Call<Joke> call, Response<Joke> response) {
                        System.out.println(response.body());
                        //response.body() - объект класса Joke, его нам и нужно сохранить в бд, можно даже отдельно две части.
                        String str = "first part: " + response.body().getSetup() + "\n \n second part: " + response.body().getDelivery();
                        textView2.setText(str);
                    }

                    @Override
                    public void onFailure(Call<Joke> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });

            }
        });
    }


}