package com.example.ejentregar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class PeliculaSinopsis extends AppCompatActivity {

    private TextView sinopsis;
    private ImageView portada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_sinopsis);
        this.portada = findViewById(R.id.portada);
        this.sinopsis = findViewById(R.id.sinopsis);
        this.sinopsis.setText(MainActivity.peliculas.get(ListaCompleta.pelicula).getSinopsis());
        this.portada.setImageResource(MainActivity.peliculas.get(ListaCompleta.pelicula).getPortada());
    }
}