package com.example.ejentregar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter {

    private ArrayList<Pelicula> peliculas;

    public Adaptador(ArrayList<Pelicula> datos){
        this.peliculas = datos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View peli = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelicula,parent,false);
        ViewHolder item = new ViewHolder(peli);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titulo;
        private TextView director;
        private ImageView portada;
        private  ImageView pegi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.titulo = itemView.findViewById(R.id.titulo);
            this.director = itemView.findViewById(R.id.director);
            this.pegi = itemView.findViewById(R.id.pegi);
            this.portada = itemView.findViewById(R.id.portada);
        }
    }

}
