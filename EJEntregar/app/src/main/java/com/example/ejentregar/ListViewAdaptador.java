package com.example.ejentregar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListViewAdaptador extends BaseAdapter {

    ArrayList<Pelicula> peliculas;
    LayoutInflater inflador;

    public ListViewAdaptador(Context context,ArrayList<Pelicula> peliculas){
        this.inflador = LayoutInflater.from(context);
        this.peliculas = peliculas;
    }

    @Override
    public int getCount() {
        return peliculas.size();
    }

    @Override
    public Object getItem(int position) {
        return peliculas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contenedor contenedor = null;
        if(convertView == null){
            convertView = inflador.inflate(R.layout.peliculalistafavoritos,null);
            contenedor = new Contenedor();
            contenedor.setPortada((ImageView) convertView.findViewById(R.id.portadafav));
            contenedor.setFav((CheckBox)convertView.findViewById(R.id.favfav));
            contenedor.setTitulo((TextView)convertView.findViewById(R.id.titulofav));

            convertView.setTag(contenedor);
        }else{
            contenedor = (Contenedor) convertView.getTag();
        }

        final Pelicula pelicula = peliculas.get(position);
        contenedor.getPortada().setImageResource(pelicula.getPortada());
        contenedor.getTitulo().setText(pelicula.getTitulo());
        contenedor.getFav().setChecked(pelicula.isFavorita());

        contenedor.getFav().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pelicula.setFavorita(!pelicula.isFavorita());
            }
        });

        return convertView;
    }
}

class Contenedor{
    private ImageView portada;

    public ImageView getPortada() {
        return portada;
    }

    public void setPortada(ImageView portada) {
        this.portada = portada;
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public CheckBox getFav() {
        return fav;
    }

    public void setFav(CheckBox fav) {
        this.fav = fav;
    }

    private TextView titulo;
    private CheckBox fav;
}
