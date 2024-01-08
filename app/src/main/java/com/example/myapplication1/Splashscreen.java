package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.MainActivity;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);


        new android.os.Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(Splashscreen.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }, 2000);
    }
}

