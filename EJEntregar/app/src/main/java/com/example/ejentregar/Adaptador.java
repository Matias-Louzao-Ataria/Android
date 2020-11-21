package com.example.ejentregar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{

    private ArrayList<Pelicula> peliculas;
    private ItemClickListener itemClickListener;

    public Adaptador(ArrayList<Pelicula> datos,ItemClickListener itemClickListener) {
        this.peliculas = datos;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View peli = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelicula,parent,false);
        ViewHolder item = new ViewHolder(peli,itemClickListener);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pelicula peli = peliculas.get(position);
        holder.titulo.setText(peli.getTitulo());
        holder.director.setText(peli.getDirector());
        holder.pegi.setImageResource(peli.getPegi());
        holder.portada.setImageResource(peli.getPortada());
    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titulo,director;
        private ImageView portada;
        private ItemClickListener itemClickListener;

        public TextView getTitulo() {
            return titulo;
        }

        public void setTitulo(TextView titulo) {
            this.titulo = titulo;
        }

        public TextView getDirector() {
            return director;
        }

        public void setDirector(TextView director) {
            this.director = director;
        }

        public ImageView getPortada() {
            return portada;
        }

        public void setPortada(ImageView portada) {
            this.portada = portada;
        }

        public ImageView getPegi() {
            return pegi;
        }

        public void setPegi(int pegi) {
            this.pegi.setImageResource(pegi);
        }

        private  ImageView pegi;

        public ViewHolder(@NonNull View itemView,ItemClickListener itemClickListener) {
            super(itemView);
            this.titulo = itemView.findViewById(R.id.titulo);
            this.director = itemView.findViewById(R.id.director);
            this.pegi = itemView.findViewById(R.id.pegi);
            this.portada = itemView.findViewById(R.id.portada);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.OnItemClicked(getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void OnItemClicked(int pos);
    }

}
