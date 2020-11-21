package com.example.ejentregar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
        this.portada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Datos d = new Datos();
                Intent[] intents = d.watchYoutubeVideo(MainActivity.peliculas.get(ListaCompleta.pelicula).getIdYoutube());
                try {
                    startActivity(intents[0]);
                }catch(ActivityNotFoundException e){
                    startActivity(intents[1]);
                }
            }
        });
    }
}