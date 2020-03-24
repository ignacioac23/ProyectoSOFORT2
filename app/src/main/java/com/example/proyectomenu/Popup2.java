package com.example.proyectomenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Popup2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup2);
    }
    public void botonexist (View view){
        Intent botonexist = new Intent(this, Mapa.class);
        startActivity(botonexist);
    }
}
