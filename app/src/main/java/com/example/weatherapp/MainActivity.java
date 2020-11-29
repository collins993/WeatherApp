package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weatherapp.Retrofit.ApiClient;
import com.example.weatherapp.Retrofit.ApiInterface;
import com.example.weatherapp.Retrofit.Example;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView temperature, description, humidity, sea_level, ground_level;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialization
        input = findViewById(R.id.editTextCountry);
        temperature = findViewById(R.id.textViewTemp);
        description = findViewById(R.id.textViewDescription);
        humidity = findViewById(R.id.textViewHumidity);
        sea_level = findViewById(R.id.textViewSeaLevel);
        ground_level = findViewById(R.id.textViewGroundLevel);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherData(input.getText().toString().trim());
            }
        });
    }

    private void getWeatherData(String name) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Example> call = apiInterface.getWeatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                temperature.setText("Temp" + "" + response.body().getMain().getTemp());
                description.setText("Feels Like" + "" + "" + response.body().getMain().getFeels_like());
                humidity.setText("Humidity" + "" + "" + response.body().getMain().getHumidity());
                sea_level.setText("Sea Level" + "" + "" + response.body().getMain().getSea_level());


//                ground_level.setText(response.body().getWeather().getDescription());


//                Log.d("Check", "WOW " + response.body().getWeather().getDescription());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}