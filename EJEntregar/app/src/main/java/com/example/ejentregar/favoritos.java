package com.example.ejentregar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class favoritos extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datos d = new datos();
        ArrayAdapter<Pelicula> datos = new ArrayAdapter<Pelicula>(this,android.R.layout.simple_list_item_1,d.rellenaPeliculas());
        setListAdapter(datos);
    }
}