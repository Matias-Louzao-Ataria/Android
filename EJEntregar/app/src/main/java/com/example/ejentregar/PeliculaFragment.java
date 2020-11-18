package com.example.ejentregar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PeliculaFragment extends Fragment {
    private Pelicula peliculaAMostrar;
    private TextView titulo;
    private TextView director;
    private TextView sala;
    private TextView fecha;
    private TextView duracion;
    public PeliculaFragment() {
        // Required empty public constructor
        /*View view = getView();
        this.peliculaAMostrar = pelicula;
        this.titulo = view.findViewById(R.id.titulo);
        this.titulo.setText(pelicula.getTitulo());
        this.director = view.findViewById(R.id.director);
        this.director.setText(pelicula.getDirector());
        this.sala = view.findViewById(R.id.sala);
        this.sala.setText(pelicula.getSala());
        this.fecha = view.findViewById(R.id.fecha);
        this.fecha.setText(pelicula.getFecha().toString());
        this.duracion = view.findViewById(R.id.duracion);
        this.duracion.setText(pelicula.getDuracion());*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pelicula, container, false);
    }
}