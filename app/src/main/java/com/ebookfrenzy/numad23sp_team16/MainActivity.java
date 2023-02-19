package com.ebookfrenzy.numad23sp_team16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCountry =findViewById(R.id.btn_country);
        btnCountry.setOnClickListener(v -> {
            Intent i = new Intent(this, MyApi.class);
            startActivity(i);
        });
    }
}