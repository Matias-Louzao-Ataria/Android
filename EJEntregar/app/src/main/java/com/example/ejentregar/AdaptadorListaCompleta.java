package com.example.ejentregar;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.time.LocalDate;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AdaptadorListaCompleta extends RecyclerView.Adapter<AdaptadorListaCompleta.ViewHolder>{

    private ArrayList<Pelicula> peliculas;
    private ItemClickListener itemClickListener;

    public AdaptadorListaCompleta(ArrayList<Pelicula> datos,ItemClickListener itemClickListener) {
        this.peliculas = datos;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View peli = LayoutInflater.from(parent.getContext()).inflate(R.layout.peliculacompleta,parent,false);
        ViewHolder item = new ViewHolder(peli,itemClickListener);
        return item;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Pelicula peli = peliculas.get(position);
        holder.titulo.setText(peli.getTitulo());
        holder.director.setText(peli.getDirector());
        holder.pegi.setImageResource(peli.getPegi());
        holder.portada.setImageResource(peli.getPortada());
        Calendar cal = Calendar.getInstance();
        cal.setTime(peli.getFecha());
        LocalDate d = LocalDateTime.ofInstant(peli.getFecha().toInstant(), ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        holder.fecha.setText(d.format(formatter));
        holder.sala.setText(peli.getSala());
        holder.duracion.setText(peli.getDuracion()+" minutos");
        holder.fav.setChecked(peli.isFavorita());
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hacer favorita la pelicula
                Pelicula p = MainActivity.peliculas.get(position);
                p.setFavorita(!p.isFavorita());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titulo,director,sala,duracion,fecha;
        private ImageView portada,pegi;
        private ItemClickListener itemClickListener;
        private CheckBox fav;

        public ViewHolder(@NonNull View itemView,ItemClickListener itemClickListener) {
            super(itemView);
            this.titulo = itemView.findViewById(R.id.titulo);
            this.director = itemView.findViewById(R.id.director);
            this.pegi = itemView.findViewById(R.id.pegi);
            this.portada = itemView.findViewById(R.id.portada3);
            this.sala = itemView.findViewById(R.id.sala);
            this.duracion = itemView.findViewById(R.id.duracion);
            this.fecha = itemView.findViewById(R.id.fecha);
            this.fav = itemView.findViewById(R.id.checkBox2);
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
