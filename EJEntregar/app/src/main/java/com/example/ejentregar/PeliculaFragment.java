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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pelicula, container, false);
    }
}