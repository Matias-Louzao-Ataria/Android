package com.example.ejentregar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class favoritos extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<Pelicula> datos = new ArrayAdapter<Pelicula>(this,android.R.layout.simple_list_item_1,MainActivity.peliculas);
        for(int i = 0;i < MainActivity.peliculas.size();i++){
            if(MainActivity.peliculas.get(i).isFavorita()){
                //Buscar forma de poner el fondo azul a los elementos que ya son favoritos de entrada.
            }
        }
        setListAdapter(datos);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.i("A","position");
    }
}